package rpc;

public interface RPCConnection {

	public void send(RPCMessage message);

	public void addMessageListener(RPCMessageListener l);

	public void removeMessageListener(RPCMessageListener l);

}
