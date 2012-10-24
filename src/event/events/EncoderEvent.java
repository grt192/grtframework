package event.events;

import sensor.GRTEncoder;

/**
 *
 * @author gerberduffy
 */
public class EncoderEvent extends SensorEvent {

    public static final int ROTATION_STARTED = 0;
    public static final int ROTATION_STOPPED = 1;
    public static final int ANGLE_CHANGE = 2;

    public EncoderEvent(GRTEncoder source, int id, double data) {
        super(source, id, data);
    }
}
