/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.util.Vector;
import rpc.RPCConnection;
import rpc.RPCMessage;

/**
 * Singleton class that is responsible for all system logging.
 * @author agd
 */
public class GRTLogger {
    
    //PREFIXES: Prefix to every message of the three categories
    private static final String LOG_INFO_PREFIX = "[INFO]:";
    private static final String LOG_ERROR_PREFIX = "[ERROR]";
    private static final String LOG_SUCCESS_PREFIX = "[SUCCESS]";
    
    //RPC Keys for the three kinds of log messages
    private static final int LOG_INFO_KEY = 100;
    private static final int LOG_ERROR_KEY = 101;
    private static final int LOG_SUCCESS_KEY = 102;
    
    private static GRTLogger logger = new GRTLogger();
    private Vector logReceivers = new Vector();
    
    private boolean rpcEnabled = false;
    
    /*
     * Get the logger singleton that can be used to log messages to the system
     * and external RPCConnections.
     */
    public static GRTLogger getLogger(){
        return logger;
    }
    
    public void addLogListener(RPCConnection conn){
        logReceivers.addElement(conn);
    }
    
    public void removeLogListener(RPCConnection conn){
        logReceivers.removeElement(conn);
    }
    
    public void enableRPC(){
        rpcEnabled = true;
    }
    
    public void disableRPC(){
        rpcEnabled = false;
    }
    
    public void logInfo(String data){
        String message = LOG_INFO_PREFIX + data;
        System.out.println(message);
        for(int i=0; i < logReceivers.size(); i++){
            RPCConnection conn = ((RPCConnection)logReceivers.elementAt(i));
            conn.send(new RPCMessage(LOG_INFO_KEY, message));
        }
    }
    
    public void logError(String data){
        String message = LOG_ERROR_PREFIX + data;
        System.out.println(message);
        for(int i=0; i < logReceivers.size(); i++){
            RPCConnection conn = ((RPCConnection)logReceivers.elementAt(i));
            conn.send(new RPCMessage(LOG_ERROR_KEY, message));
        }
    }
    
    public void logSuccess(String data){
        String message = LOG_SUCCESS_PREFIX + data;
        System.out.println(message);
        for(int i=0; i < logReceivers.size(); i++){
            RPCConnection conn = ((RPCConnection)logReceivers.elementAt(i));
            conn.send(new RPCMessage(LOG_SUCCESS_KEY, message));
        }
    }
}
