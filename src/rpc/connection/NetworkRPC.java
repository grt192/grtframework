/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc.connection;

import networking.GRTServer;
import networking.SocketEvent;
import networking.SocketListener;
import rpc.RPCConnection;
import rpc.RPCMessage;

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
     * 
     * @param name name of connection
     * @param port connection port
     */
    public NetworkRPC(String name, int port) {
        super(name);
        connection = new GRTServer(name + "-server", port);
        initConnection();
    }

    private void initConnection(){
        connection.addSocketListener(this);
        connection.startPolling();
    }

    //TODO enable sending to a single host
    /**
     * Sends an RPCMessage
     * 
     * @param message message to send
     */
    public void send(RPCMessage message) {
        connection.sendData(encode(message));
    }

    public void onConnect(SocketEvent e) { //TODO
    }

    public void onDisconnect(SocketEvent e) { //TODO
    }

    public void dataRecieved(SocketEvent e) {
        notifyListeners(e.getData());
    }
}
