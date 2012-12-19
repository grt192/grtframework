package sensor.base;

import core.Sensor;
import event.events.DrivingEvent;
import event.events.ShiftEvent;
import event.listeners.DrivingListener;
import event.listeners.ShiftListener;
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
    public static final int KEY_LEFT_SHIFT = 2;
    public static final int KEY_RIGHT_SHIFT = 3;
    private final Vector drivingListeners;
    private final Vector shiftListeners;

    /**
     * Creates a new driver station.
     *
     * @param name name of driver station.
     */
    public GRTDriverStation(String name) {
        super(name, 2);

        drivingListeners = new Vector();
        shiftListeners = new Vector();

    }

    public void addDrivingListener(DrivingListener l) {
        drivingListeners.addElement(l);
    }

    public void removeDrivingListener(DrivingListener l) {
        drivingListeners.removeElement(l);
    }

    public void addShiftListener(ShiftListener l) {
        shiftListeners.addElement(l);
    }

    public void removeShiftListener(ShiftListener l) {
        shiftListeners.removeElement(l);
    }

    protected void notifyLeftDriveSpeed(double speed) {
        setState(KEY_LEFT_VELOCITY, speed);
    }

    protected void notifyRightDriveSpeed(double speed) {
        setState(KEY_RIGHT_VELOCITY, speed);
    }

    protected void notifyListeners(int id, double newValue) {

        DrivingEvent ev;
        ShiftEvent sev;
        
        switch (id) {
            case KEY_LEFT_VELOCITY:
                ev = new DrivingEvent(this, DrivingEvent.SIDE_LEFT, newValue);
                for (Enumeration en = drivingListeners.elements(); en.
                        hasMoreElements();) {
                    if (en instanceof DrivingListener)
                        ((DrivingListener) en.nextElement()).driverLeftSpeed(ev);
                }
                break;

            case KEY_RIGHT_VELOCITY:
                ev = new DrivingEvent(this, DrivingEvent.SIDE_RIGHT, newValue);
                for (Enumeration en = drivingListeners.elements(); en.
                        hasMoreElements();) {
                    if (en instanceof DrivingListener)
                       ((DrivingListener) en.nextElement()).driverRightSpeed(ev);
                }
                break;
                
            case KEY_LEFT_SHIFT:
                sev = new ShiftEvent(this, DrivingEvent.SIDE_LEFT, newValue);
                for (Enumeration en = shiftListeners.elements(); en.
                        hasMoreElements();) {
                    if (en instanceof ShiftListener)
                        ((ShiftListener) en.nextElement()).shift(sev);
                }
                break;
            case KEY_RIGHT_SHIFT:
                sev = new ShiftEvent(this, DrivingEvent.SIDE_RIGHT, newValue);
                for (Enumeration en = shiftListeners.elements(); en.
                        hasMoreElements();) {
                    if (en instanceof ShiftListener)
                       ((ShiftListener) en.nextElement()).shift(sev);
                }
                break;

        }
    }
}
