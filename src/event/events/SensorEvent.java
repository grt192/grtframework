package event.events;

import core.Sensor;

/**
 * Generic sensor event.
 * 
 * @author anand, ajc
 */
public class SensorEvent {

    private Sensor source;
    private int id;
    private double data;

    public SensorEvent(Sensor source, int id, double data) {
        this.source = source;
        this.id = id;
        this.data = data;
    }

    public double getData() {
        return data;
    }

    public int getID() {
        return id;
    }

    public Sensor getSource() {
        return source;
    }
}
