package event.events;

import sensor.Potentiometer;

/**
 *
 * @author calvin
 */
public class PotentiometerEvent extends SensorEvent {

    public PotentiometerEvent(Potentiometer source, double value) {
        super(source, 0, value);
    }

    public double getAngle() {
        return getData();
    }
}
