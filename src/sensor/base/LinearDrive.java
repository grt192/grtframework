/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

/**
 *
 * @author ajc
 */
public class LinearDrive implements IDriverProfile{

    public double driveSpeed(double joystickTiltPercent) {
        return joystickTiltPercent;
    }
    
}
