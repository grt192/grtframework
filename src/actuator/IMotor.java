/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import core.Actuator;

/**
 *
 * @author ajc
 */
public abstract class IMotor extends Actuator {
    
    public IMotor(String name){
        super(name);
    }
    
    /**
     * 
     * @param speed 
     */
    public abstract void setSpeed(double speed);
    
}
