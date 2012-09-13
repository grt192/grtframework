/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import core.Actuator;

/**
 * Generic motor.
 *
 * @author ajc
 */
public abstract class Motor extends Actuator {

    public Motor(String name) {
        super(name);
    }

    /**
     * Sets the speed of this motor
     *
     * @param speed speed of motor (-1 to 1)
     */
    public abstract void setSpeed(double speed);
}
