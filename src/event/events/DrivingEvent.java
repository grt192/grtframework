/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event.events;

import sensor.base.GRTDriverStation;

/**
 *
 * @author ajc
 */
public class DrivingEvent extends SensorEvent {

    public static final int SIDE_LEFT = 0;
    public static final int SIDE_RIGHT = 1;
    
    public DrivingEvent(GRTDriverStation source, int sideID, double value) {
        super(source, sideID, value);
    }

    public int getSide() {
        return getID();
    }

    public double getPercentSpeed() {
        return getData();
    }
}
