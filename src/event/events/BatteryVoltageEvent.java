package event.events;

import sensor.GRTBatterySensor;

/**
 *
 * @author ajc
 */
public class BatteryVoltageEvent extends SensorEvent {

    public BatteryVoltageEvent(GRTBatterySensor sensor, double voltage) {
        super(sensor, 0, voltage);
    }

    public double getVoltage() {
        return getData();
    }
}
