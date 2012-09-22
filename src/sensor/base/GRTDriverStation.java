/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

import core.Sensor;
import event.events.DrivingEvent;
import event.listeners.DrivingListener;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Superclass for all DriverStations.
 *
 * @author ajc
 */
public abstract class GRTDriverStation extends Sensor {

    /*
     * State Keys
     */
    public static final int KEY_LEFT_VELOCITY = 0;
    public static final int KEY_RIGHT_VELOCITY = 1;
    private final Vector drivingListeners;

    /**
     * Creates a new driver station.
     *
     * @param name name of driver station.
     */
    public GRTDriverStation(String name) {
        super(name, 2);

        drivingListeners = new Vector();
    }

    public void addDrivingListener(DrivingListener l) {
        drivingListeners.addElement(l);
    }

    public void removeDrivingListener(DrivingListener l) {
        drivingListeners.removeElement(l);
    }

    protected void notifyLeftDriveSpeed(double speed) {
        setState(KEY_LEFT_VELOCITY, speed);
    }

    protected void notifyRightDriveSpeed(double speed) {
        setState(KEY_RIGHT_VELOCITY, speed);
    }

    protected void notifyListeners(int id, double oldValue, double newValue) {

        DrivingEvent ev;
        switch (id) {
            case KEY_LEFT_VELOCITY:
                ev = new DrivingEvent(this, DrivingEvent.SIDE_LEFT, newValue);
                for (Enumeration en = drivingListeners.elements(); en.hasMoreElements();) {
                    ((DrivingListener) en.nextElement()).driverLeftSpeed(ev);
                }
                break;

            case KEY_RIGHT_VELOCITY:
                ev = new DrivingEvent(this, DrivingEvent.SIDE_RIGHT, newValue);
                for (Enumeration en = drivingListeners.elements(); en.hasMoreElements();) {
                    ((DrivingListener) en.nextElement()).driverRightSpeed(ev);
                }
                break;
        }
    }
}
