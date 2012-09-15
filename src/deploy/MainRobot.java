/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deploy;

import actuator.GRTVictor;
import controller.PrimaryDriver;
import mechanism.GRTDriveTrain;
import mechanism.GRTRobotBase;
import rpc.connection.NetworkRPC;
import rpc.telemetry.SensorLogger;
import sensor.GRTAttack3Joystick;
import sensor.GRTBatterySensor;
import sensor.base.*;

/**
 *
 * @author ajc
 */
public class MainRobot extends GRTRobot {

   

    
    //Global Controllers
    private SensorLogger batteryLogger;
    //Teleop Controllers
    private PrimaryDriver driveControl;
    private GRTDriverStation driverStation;
    private GRTRobotBase robotBase;
    

    public MainRobot() {

        System.out.println("Running grtframeworkv6");

        //RPC Connection
        NetworkRPC rpcConn = new NetworkRPC(180);

        //Driver station components
        GRTAttack3Joystick primary = new GRTAttack3Joystick(1, 12, "primary");
        GRTAttack3Joystick secondary = new GRTAttack3Joystick(2, 12, "secondary");
        primary.start();
        secondary.start();
        primary.enable();
        secondary.enable();
        System.out.println("Joysticks initialized");

        //Battery Sensor
        GRTBatterySensor batterySensor = new GRTBatterySensor(10, "battery");
        batterySensor.start();
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
        System.out.println("Motors initialized");

        //Mechanisms
        GRTDriveTrain dt = new GRTDriveTrain(leftDT1, leftDT2, rightDT1, rightDT2);
        robotBase = new GRTRobotBase(dt, batterySensor);
        driverStation = new GRTAttack3DriverStation(primary, secondary, "driverStation");
        driverStation.enable();
        System.out.println("Mechanisms initialized");

        //Controllers
        driveControl = new PrimaryDriver(robotBase, driverStation, "driveControl");
        batteryLogger = new SensorLogger(batterySensor, rpcConn, new int[]{23}, "batterylogger");
        System.out.println("Controllers Initialized");
        
        
        addTeleopController(driveControl);
    }
}
