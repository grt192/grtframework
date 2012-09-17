/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
