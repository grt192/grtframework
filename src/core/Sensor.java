/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import event.SensorChangeListener;
import event.SensorEvent;
import java.util.Vector;

/**
 * A sensor sends sensor event data. They only send data when running.
 * 
 * @author ajc
 */
public abstract class Sensor extends GRTLoggedProcess {

    //Constants
    public static final double TRUE = 1.0;
    public static final double FALSE = 0.0;
    public static final double ERROR = -999;
    
    //Instance variables
    private Vector listeners;   //Collection of things that listen to this sensor

    public Sensor(String name) {
        super(name);
        listeners = new Vector();
        running = true;
    }

    /**
     * Adds listeners.
     */
    protected abstract void startListening();

    /**
     * Removes listeners
     */
    protected abstract void stopListening();

    public void enable() {
        //enable() always works because a Sensor is always running
        super.enable();
        startListening();
    }

    public void disable() {
        super.disable();
        stopListening();
    }

    protected void notifyStateChange(int id, double data) {
        SensorEvent e = new SensorEvent(this, id, data);
        for (int i = 0; i < listeners.size(); i++) {
            ((SensorChangeListener) listeners.elementAt(i)).sensorStateChanged(e);
        }
    }

    public void addSensorStateChangeListener(SensorChangeListener l) {
        listeners.addElement(l);
    }

    public void removeSensorStateChangeListener(SensorChangeListener l) {
        listeners.removeElement(l);
    }
}
