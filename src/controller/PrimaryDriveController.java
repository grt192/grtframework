package controller;

import core.EventController;
import event.events.DrivingEvent;
import event.listeners.DrivingListener;
import mechanism.GRTDriveTrain;
import mechanism.GRTRobotBase;
import mechanism.ShiftingDriveTrain;
import sensor.base.GRTDriverStation;

/**
 * Robot base driver.
 *
 * Operates for any DriverStation.
 *
 * @author ajc
 */
public class PrimaryDriveController extends EventController implements DrivingListener {

    //sensor
    private final GRTDriverStation ds;
    //actuator
    private final GRTRobotBase base;
    //state
    private double leftVelocity;
    private double rightVelocity;

    /**
     * Creates a new driving controller.
     * 
     * @param base robot base to drive
     * @param ds driver station to control with
     * @param name name of controller
     */
    public PrimaryDriveController(GRTRobotBase base, GRTDriverStation ds, String name) {
        super(name);
        this.base = base;
        this.ds = ds;
    }

    protected void startListening() {
        ds.addDrivingListener(this);
    }

    protected void stopListening() {
        ds.removeDrivingListener(this);
    }

    public void driverLeftSpeed(DrivingEvent e) {
        leftVelocity = e.getSpeed();

        base.tankDrive(leftVelocity, rightVelocity);
    }

    public void driverRightSpeed(DrivingEvent e) {
        rightVelocity = e.getSpeed();

        base.tankDrive(leftVelocity, rightVelocity);
    }
}
