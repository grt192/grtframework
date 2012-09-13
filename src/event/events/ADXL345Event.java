/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event.events;
import sensor.GRTADXL345;

/**
 *
 * @author calvin
 */
public class ADXL345Event {
    public static final int KEY_X = 0;
    public static final int KEY_Y = 1;
    public static final int KEY_Z = 2;

    private GRTADXL345 source;
    private int id;
    private double acceleration;

    public ADXL345Event(GRTADXL345 source, int id, double acceleration) {
        this.source = source;
        this.id = id;
        this.acceleration = acceleration;
    }

    /**
     * Gets acceleration along some axis
     * 
     * @return acceleration in G's
     */
    public double getAcceleration() {
        return acceleration;
    }

    public int getId() {
        return id;
    }

    public GRTADXL345 getSource() {
        return source;
    }
}
