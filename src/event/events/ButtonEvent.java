package event.events;

import core.Sensor;

/**
 *
 * @author ajc
 */
public class ButtonEvent extends SensorEvent {

    public ButtonEvent(Sensor source, int id, boolean pressed) {
        super(source, id, pressed ? Sensor.TRUE : Sensor.FALSE);
    }

    public int getButtonID() {
        return getID();
    }

    public boolean isPressed() {
        return getData() == Sensor.TRUE;
    }
}
