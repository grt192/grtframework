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
public class StreamedRPC extends Thread implements RPCConnection {

    private static final int MAX_STRING_LENGTH = 1024;
    // stores byteform of string until newline
    private byte[] buffer = new byte[MAX_STRING_LENGTH];
    private final InputStream in;
    private final OutputStream out;
    private boolean running; // TODO grtobject type thing
    private Vector listeners = new Vector();

    public StreamedRPC(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        running = true;
        while (running) {
            poll();
            try {
                Thread.sleep(1);// TODO sleeping
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
            // System.out.println("READ:\t" + new String(buffer, 0, len));//
            // TODO
            // selected
            // debug:
            // prints
            notifyListeners(new String(buffer, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static boolean isTelemetryLine(String line) {
        return line.length() > 3 && line.substring(0, 3).equals("USB");// TODO
        // MAGICNUMBERS
    }

    private static int getKey(String line) {
        return Integer.parseInt(line.substring(3, line.indexOf(':')));
    }

    private static double getData(String line) {
        return Double.parseDouble((line.substring(line.indexOf(':') + 1)));
    }

    public void addMessageListener(RPCMessageListener listener) {
        listeners.addElement(listener);
    }

    public void removeMessageListener(RPCMessageListener listener) {
        listeners.removeElement(listener);
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
            out.write(encode(message));
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static byte[] encode(RPCMessage m) {
        // newline to flush all buffers
        return ("USB" + m.getKey() + ":" + m.getData() + "\n").getBytes();
    }

    private static RPCMessage decode(String received) {
        return new RPCMessage(getKey(received), getData(received));
    }
}
