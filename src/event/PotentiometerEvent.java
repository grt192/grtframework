/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import sensor.Potentiometer;

/**
 *
 * @author calvin
 */
public class PotentiometerEvent {
    
    private Potentiometer source;
    private double value;

    public PotentiometerEvent(Potentiometer source, double value) {
        this.source = source;
        this.value = value;
    }

    public double getAngle() {
        return value;
    }

    public Potentiometer getSource() {
        return source;
    }
    
}
