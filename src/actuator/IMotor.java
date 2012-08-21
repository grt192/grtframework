/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import core.IProcess;

/**
 *
 * @author ajc
 */
public interface IMotor extends IProcess{
    
    /**
     * 
     * @param speed 
     */
    public void setSpeed(double speed);
    
}
