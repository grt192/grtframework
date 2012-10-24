package event.events;

import core.Sensor;
import sensor.GRTSwitch;

/**
 *
 * @author gerberduffy
 */
public class SwitchEvent extends SensorEvent {

    public SwitchEvent(GRTSwitch sw, double newState) {
        super(sw, 0, newState);
    }

    public SwitchEvent(GRTSwitch sw, boolean newState) {
        super(sw, 0, newState ? Sensor.TRUE : Sensor.FALSE);
    }

    public boolean getState() {
        return getData() == Sensor.TRUE;
    }
}
