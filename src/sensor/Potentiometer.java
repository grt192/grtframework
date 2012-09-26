/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.Sensor;
import edu.wpi.first.wpilibj.AnalogChannel;
import event.events.PotentiometerEvent;
import event.listeners.PotentiometerListener;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author calvin
 */
public class Potentiometer extends Sensor {

    public static final int KEY_VALUE = 0;
    public static final int NUM_DATA = 1;
    private int potentiometerType;
    private AnalogChannel channel;
    public static final int LINEAR = 0;
    public static final int LOGARITHMIC = 1;
    
    private Vector potentiometerListeners = new Vector();

    public Potentiometer(AnalogChannel channel, int type, int pollTime, String name) {
        super(name, pollTime, NUM_DATA);
        potentiometerType = type;
        this.channel = channel;
    }

    protected void poll() {
        setState(KEY_VALUE, updateScaledValue());
    }

    private double updateScaledValue() {
        double rawValue = channel.getVoltage();
        double scaledValue;
        switch (potentiometerType) {
            case LINEAR:
                scaledValue = rawValue / 5;
                break;
            default:
                scaledValue = rawValue / 5;
        }

        return scaledValue;
    }

    protected void notifyListeners(int id, double oldDatum, double newDatum) {
        PotentiometerEvent e = new PotentiometerEvent(this, newDatum);
        for (Enumeration en = potentiometerListeners.elements();
                en.hasMoreElements();)
            ((PotentiometerListener) en.nextElement()).valueChanged(e);
    }
    
    public void addListener(PotentiometerListener l) {
        potentiometerListeners.addElement(l);
    }

    public void removeListener(PotentiometerListener l) {
        potentiometerListeners.removeElement(l);
    }
}
