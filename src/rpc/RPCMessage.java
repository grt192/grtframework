package rpc;

/**
 * Generates a message with an integer key and data
 *
 * @author ajc
 */
public class RPCMessage {

	private final int key;
	private final String data;
	
	public static final String PREFIX = "RPC";
	public static final String PREFIX_CS = "PPC";

	public RPCMessage(int key, String data) {
		this.key = key;
		if (data.indexOf(':') >= 0)
			throw new IllegalArgumentException(
					"RPCMessage data string cannot contain semicolons");
		this.data = data;
	}
	
	public RPCMessage(int key, double data) {
		this.key = key;
		this.data = Double.toString(data);
	}
	
	public int getKey() {
		return key;
	}
	
	public boolean isDoubleData() {
		try {
			Double.parseDouble(data);
			return true;
		}
		catch (NumberFormatException e){
			return false;
		}
	}
	
	public String getData() {
		return data;
	}

	public String toString() {
		return PREFIX + key + ":" + data;
	}
	
	public String toStringWithChecksum() {
		String s = PREFIX_CS + key + ":" + data;
		return s + ":" + (s.hashCode() << 16);
	}

}
