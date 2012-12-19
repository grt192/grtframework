package controller;

import core.EventController;
import event.events.DrivingEvent;
import event.events.ShiftEvent;
import event.listeners.DrivingListener;
import event.listeners.ShiftListener;
import mechanism.ShiftingDriveTrain;
import sensor.base.GRTDriverStation;

/**
 * Robot base driver.
 *
 * Operates for any DriverStation.
 *
 * @author ajc
 */
public class ShiftingDriveController extends EventController implements DrivingListener, ShiftListener {

    //sensor
    private final GRTDriverStation ds;
    //actuator
    private final ShiftingDriveTrain dt;
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
    public ShiftingDriveController(ShiftingDriveTrain dt, GRTDriverStation ds, String name) {
        super(name);
        this.dt = dt;
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

        dt.tankDrive(leftVelocity, rightVelocity);
    }

    public void driverRightSpeed(DrivingEvent e) {
        rightVelocity = e.getSpeed();

        dt.tankDrive(leftVelocity, rightVelocity);
    }
   

	public void shift(ShiftEvent e) {
		if (e.getData() == ShiftEvent.KEY_SHIFT_DOWN){
            ((ShiftingDriveTrain) dt).shiftDown();
        }
        else{
            ((ShiftingDriveTrain) dt).shiftUp();
        }
	}
}
