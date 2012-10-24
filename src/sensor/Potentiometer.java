package sensor;

import core.Sensor;
import edu.wpi.first.wpilibj.AnalogChannel;
import event.events.PotentiometerEvent;
import event.listeners.PotentiometerListener;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Linear potentiometer on analog channel.
 *
 * @author calvin
 */
public class Potentiometer extends Sensor {

    public static final int KEY_VALUE = 0;
    public static final int NUM_DATA = 1;
    private AnalogChannel channel;
    private Vector potentiometerListeners = new Vector();

    /**
     * Instantiates a new potentiometer on the default analog channel.
     *
     * @param channel channel on analog module potentiometer is connected to
     * @param pollTime how often to poll the sensor
     * @param name name of potentiometer
     */
    public Potentiometer(int channel, int pollTime, String name) {
        super(name, pollTime, NUM_DATA);
        this.channel = new AnalogChannel(channel);
    }

    /**
     * Instantiates a new potentiometer.
     *
     * @param moduleNum analog module number
     * @param channel channel on analog module potentiometer is connected to
     * @param pollTime how often to poll the sensor
     * @param name name of potentiometer
     */
    public Potentiometer(int moduleNum, int channel,
            int pollTime, String name) {
        super(name, pollTime, NUM_DATA);
        this.channel = new AnalogChannel(channel);
    }

    protected void poll() {
        setState(KEY_VALUE, getValue());
    }

    /**
     * Returns how far the potentiometer has been moved.
     *
     * @return value from 0 - 1
     */
    public double getValue() {
        return channel.getVoltage() / 5.0;
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
