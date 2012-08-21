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
     * 
     * @param message
     */
    protected void log(String message) {
        System.out.println(toString() + "\t" + message);
    }

    /**
     * Logs in format: "[[ClassName:Id]]    @name   message
     * @param name
     * @param message
     */
    protected void log(String name, String message) {
        System.out.println(toString() + "\t" + name + "\t" + message); 
    }

    /**
     * 
     * @param data
     */
    protected void log(double data) {
        System.out.println(toString() + "\t" + data);

    }

    /**
     * 
     * @param name
     * @param data
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
     * Name
     * 
     * @return
     */
    public String getID() {
        return name;
    }
    
    /*
     * To string method, returns loggable string in the formate
     * [[ClassName:Id]]
     */
    public String toString(){
        return "[[" + getClass().getName() + ":" + getID();
    }
}
