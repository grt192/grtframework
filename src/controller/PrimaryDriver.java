/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import core.EventController;
import edu.wpi.first.wpilibj.DriverStation;
import event.DrivingEvent;
import event.DrivingListener;
import event.DrivingProfileEvent;
import event.DrivingProfileListener;
import event.XboxJoystickEvent;
import event.XboxJoystickListener;
import mechanism.GRTDriveTrain;
import sensor.base.GRTXboxDriverStation;
import mechanism.GRTRobotBase;
import sensor.base.IDriverProfile;
import sensor.base.LinearDrive;
import sensor.GRTXBoxJoystick;
import sensor.base.GRTDriverStation;

/**
 * Robot base driving.
 *
 * Operates for any DriverStation
 * 
 * @author ajc
 */
public class PrimaryDriver extends EventController implements DrivingListener, DrivingProfileListener {

    //sensor
    private final GRTDriverStation ds;
    //actuator
    private final GRTRobotBase dt;
    //drive curve
    private IDriverProfile driveProfile;
    //state
    private double leftVelocity;
    private double rightVelocity;

    public PrimaryDriver(GRTRobotBase dt, GRTDriverStation ds, IDriverProfile driveProfile, String name) {
        super(name);
        this.dt = dt;
        this.ds = ds;
        this.driveProfile = driveProfile;
    }

    protected void startListening() {
        ds.addDrivingListener(this);
        ds.addDrivingProfileListener(this);
    }

    protected void stopListening() {
        ds.removeDrivingListener(this);
        ds.removeDrivingProfileListener(this);
    }

    public void driverLeftSpeed(DrivingEvent e) {
        leftVelocity = e.getPercentSpeed();

        dt.tankDrive(driveProfile.driveSpeed(leftVelocity), driveProfile.driveSpeed(rightVelocity));
    }

    public void driverRightSpeed(DrivingEvent e) {
        rightVelocity = e.getPercentSpeed();

        dt.tankDrive(driveProfile.driveSpeed(leftVelocity), driveProfile.driveSpeed(rightVelocity));
    }

    public void drivingProfileChanged(DrivingProfileEvent e) {
        driveProfile = e.getProfile();
    }
}
