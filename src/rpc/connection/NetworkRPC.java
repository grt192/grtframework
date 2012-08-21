/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc.connection;

import networking.GRTServer;
import networking.SocketEvent;
import networking.SocketListener;
import java.util.Enumeration;
import java.util.Vector;
import rpc.RPCConnection;
import rpc.RPCMessage;
import rpc.RPCMessageListener;

/**
 * NetworkRPC provides an Internet RPC connection. It currently receives 
 * messages from any connecting host, and sends messages to all connected hosts
 * 
 * @author ajc
 */
public class NetworkRPC implements RPCConnection, SocketListener {

    private GRTServer connection;
    private Vector listeners = new Vector();

    /**
     * Opens a new Network RPC connection and starts it.
     * @param port 
     */
    public NetworkRPC(int port) {
        connection = new GRTServer(port);
        start();
    }

    
    private void start(){
        connection.addSocketListener(this);
        connection.start();
    }

    //TODO enable sending to a single host
    public void send(RPCMessage message) {
        connection.sendData(encode(message));
    }

    public void addMessageListener(RPCMessageListener l) {
        listeners.addElement(l);
    }

    public void removeMessageListener(RPCMessageListener l) {
        listeners.removeElement(l);
    }

    private void notifyListeners(String received) {
        if (isTelemetryLine(received)) {
            // RPCMessage message = new RPCMessage(getKey(received),
            // getData(received));
            RPCMessage message = decode(received);
//             System.out.println(message);
            // TODO only notify specific 'keyed' listeners
            for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
                ((RPCMessageListener) e.nextElement()).messageReceived(message);
            }

        }
    }

    private static String encode(RPCMessage m) {
        // newline to flush all buffers
        return ("USB" + m.getKey() + ":" + m.getData() + "\n");
    }

    private static RPCMessage decode(String received) {
        return new RPCMessage(getKey(received), getData(received));
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

    public void onConnect(SocketEvent e) { //TODO
    }

    public void onDisconnect(SocketEvent e) { //TODO
    }

    public void dataRecieved(SocketEvent e) {
//        System.out.println("Data received: " + e.getData());
        notifyListeners(e.getData());
    }
}
