package actuator;

import core.Actuator;

/**
 * Generic motor.
 *
 * @author ajc
 */
public abstract class Motor extends Actuator {

    /**
     * Instantiates a new motor.
     *
     * @param name name of motor
     */
    public Motor(String name) {
        super(name);
    }

    /**
     * Sets the speed of this motor.
     *
     * @param speed speed of motor (-1 to 1)
     */
    public abstract void setSpeed(double speed);
    
    /**
     * Sets the speed of this motor. Identical to setSpeed(command).
     * 
     * @param command speed of motor (-1 to 1)
     */
    public void executeCommand(double command){
        setSpeed(command);
    }
}
