package mechanism;

import actuator.Motor;
import logger.GRTLogger;

/**
 * Standard 4 motor drivetrain.
 *
 * @author ajc
 */
public class GRTDriveTrain {

    private final Motor leftFront;
    private final Motor leftBack;
    private final Motor rightFront;
    private final Motor rightBack;
    private double leftFrontSF = 1;
    private double leftBackSF = 1;
    private double rightFrontSF = -1;
    private double rightBackSF = -1;

    /**
     * Constructs a new drivetrain.
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
     * Depending on robot orientation, drivetrain configuration, controller
     * configuration, motors on different parts of the drivetrain may need to be
     * driven in differing directions. These "scale factor" numbers change the
     * magnitude and/or direction of the different motors; they are multipliers
     * for the speed fed to the motors.
     *
     * @param leftFrontSF left front scale factor.
     * @param leftBackSF left back scale factor.
     * @param rightFrontSF right front scale factor.
     * @param rightBackSF right back scale factor.
     */
    public void setScaleFactors(double leftFrontSF, double leftBackSF,
            double rightFrontSF, double rightBackSF) {
        this.leftFrontSF = leftFrontSF;
        this.leftBackSF = leftBackSF;
        this.rightFrontSF = rightFrontSF;
        this.rightBackSF = rightBackSF;
    }

    /**
     * TankDrive uses differential steering.
     *
     * @param leftVelocity left drivetrain velocity
     * @param rightVelocity right drivetrain velocity
     */
    public void tankDrive(double leftVelocity, double rightVelocity) {
        GRTLogger.logInfo("" + leftVelocity);
        leftFront.setSpeed(leftVelocity * leftFrontSF);
        leftBack.setSpeed(leftVelocity * leftBackSF);
        rightFront.setSpeed(rightVelocity * rightFrontSF);
        rightBack.setSpeed(rightVelocity * rightBackSF);
    }
}
