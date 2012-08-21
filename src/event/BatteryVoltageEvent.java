/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package event;

import sensor.GRTBatterySensor;


/**
 *
 * @author ajc
 */
public class BatteryVoltageEvent {
    private final GRTBatterySensor sensor;
    private final double voltage;

    public BatteryVoltageEvent(GRTBatterySensor sensor, double voltage){
        this.sensor = sensor;
        this.voltage = voltage;
    }

    public GRTBatterySensor getSource(){
        return sensor;
    }

    public double getVoltage(){
        return voltage;
    }

}
