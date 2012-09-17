package networking;

import com.sun.squawk.io.BufferedReader;
import core.GRTLoggedProcess;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public class GRTClientSocket extends GRTLoggedProcess implements GRTSocket {

    public static final int POLL_TIME = 50;
    private String host;
    private int port;
    private boolean connected;
    private Vector socketListeners;
    private SocketConnection connection;
    private BufferedReader in;
    private OutputStreamWriter out;
    private String lastData;

    /**
     *
     * @param host
     * @param port
     * @param name
     */
    public GRTClientSocket(String host, int port, String name) {
        super(name, POLL_TIME);

        this.host = host;
        this.port = port;
        running = connected = false;
        socketListeners = new Vector();
        try {
            connection = ((SocketConnection) (Connector.open("socket://" + host
                    + ":" + port)));
        } catch (IOException e) {
            e.printStackTrace();
            connection = null;
        }
    }

    public synchronized void sendData(String data) {
        if (connected) {
            try {
                out.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void poll() {
        if (!connected) {
            connect();
        }
        try {
            String latest = in.readLine();
            if (latest != null && !latest.equals("")) {
                lastData = latest;
                notifyListeners();
            }
        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        }
    }

    public void connect() {
        try {
            in = new BufferedReader(new InputStreamReader(connection
                    .openInputStream()));
            out = new OutputStreamWriter(connection.openOutputStream());
            connected = true;
            notifyConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isConnected() {
        return connected;
    }

    public void stop() {
        disconnect();
        this.running = false;
    }

    public void disconnect() {
        while (connected) {
            try {
                in.close();
                out.close();
                connected = false;
                notifyDisconnected();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getLastData() {
        return lastData;
    }

    private void notifyListeners() {
        for (int i = 0; i < socketListeners.size(); i++) {
            ((SocketListener) socketListeners.elementAt(i))
                    .dataRecieved(new SocketEvent(this, SocketEvent.ON_DATA,
                    lastData));
        }
    }

    private void notifyConnected() {
        for (int i = 0; i < socketListeners.size(); i++) {
            ((SocketListener) socketListeners.elementAt(i))
                    .dataRecieved(new SocketEvent(this,
                    SocketEvent.ON_CONNECT, null));
        }
    }

    private void notifyDisconnected() {
        for (int i = 0; i < socketListeners.size(); i++) {
            ((SocketListener) socketListeners.elementAt(i))
                    .dataRecieved(new SocketEvent(this,
                    SocketEvent.ON_DISCONNECT, null));
        }
    }

    public void addSocketListener(SocketListener s) {
        socketListeners.addElement(s);
    }

    public void removeSocketListener(SocketListener s) {
        socketListeners.removeElement(s);
    }
}
