/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package event.listeners;

import event.events.BatteryVoltageEvent;

/**
 *
 * @author ajc
 */
public interface BatteryVoltageListener {

    public void batteryVoltageChanged(BatteryVoltageEvent ev);

}
