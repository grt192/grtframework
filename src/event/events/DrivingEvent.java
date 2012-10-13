/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event.events;

import sensor.base.GRTDriverStation;

/**
 * Event specifying the speed of either the left or right side of the robot.
 * 
 * @author ajc
 */
public class DrivingEvent extends SensorEvent {

    public static final int SIDE_LEFT = 0;
    public static final int SIDE_RIGHT = 1;
    
    /**
     * Creates a new DrivingEvent.
     * 
     * @param source source of event
     * @param sideID left or right side
     * @param value the speed of that side
     */
    public DrivingEvent(GRTDriverStation source, int sideID, double value) {
        super(source, sideID, value);
    }

    /**
     * Whether or not this is a left side event or a right side event.
     * 
     * @return DrivingEvent.SIDE_LEFT or DrivingEvent.SIDE_RIGHT
     */
    public int getSide() {
        return getID();
    }

    /**
     * Returns the speed specified by this event.
     * 
     * @return speed of side, as number from -1 to 1
     */
    public double getSpeed() {
        return getData();
    }
}
