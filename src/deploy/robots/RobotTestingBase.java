package deploy.robots;

import actuator.GRTSolenoid;
import actuator.GRTVictor;
import controller.ShiftingDriveController;
import deploy.GRTRobot;
import edu.wpi.first.wpilibj.Compressor;
import java.util.Calendar;
import java.util.TimeZone;
import logger.GRTLogger;
import mechanism.GRTRobotBase;
import mechanism.ShiftingDriveTrain;
import sensor.GRTAttack3Joystick;
import sensor.GRTBatterySensor;
import sensor.base.*;

/**
 * Constructor for the main robot. Put all robot components here.
 *
 * @author ajc
 */
public class RobotTestingBase extends GRTRobot {

    //Teleop Controllers
    private ShiftingDriveController shiftingControl;
    private GRTDriverStation driverStation;
    private GRTRobotBase robotBase;

    /**
     * Initializer for the robot.
     */
    public RobotTestingBase() {

        //Init the logging files.
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        String dateStr = "" + cal.get(Calendar.YEAR) + "-" + cal.get(
                Calendar.MONTH) + 1 + "T" + cal.get(Calendar.HOUR_OF_DAY) + cal.
                get(Calendar.MINUTE) + cal.get(Calendar.SECOND);
        GRTLogger.logInfo("Date string = " + dateStr);
        String loggingFiles[] = new String[]{"/logs/" + dateStr + "_info.log",
            "/logs/" + dateStr + "_success.log", "/logs/" + dateStr
            + "_error.log", "/logs/" + dateStr + "_all.log"};
        GRTLogger.setLoggingFiles(loggingFiles);
        //GRTLogger.enableFileLogging();

        GRTLogger.logInfo("GRTFramework v6 starting up.");

        //Driver station components
        GRTAttack3Joystick leftJoystick =
                new GRTAttack3Joystick(2, 12, "leftStick");
        GRTAttack3Joystick rightJoystick =
                new GRTAttack3Joystick(1, 12, "rightStick");
        leftJoystick.startPolling();
        rightJoystick.startPolling();
        leftJoystick.enable();
        rightJoystick.enable();
        GRTLogger.logInfo("Joysticks initialized");

        //Battery Sensor
        GRTBatterySensor batterySensor = new GRTBatterySensor(10, "battery");
        batterySensor.startPolling();
        batterySensor.enable();
		
	//Shifter solenoids
	GRTSolenoid leftShifter = new GRTSolenoid(2, "leftShifter");
	GRTSolenoid rightShifter = new GRTSolenoid(1, "rightShifter");
	leftShifter.enable();
        rightShifter.enable();
	
	//Compressor
	Compressor compressor = new Compressor(14, 1);
	compressor.start();
	
        // PWM outputs
        GRTVictor leftDT1 = new GRTVictor(1, "leftDT1");
        GRTVictor leftDT2 = new GRTVictor(2, "leftDT2");
        GRTVictor rightDT1 = new GRTVictor(9, "rightDT1");
        GRTVictor rightDT2 = new GRTVictor(10, "rightDT2");
        leftDT1.enable();
        leftDT2.enable();
        rightDT1.enable();
        rightDT2.enable();
        GRTLogger.logInfo("Motors initialized");

        //Mechanisms
        ShiftingDriveTrain dt = new ShiftingDriveTrain(leftDT1, leftDT2,
                rightDT1, rightDT2,
                leftShifter, rightShifter);
        
        robotBase = new GRTRobotBase(dt, batterySensor);
        driverStation = new GRTAttack3DriverStation(leftJoystick, rightJoystick,
                "driverStation");
        driverStation.enable();
        GRTLogger.logInfo("Mechanisms initialized");

        //Controllers
        shiftingControl =
                new ShiftingDriveController(dt, driverStation, "driveControl");
        GRTLogger.logInfo("Controllers Initialized");
        driverStation.addDrivingListener(shiftingControl);
		driverStation.addShiftListener(shiftingControl);
        
        addTeleopController(shiftingControl);

        GRTLogger.logSuccess("Ready to drive.");
    }

    public void disabled() {
        GRTLogger.logInfo("Disabling robot. Halting drivetrain");
        robotBase.tankDrive(0.0, 0.0);
    }
}
