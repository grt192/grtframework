/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanism;

import actuator.Motor;

/**
 * Standard 4 motor drivetrain.
 * @author ajc
 */
public class GRTDriveTrain {

    private final Motor leftFront;
    private final Motor leftBack;
    private final Motor rightFront;
    private final Motor rightBack;

    /**
     * 
     * @param leftFront left front motor
     * @param leftBack left back motor
     * @param rightFront right front motor
     * @param rightBack right back motor
     */
    public GRTDriveTrain(Motor leftFront, Motor leftBack,
            Motor rightFront, Motor rightBack) {
                
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        this.rightFront = rightFront;
        this.rightBack = rightBack;
    }

    /**
     * TankDrive uses differential steering.
     * @param leftVelocity
     * @param rightVelocity
     */
    public void tankDrive(double leftVelocity, double rightVelocity) {
        leftFront.setSpeed(-leftVelocity);
        leftBack.setSpeed(-leftVelocity);
        rightFront.setSpeed(-rightVelocity);
        rightBack.setSpeed(-rightVelocity);
    }
}
