package rpc.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;

import rpc.RPCConnection;
import rpc.RPCMessage;
import rpc.RPCMessageListener;

/**
 * Reads lines from an input stream
 * 
 * @author ajc
 * 
 */
public class StreamedRPC extends RPCConnection implements Runnable {

    private static final int MAX_STRING_LENGTH = 1024;
    // stores byteform of string until newline
    private byte[] buffer = new byte[MAX_STRING_LENGTH];
    private final InputStream in;
    private final OutputStream out;
    private boolean running; // TODO grtobject type thing
    private Vector listeners = new Vector();
	private Thread thread;

    public StreamedRPC(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
		thread = new Thread(this);
		thread.start();
    }

    public void run() {
        running = true;
        while (running) {
            poll();
            try {
                thread.sleep(1);// TODO sleeping
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void poll() {
        int data;
        try {
            int len = 0;
            while ((data = in.read()) > -1) {
                if (data == '\n') {
                    break;
                }
                buffer[len++] = (byte) data;
            }
			if (len > 0)
				notifyListeners(new String(buffer, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void notifyListeners(String received) {
        if (isTelemetryLine(received)) {
            // RPCMessage message = new RPCMessage(getKey(received),
            // getData(received));
            RPCMessage message = decode(received);
            // System.out.println(message);
            // TODO only notify specific 'keyed' listeners
            for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
                ((RPCMessageListener) e.nextElement()).messageReceived(message);
            }

        }
    }

    public void send(RPCMessage message) {
        try {
            out.write(encode(message).getBytes());
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static RPCMessage decode(String received) {
        return new RPCMessage(getKey(received), getData(received));
    }
}
