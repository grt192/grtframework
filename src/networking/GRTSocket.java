package networking;

public interface GRTSocket {
	public void sendData(String data);

	public boolean isConnected();

	public void connect();

	public void disconnect();

	public void addSocketListener(SocketListener s);

	public void removeSocketListener(SocketListener s);
}
