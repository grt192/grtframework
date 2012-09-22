package rpc;

import core.GRTLoggedProcess;
import java.util.Enumeration;
import java.util.Vector;

public abstract class RPCConnection extends GRTLoggedProcess {

    public static final String PREFIX = RPCMessage.PREFIX;
    public static final String PREFIX_CS = RPCMessage.PREFIX_CS;
    private static final boolean USE_CHECKSUM = true;
    protected Vector listeners = new Vector();

    public RPCConnection(String name) {
        super(name);
    }
    
    public RPCConnection(String name, int sleepTime) {
        super(name, sleepTime);
    }
    
    public abstract void send(RPCMessage message);

    public void addMessageListener(RPCMessageListener l) {
        listeners.addElement(l);
    }

    public void removeMessageListener(RPCMessageListener l) {
        listeners.removeElement(l);
    }

    protected static boolean isTelemetryLine(String line) {
        if (line.length() > PREFIX.length()) {
            if (line.substring(0, PREFIX.length()).equals(PREFIX)) {
                return true;
            } else if (isValidChecksumLine(line)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isValidChecksumLine(String line) {
        int separatorIndex = line.lastIndexOf(':');
        if (separatorIndex < 0)
            return false;
        String original = line.substring(0, separatorIndex);
        int hashCode;
        try {
            hashCode = Integer.parseInt(line.substring(separatorIndex + 1));
        } catch (NumberFormatException e) {
            return false;
        }
        return original.hashCode() == hashCode;
    }
    
    private static boolean isNonChecksumLine(String line) {
        if (line.length() < PREFIX.length())
            return false;
        return line.substring(0, PREFIX.length()).equals(PREFIX);
    }

    protected static String encode(RPCMessage m) {
        // newline to flush all buffers
        if (USE_CHECKSUM) {
            return m.toStringWithChecksum() + "\n";
        }
        return m.toString() + "\n";
    }

    protected static int getKey(String line) {
        return Integer.parseInt(
                line.substring(PREFIX.length(), line.indexOf(':')), 16);
    }

    protected static String getData(String line) {
        if (isValidChecksumLine(line))
            return line.substring(line.indexOf(':') + 1, line.lastIndexOf(':'));
        if (isNonChecksumLine(line))
            return line.substring(line.indexOf(':') + 1);
        return null;
    }
    
    protected void notifyListeners(String received) {
        if (isTelemetryLine(received)) {
            RPCMessage message = decode(received);
            for (Enumeration en = listeners.elements(); en.hasMoreElements();) {
                ((RPCMessageListener) en.nextElement()).messageReceived(message);
            }

        }
    }
    
    private static RPCMessage decode(String received) {
        return new RPCMessage(getKey(received), getData(received));
    }
    
}
