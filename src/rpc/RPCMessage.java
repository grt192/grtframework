package rpc;

/**
 * Generates a message with an integer key and data
 *
 * @author ajc
 */
public class RPCMessage {

    private final int key;
    private final double data;

    /**
     * Creates a new RPCMessage.
     * @param key message key
     * @param data message data
     */
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
