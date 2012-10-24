package event.events;

import sensor.GRTADXL345;

/**
 *
 * @author calvin
 */
public class ADXL345Event extends SensorEvent {

    public static final int KEY_X = 0;
    public static final int KEY_Y = 1;
    public static final int KEY_Z = 2;

    public ADXL345Event(GRTADXL345 source, int id, double acceleration) {
        super(source, id, acceleration);
    }

    /**
     * Gets acceleration along some axis
     *
     * @return acceleration in G's
     */
    public double getAcceleration() {
        return getData();
    }
}
