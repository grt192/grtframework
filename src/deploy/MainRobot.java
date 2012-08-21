/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deploy;

import actuator.GRTVictor;
import balancer.RobotTiltAccel;
import controller.PrimaryDriver;
import mechanism.GRTDriveTrain;
import sensor.base.GRTXboxDriverStation;
import mechanism.GRTRobotBase;
import sensor.base.LinearDrive;
import sensor.base.SquareDrive;
import sensor.base.IDriverProfile;
import rpc.connection.NetworkRPC;
import rpc.telemetry.SensorLogger;
import sensor.ADXL345DigitalAccelerometer;
import sensor.GRTADXL345;
import sensor.GRTAttack3Joystick;
import sensor.GRTBatterySensor;
import sensor.GRTEncoder;
import sensor.GRTGyro;
import sensor.GRTSwitch;
import sensor.GRTXBoxJoystick;
import sensor.base.*;

/**
 *
 * @author ajc
 */
public class MainRobot extends GRTRobot {

    /**
     * Buttons which refer to enumerated driver curve profiles.
     * First index refers to Linear drive
     * Second index refers to Square drive
     */
    public static final int[] DRIVER_PROFILE_KEYS = new int[] {1,2};
    public static final IDriverProfile[] DRIVER_PROFILES = new IDriverProfile[] {new LinearDrive(), new SquareDrive()};
    

    
    //Global Controllers
    private SensorLogger batteryLogger;
    //Teleop Controllers
    private PrimaryDriver driveControl;
    private GRTDriverStation driverStation;
    private GRTRobotBase robotBase;
    private GRTADXL345 adxl;
//    private final ADXL345DigitalAccelerometer primaryADXL;
//    private final RobotTiltAccel tiltSensor;
//    private final SensorLogger tiltLogger;
    

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
        driverStation = new GRTAttack3DriverStation(primary, secondary, DRIVER_PROFILE_KEYS, DRIVER_PROFILES,
                "driverStation");
        driverStation.enable();
        System.out.println("Mechanisms initialized");

        //Controllers
        driveControl = new PrimaryDriver(robotBase, driverStation, new LinearDrive(), "driveControl");
        batteryLogger = new SensorLogger(batterySensor, rpcConn, new int[]{23}, "batterylogger");
        System.out.println("Controllers Initialized");
        
//        adxl = new  GRTADXL345(1, 25, "ADXL345");
//        adxl.enable();
//        adxl.start();

        
//        GRTGyro gyro = new GRTGyro(1, 10, "Gyro");
//        gyro.enable();
//        gyro.start();
        
//        tiltSensor = new RobotTiltAccel(adxl, "TiltSensor");
//        tiltLogger = new SensorLogger(tiltSensor, rpcConn, new int[]{210}, "tiltLogger");
        
        // Start/prepare controllers
//        primaryADXL.enable();
//        batteryLogger.enable();
//        tiltSensor.enable();
        
        addTeleopController(driveControl);
//        addAutonomousController(tiltLogger);

        GRTEncoder encoder1 =  new GRTEncoder(2, 1, 4.0, 10, "EncoderLeft");
        
        GRTEncoder encoder2 = new GRTEncoder(3, 4, 4.0, 10, "EncoderRight");
        
        
        encoder1.start(); encoder1.enable();
        encoder2.start(); encoder2.enable();
        
        SensorLogger encoderLogger1 = new SensorLogger(encoder1, rpcConn, new int[]{81, 82, 83}, null);
        SensorLogger encoderLogger2 = new SensorLogger(encoder2, rpcConn, new int[]{84,85,86}, null);
        
        
        encoderLogger1.enable();
        encoderLogger2.enable();
    }
}
