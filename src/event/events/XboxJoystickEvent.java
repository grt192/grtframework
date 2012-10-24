package event.events;

import sensor.GRTXBoxJoystick;

/**
 *
 * @author student
 */
public class XboxJoystickEvent extends SensorEvent {

    public XboxJoystickEvent(GRTXBoxJoystick source, int id, double value) {
        super(source, id, value);
    }
}