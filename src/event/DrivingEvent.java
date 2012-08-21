/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import sensor.base.GRTDriverStation;

/**
 *
 * @author ajc
 */
public class DrivingEvent {

    public static final int SIDE_LEFT = 0;
    public static final int SIDE_RIGHT = 1;
    private final GRTDriverStation source;
    private final int sideID;
    private final double value;

    public DrivingEvent(GRTDriverStation source, int sideID, double value) {
        this.source = source;
        this.sideID = sideID;
        this.value = value;
    }

    public int getSide() {
        return sideID;
    }

    public double getPercentSpeed() {
        return value;
    }

    public GRTDriverStation getSource() {
        return source;
    }
}
