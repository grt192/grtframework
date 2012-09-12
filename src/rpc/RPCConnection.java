package rpc;

import java.util.Vector;

public abstract class RPCConnection {
	
	public static final String PREFIX = RPCMessage.PREFIX;
	public static final String PREFIX_CS = RPCMessage.PREFIX_CS;
	
	private static final boolean USE_CHECKSUM = true;
	
	protected Vector listeners = new Vector();
	
	public abstract void send(RPCMessage message);

	public void addMessageListener(RPCMessageListener l) {
        listeners.addElement(l);
    }

    public void removeMessageListener(RPCMessageListener l) {
        listeners.removeElement(l);
    }
	
	protected static boolean isTelemetryLine(String line) {
        if (line.length() > PREFIX.length()) {
			if (line.substring(0, PREFIX.length()).equals(PREFIX))
				return true;
			else if (isValidChecksumLine(line))
				return true;
		}
		
		return false;
    }
	
	private static boolean isValidChecksumLine(String line) {
		int separatorIndex = line.lastIndexOf(':');
		String original = line.substring(0, separatorIndex);
		int hashCode;
		try {
			hashCode = Integer.parseInt(line.substring(separatorIndex + 1));
		} catch (NumberFormatException e) {
			return false;
		}
		return original.hashCode() == hashCode;
	}
	
	protected static String encode(RPCMessage m) {
        // newline to flush all buffers
		if (USE_CHECKSUM)
			return m.toStringWithChecksum() + "\n";
        return m.toString() + "\n";
    }

    protected static int getKey(String line) {
        return Integer.parseInt(
				line.substring(PREFIX.length(), line.indexOf(':')));
    }

    protected static String getData(String line) {
		if (isValidChecksumLine(line))
			return line.substring(line.indexOf(':') + 1, line.lastIndexOf(':'));
		return line.substring(line.indexOf(':') + 1);
	}

}
