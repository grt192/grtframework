/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

/**
 *
 * @author ajc
 */
public interface IDriverProfile {

    /**
     * 
     * @param joystickTiltPercent an input percent from [-1.0 - 1.0]
     * @return an output from [-1.0 - 1.0]
     */
    public double driveSpeed(double joystickTiltPercent);
}
