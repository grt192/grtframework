package core;

import event.events.SensorEvent;
import event.listeners.SensorChangeListener;
import java.util.Enumeration;
import java.util.Vector;

/**
 * A sensor sends numeric sensor event data. They only send data when running.
 * Sensors can either receive data through events, or by polling. It
 * additionally stores the state of variables and performs state change checks.
 *
 * @author ajc
 */
public abstract class Sensor extends GRTLoggedProcess {

    //Constants
    public static final double TRUE = 1.0;
    public static final double FALSE = 0.0;
    public static final double ERROR = Double.NaN;
    //Instance variables
    private final Vector stateChangeListeners;   //Collection of things that listen to this sensor
    private double[] data;

    /**
     * Constructs a sensor that doesn't poll.
     *
     * @param name name of sensor.
     * @param numData number of pieces of data.
     */
    public Sensor(String name, int numData) {
        this(name, -1, numData);
    }

    /**
     * Construct a polling sensor. Subclasses need to start themselves--make a
     * call to startPolling();
     *
     * @param name name of the sensor.
     * @param sleepTime time between polls [ms].
     * @param numData number of pieces of data.
     */
    public Sensor(String name, int sleepTime, int numData) {
        super(name, sleepTime);
        stateChangeListeners = new Vector();
        running = true;
        data = new double[numData];
    }

    /**
     * Stores a datum, and notifies listeners if the state of it has changed.
     *
     * @param id key of the data
     * @param datum fresh datum
     */
    protected void setState(int id, double datum) {
        double previous = data[id];
        //notify self and state change listeners if the datum has changed
        if (previous != datum) {
            notifyStateChange(id, previous, datum);
        }
        data[id] = datum;
    }

    /**
     * Retrieves sensor data.
     *
     * @param id numeric identifier of data.
     * @return representative sensor data.
     */
    public double getState(int id) {
        if (id >= data.length || id < 0) {
            return ERROR;
        }
        return data[id];
    }

    /**
     * Returns the number of different data stored by this sensor.
     *
     * @return number of data.
     */
    public int numData() {
        return data.length;
    }

    /**
     * Enables listening. Sensors need not listen to events, however.
     */
    protected void startListening() {
    }

    /**
     * Disables listening. Sensors need not listen to events, however.
     */
    protected void stopListening() {
    }

    public void enable() {
        //enable() always works because a Sensor is always running
        super.enable();
        startListening();
    }

    public void disable() {
        super.disable();
        stopListening();
    }

    /**
     * Calls the listener events based on what has changed
     *
     * @param id the key of the data that changed
     * @param oldDatum the datum's previous value
     * @param newDatum the datum's new value
     */
    protected abstract void notifyListeners(int id, double oldDatum, double newDatum);

    protected void notifyStateChange(int id, double oldDatum, double newDatum) {
        notifyListeners(id, oldDatum, newDatum);
        SensorEvent e = new SensorEvent(this, id, newDatum);
        for (Enumeration en = stateChangeListeners.elements(); en.hasMoreElements();) {
            ((SensorChangeListener) en.nextElement()).sensorStateChanged(e);
        }
    }

    /**
     * Adds a sensor state change listener.
     *
     * @param l state change listener to add.
     */
    public void addSensorStateChangeListener(SensorChangeListener l) {
        stateChangeListeners.addElement(l);
    }

    /**
     * Removes a sensor state change listener.
     *
     * @param l state change listener to remove.
     */
    public void removeSensorStateChangeListener(SensorChangeListener l) {
        stateChangeListeners.removeElement(l);
    }
}
