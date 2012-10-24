package event.listeners;

import event.events.GyroEvent;

/**
 *
 * @author calvin
 */
public interface GyroListener {

    public void angleChanged(GyroEvent e);
}
