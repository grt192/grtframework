/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deploy;

import actuator.GRTVictor;
import controller.PrimaryDriver;
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
    private PrimaryDriver driveControl;
    private GRTDriverStation driverStation;
    private GRTRobotBase robotBase;
    
    private GRTLogger logger = GRTLogger.getLogger();

    /**
     * Initializer for the robot.
     */
    public MainRobot() {

        logger.logInfo("GRTFramework v6 starting up.");

        //Driver station components
        GRTAttack3Joystick primary = new GRTAttack3Joystick(1, 12, "primary");
        GRTAttack3Joystick secondary = new GRTAttack3Joystick(2, 12, "secondary");
        primary.startPolling();
        secondary.startPolling();
        primary.enable();
        secondary.enable();
        logger.logInfo("Joysticks initialized");

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
        logger.logInfo("Motors initialized");

        //Mechanisms
        GRTDriveTrain dt = new GRTDriveTrain(leftDT1, leftDT2, rightDT1, rightDT2);
        robotBase = new GRTRobotBase(dt, batterySensor);
        driverStation = new GRTAttack3DriverStation(primary, secondary, "driverStation");
        driverStation.enable();
        logger.logInfo("Mechanisms initialized");

        //Controllers
        driveControl = new PrimaryDriver(robotBase, driverStation, "driveControl");
        logger.logInfo("Controllers Initialized");


        addTeleopController(driveControl);

        logger.logSuccess("Ready to drive.");
    }

    public void disabled() {
        logger.logInfo("Disabling robot. Halting drivetrain");
        robotBase.tankDrive(0.0, 0.0);
    }
}
