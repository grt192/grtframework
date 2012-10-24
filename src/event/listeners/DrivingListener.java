package event.listeners;

import event.events.DrivingEvent;

/**
 * A high level interface for robot driving
 *
 * @author ajc
 */
public interface DrivingListener {

    /**
     * Called to set speed of left drivetrain.
     *
     * @param e event specifying left side speed
     */
    public void driverLeftSpeed(DrivingEvent e);

    /**
     * Called to set speed of right drivetrain.
     *
     * @param e event specifying right side speed
     */
    public void driverRightSpeed(DrivingEvent e);
}
