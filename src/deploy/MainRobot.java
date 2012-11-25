package deploy;

import actuator.GRTVictor;
import controller.PrimaryDriveController;
import java.util.Calendar;
import java.util.TimeZone;
import logger.GRTLogger;
import mechanism.GRTDriveTrain;
import mechanism.GRTRobotBase;
import sensor.GRTAttack3Joystick;
import sensor.GRTBatterySensor;
import sensor.base.*;

/**
 * Constructor for the main robot. Put all robot components here.
 *
 * @author ajc
 */
public class MainRobot extends GRTRobot {

    //Teleop Controllers
    private PrimaryDriveController driveControl;
    private GRTDriverStation driverStation;
    private GRTRobotBase robotBase;

    /**
     * Initializer for the robot.
     */
    public MainRobot() {

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
        GRTLogger.enableFileLogging();

        GRTLogger.logInfo("GRTFramework v6 starting up.");

        //Driver station components
        GRTAttack3Joystick primary = new GRTAttack3Joystick(1, 12, "primary");
        GRTAttack3Joystick secondary =
                new GRTAttack3Joystick(2, 12, "secondary");
        primary.startPolling();
        secondary.startPolling();
        primary.enable();
        secondary.enable();
        GRTLogger.logInfo("Joysticks initialized");

        //Battery Sensor
        GRTBatterySensor batterySensor = new GRTBatterySensor(10, "battery");
        batterySensor.startPolling();
        batterySensor.enable();

        // PWM outputs
        GRTVictor leftDT1 = new GRTVictor(2, "leftDT1");
        GRTVictor leftDT2 = new GRTVictor(3, "leftDT2");
        GRTVictor rightDT1 = new GRTVictor(8, "rightDT1");
        GRTVictor rightDT2 = new GRTVictor(9, "rightDT2");
        leftDT1.enable();
        leftDT2.enable();
        rightDT1.enable();
        rightDT2.enable();
        GRTLogger.logInfo("Motors initialized");

        //Mechanisms
        GRTDriveTrain dt = new GRTDriveTrain(leftDT1, leftDT2, rightDT1,
                rightDT2);
        robotBase = new GRTRobotBase(dt, batterySensor);
        driverStation = new GRTAttack3DriverStation(primary, secondary,
                "driverStation");
        driverStation.enable();
        GRTLogger.logInfo("Mechanisms initialized");

        //Controllers
        driveControl = new PrimaryDriveController(robotBase, driverStation,
                "driveControl");
        GRTLogger.logInfo("Controllers Initialized");


        addTeleopController(driveControl);

        GRTLogger.logSuccess("Ready to drive.");
    }

    public void disabled() {
        GRTLogger.logInfo("Disabling robot. Halting drivetrain");
        robotBase.tankDrive(0.0, 0.0);
    }
}
