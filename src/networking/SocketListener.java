package networking;

public interface SocketListener {
	
	public void onConnect(SocketEvent e);
	public void onDisconnect(SocketEvent e);
	public void dataRecieved(SocketEvent e);
}
