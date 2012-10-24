package rpc;

/**
 * Generic listener for RPC messages
 *
 * @author ajc
 */
public interface RPCMessageListener {

    /**
     * Do this when a message is received
     *
     * @param message message received
     */
    public void messageReceived(RPCMessage message);
}
