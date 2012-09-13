package core;

/**
 * An actuator is a driver for low level hardware that directly influences the 
 * physical world. Actuators can perform actions.
 * @author ajc
 */
public abstract class Actuator extends GRTLoggedProcess {
    
    /**
     * Creates an actuator with some name.
     * 
     * @param name name of actuator
     */
    public Actuator(String name){
        super(name);
    }
    
    /**
     * Performs an action. Actuator must be enabled for this to succeed.
     * @param command command to sent to actuator
     */
    public abstract void executeCommand(double command);
    
}
