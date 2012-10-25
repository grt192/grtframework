package logger;

import edu.wpi.first.wpilibj.DriverStationLCD;
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

    private DriverStationLCD dash = DriverStationLCD.getInstance();
    //PREFIXES: Prefix to every message of the three categories
    private static final String LOG_INFO_PREFIX = "[INFO]:";
    private static final String LOG_ERROR_PREFIX = "[ERROR]:";
    private static final String LOG_SUCCESS_PREFIX = "[SUCCESS]";
    //RPC Keys for the three kinds of log messages
    private static final int LOG_INFO_KEY = 100;
    private static final int LOG_ERROR_KEY = 101;
    private static final int LOG_SUCCESS_KEY = 102;
    private Vector dsBuffer = new Vector();
    private static GRTLogger logger = new GRTLogger();
    private Vector logReceivers = new Vector();
    private boolean rpcEnabled = false;
    private long startTimeMillis = System.currentTimeMillis();

    private GRTLogger() {
        for (int i = 0; i < 6; i++)
            dsBuffer.addElement("");
    }

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
        String message = elapsedTime() + " " + LOG_INFO_PREFIX + data;
        System.out.println(message);

        if (rpcEnabled) {
            RPCMessage e = new RPCMessage(LOG_INFO_KEY, message);
            for (Enumeration en = logReceivers.elements(); en.hasMoreElements();)
                ((RPCConnection) en.nextElement()).send(e);
        }
    }

    /**
     * Logs a general message, and displays it on the driver station.
     *
     * @param data message to log
     */
    public void dsLogInfo(String data) {
        dsPrintln(LOG_INFO_PREFIX + data);
        logInfo(data);
    }

    /**
     * Log an error message.
     *
     * @param data message to log.
     */
    public void logError(String data) {
        String message = elapsedTime() + " " + LOG_ERROR_PREFIX + data;
        System.out.println(message);

        if (rpcEnabled) {
            RPCMessage e = new RPCMessage(LOG_ERROR_KEY, message);
            for (Enumeration en = logReceivers.elements(); en.hasMoreElements();)
                ((RPCConnection) en.nextElement()).send(e);
        }
    }

    /**
     * Logs an error message, and displays it on the driver station.
     *
     * @param data message to log
     */
    public void dsLogError(String data) {
        dsPrintln(LOG_ERROR_PREFIX + data);
        logError(data);
    }

    /**
     * Log a success message.
     *
     * @param data message to log.
     */
    public void logSuccess(String data) {
        String message = elapsedTime() + " " + LOG_SUCCESS_PREFIX + data;
        System.out.println(message);

        if (rpcEnabled) {
            RPCMessage e = new RPCMessage(LOG_SUCCESS_KEY, message);
            for (Enumeration en = logReceivers.elements(); en.hasMoreElements();)
                ((RPCConnection) en.nextElement()).send(e);
        }
    }

    /**
     * Logs a success message, and displays it on the driver station.
     *
     * @param data message to log
     */
    public void dsLogSuccess(String data) {
        dsPrintln(LOG_ERROR_PREFIX + data);
        logSuccess(data);
    }

    private String elapsedTime() {
        StringBuffer s = new StringBuffer();
        
        long secElapsed = (System.currentTimeMillis() - startTimeMillis) / 1000;
        int minElapsed = (int) secElapsed / 60;
        int hrElapsed = minElapsed / 60;
        
        if (hrElapsed < 10)
            s.append("0");
        s.append(hrElapsed).append(":");
        
        if (minElapsed % 60 < 10)
            s.append("0");
        s.append(minElapsed % 60).append(":");
        
        if (secElapsed % 60 < 10)
            s.append("0");
        s.append(secElapsed % 60);
        
        return s.toString();
    }

    private void dsPrintln(String data) {
        dsBuffer.addElement(data);
        dsBuffer.removeElementAt(0);

        dash.println(DriverStationLCD.Line.kMain6, 1,
                (String) dsBuffer.elementAt(5));
        dash.println(DriverStationLCD.Line.kUser6, 1,
                (String) dsBuffer.elementAt(4));
        dash.println(DriverStationLCD.Line.kUser5, 1,
                (String) dsBuffer.elementAt(3));
        dash.println(DriverStationLCD.Line.kUser4, 1,
                (String) dsBuffer.elementAt(2));
        dash.println(DriverStationLCD.Line.kUser3, 1,
                (String) dsBuffer.elementAt(1));
        dash.println(DriverStationLCD.Line.kUser2, 1,
                (String) dsBuffer.elementAt(0));

        dash.updateLCD();
    }
}
