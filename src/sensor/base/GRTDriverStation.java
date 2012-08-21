/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

import core.Sensor;
import event.DrivingEvent;
import event.DrivingListener;
import event.DrivingProfileEvent;
import event.DrivingProfileListener;
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
    
    //profiles
    protected IDriverProfile[] curves;
    /*
     * maps the profile index to the button that should register it
     * so {3 4} means button 3 will register PROFILE_LINEAR,
     * while button 4 will register PROFILE_SQUARED.
     */
    protected final int[] profileButtons;
    //listeners
    private final Vector drivingListeners;
    private final Vector profileListeners;

    /**
     * 
     * @param profileButtons
     * @param curves
     * @param name
     */
    public GRTDriverStation(int[] profileButtons, IDriverProfile[] curves, String name) {
        super(name);
        this.profileButtons = profileButtons;
        this.curves = curves;

        drivingListeners = new Vector();
        profileListeners = new Vector();
    }

    public void addDrivingListener(DrivingListener l) {
        drivingListeners.addElement(l);
    }

    public void removeDrivingListener(DrivingListener l) {
        drivingListeners.removeElement(l);
    }

    public void addDrivingProfileListener(DrivingProfileListener l) {
        profileListeners.addElement(l);
    }

    public void removeDrivingProfileListener(DrivingProfileListener l) {
        profileListeners.removeElement(l);
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

    protected void notifyProfileChange(int profileID) {
        DrivingProfileEvent e = new DrivingProfileEvent(this, curves[profileID]);
        for (int i = 0; i < profileListeners.size(); i++) {
            ((DrivingProfileListener) profileListeners.elementAt(i)).drivingProfileChanged(e);

        }
    }
}
