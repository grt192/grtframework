/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import rpc.RPCConnection;
import rpc.RPCMessage;

/**
 * Singleton class that is responsible for all system logging.
 *
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

    /**
     * Get the logger singleton that can be used to log messages to the system
     * and external RPCConnections.
     * 
     * @return logger instance
     */
    public static GRTLogger getLogger() {
        return logger;
    }

    public void addLogListener(RPCConnection conn) {
        logReceivers.addElement(conn);
    }

    public void removeLogListener(RPCConnection conn) {
        logReceivers.removeElement(conn);
    }

    /**
     * Enable sending of RPCMessages.
     */
    public void enableRPC() {
        rpcEnabled = true;
    }

    /**
     * Disable sending of RPCMessages.
     */
    public void disableRPC() {
        rpcEnabled = false;
    }

    /**
     * Log a general message.
     *
     * @param data message to log.
     */
    public void logInfo(String data) {
        String message = formattedTime() + ' ' + LOG_INFO_PREFIX + data;
        System.out.println(message);

        if (rpcEnabled) {
            RPCMessage e = new RPCMessage(LOG_INFO_KEY, message);
            for (Enumeration en = logReceivers.elements(); en.hasMoreElements();) {
                ((RPCConnection) en.nextElement()).send(e);
            }
        }
    }

    
    /**
     * Log an error message.
     *
     * @param data message to log.
     */
    public void logError(String data) {
        String message = formattedTime() + ' ' + LOG_ERROR_PREFIX + data;
        System.out.println(message);
        if (rpcEnabled) {
            RPCMessage e = new RPCMessage(LOG_ERROR_KEY, message);
            for (Enumeration en = logReceivers.elements(); en.hasMoreElements();) {
                ((RPCConnection) en.nextElement()).send(e);
            }
        }
    }

    /**
     * Log a success message.
     *
     * @param data message to log.
     */
    public void logSuccess(String data) {
        String message = formattedTime() + ' ' + LOG_SUCCESS_PREFIX + data;
        System.out.println(message);
        if (rpcEnabled) {
            RPCMessage e = new RPCMessage(LOG_SUCCESS_KEY, message);
            for (Enumeration en = logReceivers.elements(); en.hasMoreElements();) {
                ((RPCConnection) en.nextElement()).send(e);
            }
        }
    }

    private String formattedTime() {
        return new Date().toString();
    }
}
