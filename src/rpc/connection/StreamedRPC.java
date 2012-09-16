package rpc.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import rpc.RPCConnection;
import rpc.RPCMessage;

/**
 * Reads lines from an input stream
 *
 * @author ajc
 *
 */
public class StreamedRPC extends RPCConnection {

    private static final int MAX_STRING_LENGTH = 1024;
    // stores byteform of string until newline
    private byte[] buffer = new byte[MAX_STRING_LENGTH];
    private final InputStream in;
    private final OutputStream out;
    private Thread thread;

    public StreamedRPC(String name, InputStream in, OutputStream out) {
        super(name);
        this.in = in;
        this.out = out;
        thread = new Thread(this);
        
        sleepTime = 1;  //polls at intervals of 1ms
    }
    
    public void start(){
        thread.start();
    }

    private void poll() {
        int data;
        try {
            int len = 0;
            while ((data = in.read()) > -1) {
                if (data == '\n')
                    break;
                buffer[len++] = (byte) data;
            }
            if (len > 0) 
                notifyListeners(new String(buffer, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
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

}
