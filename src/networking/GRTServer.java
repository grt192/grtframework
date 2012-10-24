package networking;

import com.sun.squawk.io.BufferedReader;
import core.GRTLoggedProcess;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.StreamConnection;

/**
 * A event driven daemon which makes multiple single client connections
 *
 * @author data, ajc
 */
public class GRTServer extends GRTLoggedProcess implements GRTSocket {

    /**
     * A single client connection to the server.
     */
    private class GRTSingleConnect extends GRTLoggedProcess implements GRTSocket {

        private StreamConnection client;
        private BufferedReader in;
        InputStreamReader isr;
        private OutputStreamWriter out;
        private boolean connected = true;
        Vector serverSocketListeners;

        public GRTSingleConnect(String name, StreamConnection client) {
            super(name, 0);
            try {
                this.client = client;
//                GRTRobot.getInstance().getLogger().write("GRTServer", client.toString());
                serverSocketListeners = new Vector();
//                isr = new InputStreamReader(client.openInputStream());
//                isr.reads
                in = new BufferedReader(new InputStreamReader(client.openInputStream()), 1);

//                client.openDataInputStream().readUT
//                in = client.openDataInputStream();
                out = new OutputStreamWriter(client.openOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void poll() {
            String text;
            try {
                text = in.readLine();
                notifyMyListeners(text);
            } catch (IOException ex) {
                this.disconnect();
                ex.printStackTrace();
            }

        }

        public void stop() {
            running = false;
        }

        public void sendData(String data) {
            try {
                out.write(data + "\n");
            } catch (IOException e) {
                //GRTRobot.getInstance().getLogger().write("GRTServer", "disconnected from client");
                this.disconnect();
            } catch (Exception e) {
                e.printStackTrace();

            }

        }

        public boolean isConnected() {
            return connected;
        }

        public void connect() {
            connected = true;
        }

        public void disconnect() {
            try {
                in.close();
                out.close();
                client.close();
                clients.removeElement(this);
                running = false;
                notifyMyDisconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void addSocketListener(SocketListener s) {
            serverSocketListeners.addElement(s);
        }

        public void removeSocketListener(SocketListener s) {
            serverSocketListeners.removeElement(s);
        }

        private void notifyMyListeners(String text) {
            if (text == null) {
                return;
            }
            SocketEvent e = new SocketEvent(this, SocketEvent.ON_DATA, text);
            for (Enumeration en = serverSocketListeners.elements(); en.hasMoreElements();) {
                ((SocketListener) en.nextElement()).dataRecieved(e);
            }
            notifyListeners(text, this);
        }

        private void notifyMyDisconnect() {
            SocketEvent e = new SocketEvent(this, SocketEvent.ON_DISCONNECT, null);
            for (Enumeration en = serverSocketListeners.elements(); en.hasMoreElements();) {
                ((SocketListener) en.nextElement()).onDisconnect(e);
            }
            notifyDisconnect(this);
        }
    }

    public GRTServer(String name, int port) {
        super(name, 0);
        server = null;
        while (server == null) {
            try {
//                Connector.
                server = (ServerSocketConnection) Connector.open("socket://:" + port);
            } catch (IOException e) {
                e.printStackTrace();
                logError("Failed to open socket!");
                server = null;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        serverSocketListeners = new Vector();
        clients = new Vector();

    }
    private ServerSocketConnection server;
    private Vector clients;
    private Vector serverSocketListeners;

    public void sendData(String data) {
        for (Enumeration en = clients.elements(); en.hasMoreElements();) {
            ((GRTSingleConnect) en.nextElement()).sendData(data);
        }
    }

    public boolean isConnected() {
        return clients.size() > 0;
    }

    protected void poll() {
        connect();
    }

    public void connect() {
        try {
            StreamConnection client = server.acceptAndOpen();
            GRTSingleConnect c = new GRTSingleConnect(
                    name + " single connect #" + clients.size(), client);
            c.startPolling();
            clients.addElement(c);
            notifyConnect(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        for (Enumeration en = clients.elements(); en.hasMoreElements();) {
            ((GRTSingleConnect) en.nextElement()).stop();
        }
    }

    public void addSocketListener(SocketListener s) {
        serverSocketListeners.addElement(s);
    }

    public void removeSocketListener(SocketListener s) {
        serverSocketListeners.removeElement(s);
    }

    private void notifyListeners(String text, GRTSocket source) {
        SocketEvent e = new SocketEvent(source, SocketEvent.ON_DATA, text);
        for (Enumeration en = serverSocketListeners.elements(); en.hasMoreElements();) {
            ((SocketListener) en.nextElement()).dataRecieved(e);
        }
    }

    private void notifyDisconnect(GRTSocket source) {
        SocketEvent e = new SocketEvent(source, SocketEvent.ON_DISCONNECT, null);
        for (Enumeration en = serverSocketListeners.elements(); en.hasMoreElements();) {
            ((SocketListener) en.nextElement()).onDisconnect(e);
        }
    }

    private void notifyConnect(GRTSocket source) {
        SocketEvent e = new SocketEvent(source, SocketEvent.ON_DISCONNECT, null);
        for (Enumeration en = serverSocketListeners.elements(); en.hasMoreElements();) {
            ((SocketListener) en.nextElement()).onConnect(e);
        }
    }
}
