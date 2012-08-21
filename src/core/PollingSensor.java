/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 * A PollingSensor directly obtains data through the poll() method, as opposed 
 * to generic sensors that could receive events.
 * 
 * It additionally stores the state of variables, and automatically performs
 * state change checks.
 * 
 * 
 * @author ajc
 */
public abstract class PollingSensor extends Sensor {

    private double[] data;
    private final int sleepTime;

    /**
     * Construct a polling sensor. 
     * Subclasses need to start themselves-- make a call to start();
     * @param name name of the sensor
     * @param sleepTime time between polls [ms]
     * @param numData number of pieces of data
     */
    public PollingSensor(String name, int sleepTime, int numData) {
        super(name);
        this.sleepTime = sleepTime;
        data = new double[numData];
    }

    /**
     * Called to poll sensor.
     */
    protected abstract void poll();

    public void run() {
        running = true;
        while (running) {

            //only poll, and thus only send events, if enabled
            if (enabled) {
                poll();
            }

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
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
            notifyListeners(id, previous, datum);
            notifyStateChange(id, datum);
        }
        data[id] = datum;
    }

    /**
     * Retrieves sensor data
     * @param id
     * @return 
     */
    public double getState(int id) {
        return data[id];
    }

    /**
     * Calls the listener events based on what has changed
     * @param id the key of the data that changed
     * @param oldDatum the datum's previous value
     * @param newDatum  the datum's new value
     */
    protected abstract void notifyListeners(int id, double oldDatum, double newDatum);

    /*
     * Polling sensors do not listen to things, necesserily
     */
    protected void startListening() {
    }

    protected void stopListening() {
    }
}
