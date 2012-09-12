package core;

/**
 * A GRTLoggedProcess is a controllable process. It can be initiated/terminated.
 * 
 * When a GRTLoggedProcess is constructed, it is immediately run, but not enabled.
 * 
 * @author ajc
 * 
 */
public abstract class GRTLoggedProcess extends Thread implements IProcess {

    protected final String name;
    protected boolean enabled = false;
    protected boolean running = true;

    public GRTLoggedProcess(String name) {
        this.name = name;
    }

    /**
     * Logs a message.
     * @param message message to log
     */
    protected void log(String message) {
        System.out.println(toString() + "\t" + message);
    }

    /**
     * Logs in format: "[[ClassName:Id]]	name	message"
     * @param name name to log
     * @param message message to log
     */
    protected void log(String name, String message) {
        System.out.println(toString() + "\t" + name + "\t" + message); 
    }

    /**
     * Logs in format: "[[ClassName:Id]]	data"
     * @param data data to log
     */
    protected void log(double data) {
        System.out.println(toString() + "\t" + data);

    }

    /**
     * Logs in format: "[[ClassName:Id]]	name	data"
     * @param name name to log
     * @param data data to log
     */
    protected void log(String name, double data) {
        System.out.println(toString() + "\t" + name + "\t" + data);
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void halt() {
        running = false;
        disable(); 
    }

    public boolean isRunning() {
        return running;
    }

    /**
     * Returns the name of this process
     * 
     * @return name
     */
    public String getID() {
        return name;
    }
    
    /*
     * toString method, returns loggable string in the format
     * [[ClassName:Id]]
	 * 
	 * @return loggable string
     */
    public String toString(){
        return "[[" + getClass().getName() + ":" + getID();
    }
}
