/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanism;

import actuator.IMotor;

/**
 * Standard 4 motor drivetrain.
 * @author ajc
 */
public class GRTDriveTrain {

    private final IMotor leftFront;
    private final IMotor leftBack;
    private final IMotor rightFront;
    private final IMotor rightBack;

    /**
     * 
     * @param leftFront left front motor
     * @param leftBack left back motor
     * @param rightFront right front motor
     * @param rightBack right back motor
     */
    public GRTDriveTrain(IMotor leftFront, IMotor leftBack,
            IMotor rightFront, IMotor rightBack) {
                
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
