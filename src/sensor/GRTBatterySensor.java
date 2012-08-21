/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.PollingSensor;
import edu.wpi.first.wpilibj.DriverStation;
import event.BatteryVoltageEvent;
import event.BatteryVoltageListener;
import java.util.Vector;

/**
 * A battery sensor that retrieves main battery voltage from the analog sidecar
 * @author ajc
 */
public class GRTBatterySensor extends PollingSensor {

    public static final int KEY_BATTERY_VOLTAGE = 0;
    
    private final DriverStation ds;
    private final Vector listeners;

    public GRTBatterySensor(int pollTime, String name) {
        super(name, pollTime, 1);
        ds = DriverStation.getInstance();
        listeners = new Vector();
    }

    protected void poll() {
        setState(KEY_BATTERY_VOLTAGE, ds.getBatteryVoltage());
    }

    protected void notifyListeners(int id, double oldDatum, double newDatum) {

        BatteryVoltageEvent e = new BatteryVoltageEvent(this, newDatum);

        for (int i = 0; i < listeners.size(); i++) {
            ((BatteryVoltageListener) listeners.elementAt(i)).batteryVoltageChanged(e);
        }
    }

    public void addBatteryVoltageListener(BatteryVoltageListener l) {
        listeners.addElement(l);
    }

    public void removeBatteryVoltageListener(BatteryVoltageListener l) {
        listeners.removeElement(l);
    }
}
