package event.listeners;

import event.events.BatteryVoltageEvent;

/**
 *
 * @author ajc
 */
public interface BatteryVoltageListener {

    public void batteryVoltageChanged(BatteryVoltageEvent ev);
}
