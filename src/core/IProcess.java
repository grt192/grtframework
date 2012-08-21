package core;

/**
 * An IProcess is a component that produces a certain behavior.
 * This behavior can be enabled or disabled for a temporary pausing, or halted.
 * Halting stops the behavior from ever being re-enabled again.
 * 
 * @author ajc
 * 
 */
public interface IProcess {

    /**
     * Enables process behavior
     */
    public void enable();
    
    /**
     * Disables process behavior
     */
    public void disable();
    
    /**
     * 
     * @return true if behavior is active
     */
    public boolean isEnabled();
    
    /**
     * Terminates the behavior forever
     */
    public void halt();
    
    /**
     * 
     * @return true if the process can be enabled
     */
    public boolean isRunning();
        

}
