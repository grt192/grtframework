package core;

/**
 * A GRTLoggedProcess is a controllable process. It can be initiated/terminated.
 *
 * When a GRTLoggedProcess is constructed, it is neither run nor enabled.
 *
 * @author ajc
 *
 */
public abstract class GRTLoggedProcess extends Thread {
    
    protected final String name;
    protected boolean enabled = false;
    protected boolean running = true;
    
    /**
     * How long between executions of poll() to pause for, in milliseconds.
     */
    protected int sleepTime = 0;

    public GRTLoggedProcess(String name) {
        this.name = name;
    }

    /*
     * The run method of GRTLoggedProcess, by default, performs an action at
     * some interval, but only if poll() is overridden and pollTime is
     * nonnegative.
     *
     * Instead of calling run(), call start() instead.
     */
    public void run() {
        running = true;
        while (running && sleepTime >= 0) {

            //only poll, and thus only send events, if enabled
            if (enabled)
                poll();

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * In order to poll and have meaningful effects, poll() must be overridden.
     */
    private void poll() {
    }

    /**
     * Logs a message.
     *
     * @param message message to log
     */
    protected void log(String message) {
        System.out.println(toString() + "\t" + message);
    }

    /**
     * Logs in format: "[[ClassName:Id]]	name	message"
     *
     * @param name name to log.
     * @param message message to log.
     */
    protected void log(String name, String message) {
        System.out.println(toString() + "\t" + name + "\t" + message);
    }

    /**
     * Logs in format: "[[ClassName:Id]]	data"
     *
     * @param data data to log.
     */
    protected void log(double data) {
        System.out.println(toString() + "\t" + data);

    }

    /**
     * Logs in format: "[[ClassName:Id]]	name	data"
     *
     * @param name name to log.
     * @param data data to log.
     */
    protected void log(String name, double data) {
        System.out.println(toString() + "\t" + name + "\t" + data);
    }

    /**
     * Enables actions of this process.
     */
    public void enable() {
        enabled = true;
    }

    /**
     * Disables actions of this process
     */
    public void disable() {
        enabled = false;
    }

    /**
     * Returns whether or not actions of this process are enabled.
     *
     * @return true if enabled, false otherwise.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Stops execution of this process.
     */
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
     * @return name of this process.
     */
    public String getID() {
        return name;
    }

    /*
     * toString method, returns loggable string in the format
     * [[ClassName:Id]].
     * 
     * @return loggable string.
     */
    public String toString() {
        return "[[" + getClass().getName() + ":" + getID();
    }
}
