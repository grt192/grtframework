/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import core.IProcess;

/**
 * Generic interface for a motor.
 * @author ajc
 */
public interface IMotor extends IProcess{
    
    /**
     * Sets the speed of this motor
     * @param speed speed of motor (-1 to 1)
     */
    public void setSpeed(double speed);
    
}
