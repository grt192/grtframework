package networking;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import com.sun.squawk.io.BufferedReader;
import javax.microedition.io.ServerSocketConnection;

public class GRTSingleClientServer extends Thread implements GRTSocket {
	public static final int POLL_TIME = 50;

	private ServerSocketConnection server;
	private StreamConnection client;
	private BufferedReader in;
	private OutputStreamWriter out;
	private boolean connected;
	private Vector serverSocketListeners;
	private String lastData;

	private boolean running;

	public GRTSingleClientServer(int port) {
		try {
			server = (ServerSocketConnection) Connector.open("socket://:"
					+ port);
		} catch (IOException e) {
			e.printStackTrace();
			server = null;
		}
		connected = false;
		serverSocketListeners = new Vector();
		running = false;
	}

	public void run() {
		running = true;
		while (running) {
			poll();
		}
	}

	public synchronized void sendData(String data) {
		if (connected) {
			try {
				out.write(data+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void connect() {
		try {
			client = server.acceptAndOpen();
			in = new BufferedReader(new InputStreamReader(client
					.openInputStream()));
			out = new OutputStreamWriter(client.openOutputStream());
			connected = true;
			notifyConnected();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void poll() {
		if (!connected) {
			connect();
		}
		try {
			String latest = in.readLine();
			if (latest != null && !latest.equals("")){
				lastData = latest;
				notifyListeners();
			}
		} catch (Exception e) {
			e.printStackTrace();
			disconnect();
		}
	}

	private void notifyListeners() {
		for (int i = 0; i < serverSocketListeners.size(); i++) {
			((SocketListener) serverSocketListeners.elementAt(i))
					.dataRecieved(new SocketEvent(this, SocketEvent.ON_DATA,
							lastData));
		}
	}
	
	private void notifyConnected() {
		for (int i = 0; i < serverSocketListeners.size(); i++) {
			((SocketListener) serverSocketListeners.elementAt(i))
					.dataRecieved(new SocketEvent(this, SocketEvent.ON_CONNECT,
							null));
		}
	}
	
	private void notifyDisconnected() {
		for (int i = 0; i < serverSocketListeners.size(); i++) {
			((SocketListener) serverSocketListeners.elementAt(i))
					.dataRecieved(new SocketEvent(this, SocketEvent.ON_DISCONNECT,
							null));
		}
	}

	public boolean isConnected() {
		return connected;
	}

	public String getLastData() {
		return lastData;
	}

	public boolean isRunning() {
		return running;
	}

	public void stop() {
		disconnect();
		this.running = false;
	}

	public void disconnect() {
		if (connected) {
			try {
				connected = false;
				in.close();
				out.close();
				client.close();
				notifyDisconnected();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addSocketListener(SocketListener s) {
		serverSocketListeners.addElement(s);
	}

	public void removeSocketListener(SocketListener s) {
		serverSocketListeners.removeElement(s);
	}

}
