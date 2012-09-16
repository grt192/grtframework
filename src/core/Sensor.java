/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import event.events.SensorEvent;
import event.listeners.SensorChangeListener;
import java.util.Enumeration;
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
    public static final double ERROR = -Double.NaN;
    
    //Instance variables
    private Vector listeners;   //Collection of things that listen to this sensor

    public Sensor(String name) {
        super(name);
        listeners = new Vector();
        running = true;
    }

    /**
     * Enables listening. Sensors need not listen to events, however.
     */
    protected void startListening(){};

    /**
     * Disables listening. Sensors need not listen to events, however.
     */
    protected void stopListening(){};

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
        for (Enumeration en = listeners.elements(); en.hasMoreElements();) {
            ((SensorChangeListener) en.nextElement()).sensorStateChanged(e);
        }
    }

    public void addSensorStateChangeListener(SensorChangeListener l) {
        listeners.addElement(l);
    }

    public void removeSensorStateChangeListener(SensorChangeListener l) {
        listeners.removeElement(l);
    }
}
