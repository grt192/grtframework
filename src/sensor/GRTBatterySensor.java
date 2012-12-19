package sensor;

import core.Sensor;
import edu.wpi.first.wpilibj.DriverStation;
import event.events.BatteryVoltageEvent;
import event.listeners.BatteryVoltageListener;
import java.util.Enumeration;
import java.util.Vector;

/**
 * A battery sensor that retrieves main battery voltage from the analog sidecar.
 *
 * @author ajc
 */
public class GRTBatterySensor extends Sensor {

    public static final int KEY_BATTERY_VOLTAGE = 0;
    private final DriverStation ds;
    private final Vector listeners;

    /**
     * Instantiates a new battery sensor.
     *
     * @param pollTime how often to poll
     * @param name name of sensor
     */
    public GRTBatterySensor(int pollTime, String name) {
        super(name, pollTime, 1);
        ds = DriverStation.getInstance();
        listeners = new Vector();
    }

    protected void poll() {
        setState(KEY_BATTERY_VOLTAGE, ds.getBatteryVoltage());
    }

    /**
     * Returns the current voltage of the battery.
     *
     * @return battery voltage, ideally >12V
     */
    public double getVoltage() {
        return getState(KEY_BATTERY_VOLTAGE);
    }

    protected void notifyListeners(int id,  double newDatum) {

        BatteryVoltageEvent e = new BatteryVoltageEvent(this, newDatum);

        for (Enumeration en = listeners.elements(); en.hasMoreElements();)
            ((BatteryVoltageListener) en.nextElement()).batteryVoltageChanged(e);
    }

    public void addBatteryVoltageListener(BatteryVoltageListener l) {
        listeners.addElement(l);
    }

    public void removeBatteryVoltageListener(BatteryVoltageListener l) {
        listeners.removeElement(l);
    }
}
