package event.listeners;

import event.events.ShiftEvent;

/**
 * A high level interface for robot driving
 *
 * @author ajc
 */
public interface ShiftListener {

    /**
     * Called to set shift drivetrain. 
     *
     * @param e event specifying shifter and direction
     */
    public void shift(ShiftEvent e);

}
