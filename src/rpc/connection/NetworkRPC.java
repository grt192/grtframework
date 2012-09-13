/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc.connection;

import java.util.Enumeration;
import networking.GRTServer;
import networking.SocketEvent;
import networking.SocketListener;
import rpc.RPCConnection;
import rpc.RPCMessage;
import rpc.RPCMessageListener;

/**
 * NetworkRPC provides an Internet RPC connection. It currently receives 
 * messages from any connecting host, and sends messages to all connected hosts
 * 
 * @author ajc
 */
public class NetworkRPC extends RPCConnection implements SocketListener {

    private GRTServer connection;

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

    private static RPCMessage decode(String received) {
        return new RPCMessage(getKey(received), getData(received));
    }

    public void onConnect(SocketEvent e) { //TODO
    }

    public void onDisconnect(SocketEvent e) { //TODO
    }

    public void dataRecieved(SocketEvent e) {
        notifyListeners(e.getData());
    }
}
