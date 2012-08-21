/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

/**
 *
 * @author ajc
 */
public class SquareDrive implements IDriverProfile {

    public double driveSpeed(double joystickTiltPercent) {
        //save the sign: becomes -1 if original was negative, +1 if positive
        double sign = joystickTiltPercent > 0 ? +1.0 : -1.0;
        //apply sign to square to enable reverse driving
        return sign * joystickTiltPercent * joystickTiltPercent;
    }
}
