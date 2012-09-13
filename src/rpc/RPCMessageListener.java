package rpc;

/**
 * Generic listener for RPC messages
 * 
 * @author ajc
 */
public interface RPCMessageListener {

	public void messageReceived(RPCMessage message);
}
