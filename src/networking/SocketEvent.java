package networking;

public class SocketEvent {
	public static final int ON_DATA = 0;
	public static final int ON_CONNECT = 1;
	public static final int ON_DISCONNECT = 2;
	private GRTSocket source;
	private int id;
	private String data;
	
	public SocketEvent(GRTSocket source, int id, String data) {
		super();
		this.source = source;
		this.id = id;
		this.data = data;
	}
	public GRTSocket getSource() {
		return source;
	}
	public int getId() {
		return id;
	}
	public String getData() {
		return data;
	}
	
	
}
