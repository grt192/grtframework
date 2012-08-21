package rpc;

public class RPCMessage {

	private final int key;
	private final double data;

	public RPCMessage(int key, double data) {
		this.key = key;
		this.data = data;
	}

	public int getKey() {
		return key;
	}

	public double getData() {
		return data;
	}

	public String toString() {
		return "RPCMessage:" + key + ":" + data;
	}

}
