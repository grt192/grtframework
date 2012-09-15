/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

import core.Sensor;
import event.events.DrivingEvent;
import event.listeners.DrivingListener;
import java.util.Vector;

/**
 * Superclass for all DriverStations.
 *
 * Handles driver profiles.s
 * @author ajc
 */
public abstract class GRTDriverStation extends Sensor {

    /*
     * State Keys
     */
    public static final int KEY_LEFT_VELOCITY = 0;
    public static final int KEY_RIGHT_VELOCITY = 1;
    public static final int KEY_PROFILE_ID = 2;
    
    private final Vector drivingListeners;

    /**
     * 
     * @param profileButtons
     * @param curves
     * @param name
     */
    public GRTDriverStation(String name) {
        super(name);

        drivingListeners = new Vector();
    }

    public void addDrivingListener(DrivingListener l) {
        drivingListeners.addElement(l);
    }

    public void removeDrivingListener(DrivingListener l) {
        drivingListeners.removeElement(l);
    }

    protected void notifyLeftDriveSpeed(double speed) {
        DrivingEvent ev = new DrivingEvent(this, DrivingEvent.SIDE_LEFT, speed);
        for (int i = 0; i < drivingListeners.size(); i++) {
            ((DrivingListener) drivingListeners.elementAt(i)).driverLeftSpeed(ev);
        }
    }

    protected void notifyRightDriveSpeed(double speed) {
        DrivingEvent ev = new DrivingEvent(this, DrivingEvent.SIDE_RIGHT, speed);
        for (int i = 0; i < drivingListeners.size(); i++) {
            ((DrivingListener) drivingListeners.elementAt(i)).driverRightSpeed(ev);
        }
    }
}
