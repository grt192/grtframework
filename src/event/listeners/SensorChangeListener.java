package event.listeners;

import event.events.SensorEvent;

/**
 *
 * @author anand, ajc
 */
public interface SensorChangeListener {

    public void sensorStateChanged(SensorEvent e);
}
