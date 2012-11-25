package logger;

import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Timer;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.io.Connector;
import rpc.RPCConnection;
import rpc.RPCMessage;

/**
 * Static class that is responsible for all system logging.
 *
 * @author agd
 */
public class GRTLogger {

    private static final DriverStationLCD dash =
            DriverStationLCD.getInstance();
    //PREFIXES: Prefix to every message of the three categories
    private static final String LOG_INFO_PREFIX = "[INFO]:";
    private static final String LOG_ERROR_PREFIX = "[ERROR]:";
    private static final String LOG_SUCCESS_PREFIX = "[SUCCESS]";
    //RPC Keys for the three kinds of log messages
    private static final int LOG_INFO_KEY = 100;
    private static final int LOG_ERROR_KEY = 101;
    private static final int LOG_SUCCESS_KEY = 102;
    private static final Vector dsBuffer = new Vector();
    private static Vector logReceivers = new Vector();
    private static boolean rpcEnabled = false;
    private static final String NEWLINE = System.getProperty("line.separator");
    
    private static boolean fileLogging = false;
    private static String[] loggingFileNames;     //File to which we log our output.
    private static FileConnection[] fileConnections;
    private static OutputStreamWriter[] fileWriters;
    public static final int FILE_INFO_LOG = 0;
    public static final int FILE_ERROR_LOG = 1;
    public static final int FILE_SUCCESS_LOG = 2;
    public static final int FILE_CONSOLIDATED_LOG = 3;

    static {
        for (int i = 0; i < 6; i++)
            dsBuffer.addElement("");
    }

    public static void addLogListener(RPCConnection conn) {
        logReceivers.addElement(conn);
    }

    public static void removeLogListener(RPCConnection conn) {
        logReceivers.removeElement(conn);
    }

    /**
     * Enable sending of RPCMessages.
     */
    public static void enableRPC() {
        rpcEnabled = true;
    }

    /**
     * Disable sending of RPCMessages.
     */
    public static void disableRPC() {
        rpcEnabled = false;
    }

    /**
     * Enable logging to a file.
     */
    public static void enableFileLogging() {
        fileLogging = true;
    }

    /**
     * Disable logging to a file.
     */
    public static void disableFileLogging() {
        fileLogging = false;
    }

    /**
     * File paths to log to.
     *
     * @param filenames absolute file paths, e.g.
     * "/logging/info_081912-001253.txt"
     */
    public static void setLoggingFiles(String[] filenames) {
        loggingFileNames = filenames;
        
        //if there are previous connections, finish writing and close them all
        if (fileWriters != null)
            for (int i = 0; i < fileWriters.length; i++)
                if (fileWriters[i] != null)
                    try {
                        fileWriters[i].close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
        if (fileConnections != null)
            for (int i = 0; i < fileConnections.length; i++)
                if (fileConnections[i] != null)
                    try {
                        fileConnections[i].close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
        
        fileConnections = new FileConnection[filenames.length];
        fileWriters = new OutputStreamWriter[filenames.length];
    }

    private static void logLineToFile(String message, int fileDescriptor) {
        /* 
         * Note: because it only prepends "file://" with 2 slashes,
         * loggingFileNames[fileDescriptor] should return an
         * absolute path (ex: /logging/info_081912-001253.txt)
         */
        String url = "file://" + loggingFileNames[fileDescriptor];
        message += NEWLINE;
        
        //if connection and writer not already created, open one
        if (fileConnections[fileDescriptor] == null ||
                !fileConnections[fileDescriptor].isOpen())
            try {
                fileConnections[fileDescriptor] =
                        (FileConnection) Connector.open(url);
                fileConnections[fileDescriptor].create();
                fileWriters[fileDescriptor] = new OutputStreamWriter(
                        fileConnections[fileDescriptor].openOutputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        //write stuff to file, and flush
        try {
            fileWriters[fileDescriptor].write(message);
            fileWriters[fileDescriptor].flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Log a general message.
     *
     * @param data message to log.
     */
    public static void logInfo(String data) {
        String message = elapsedTime() + " " + LOG_INFO_PREFIX + data;
        System.out.println(message);

        if (rpcEnabled) {
            RPCMessage e = new RPCMessage(LOG_INFO_KEY, message);
            for (Enumeration en = logReceivers.elements(); en.hasMoreElements();)
                ((RPCConnection) en.nextElement()).send(e);
        }
        if (fileLogging) {
            logLineToFile(message, FILE_INFO_LOG);
            logLineToFile(message, FILE_CONSOLIDATED_LOG);
        }
    }

    /**
     * Logs a general message, and displays it on the driver station.
     *
     * @param data message to log
     */
    public static void dsLogInfo(String data) {
        dsPrintln(LOG_INFO_PREFIX + data);
        logInfo(data);
    }

    /**
     * Log an error message.
     *
     * @param data message to log.
     */
    public static void logError(String data) {
        String message = elapsedTime() + " " + LOG_ERROR_PREFIX + data;
        System.out.println(message);

        if (rpcEnabled) {
            RPCMessage e = new RPCMessage(LOG_ERROR_KEY, message);
            for (Enumeration en = logReceivers.elements(); en.hasMoreElements();)
                ((RPCConnection) en.nextElement()).send(e);
        }
        if (fileLogging) {
            logLineToFile(message, FILE_ERROR_LOG);
            logLineToFile(message, FILE_CONSOLIDATED_LOG);
        }
    }

    /**
     * Logs an error message, and displays it on the driver station.
     *
     * @param data message to log
     */
    public static void dsLogError(String data) {
        dsPrintln(LOG_ERROR_PREFIX + data);
        logError(data);
    }

    /**
     * Log a success message.
     *
     * @param data message to log.
     */
    public static void logSuccess(String data) {
        String message = elapsedTime() + " " + LOG_SUCCESS_PREFIX + data;
        System.out.println(message);

        if (rpcEnabled) {
            RPCMessage e = new RPCMessage(LOG_SUCCESS_KEY, message);
            for (Enumeration en = logReceivers.elements(); en.hasMoreElements();)
                ((RPCConnection) en.nextElement()).send(e);
        }
        if (fileLogging) {
            logLineToFile(message, FILE_SUCCESS_LOG);
            logLineToFile(message, FILE_CONSOLIDATED_LOG);
        }
    }

    /**
     * Logs a success message, and displays it on the driver station.
     *
     * @param data message to log
     */
    public static void dsLogSuccess(String data) {
        dsPrintln(LOG_ERROR_PREFIX + data);
        logSuccess(data);
    }

    private static String elapsedTime() {
        StringBuffer s = new StringBuffer();

        int secElapsed = (int) Timer.getFPGATimestamp();
        int minElapsed = secElapsed / 60;
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

    private static void dsPrintln(String data) {
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