package event.events;

import sensor.GRTAttack3Joystick;

/**
 *
 * @author dan
 */
public class Attack3JoystickEvent extends SensorEvent {

    public Attack3JoystickEvent(GRTAttack3Joystick source, int id, double value) {
        super(source, id, value);
    }
}
