package sensor;

import core.Sensor;
import edu.wpi.first.wpilibj.DigitalInput;
import event.events.SwitchEvent;
import event.listeners.SwitchListener;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Wrapper class for a switch. By default, switches are assumed to pull the
 * input low when pressed. If instead the input is low by default and pressing
 * the switch pulls the input high, set reversed to true.
 *
 * @author gerberduffy
 */
public class GRTSwitch extends Sensor {

    private DigitalInput in;
    private static final int STATE = 0;
    private static final int NUM_DATA = 1;
    private final boolean reversed;
    private Vector listeners;

    /**
     * Instantiates a new GRTSwitch.
     *
     * @param moduleNum DIO module number that switch is connected to
     * @param channel channel on IO module switch is connected to
     * @param pollTime how often to poll the switch
     * @param reversed true if switch pulls input high, false otherwise
     * @param name name of switch
     */
    public GRTSwitch(int moduleNum, int channel,
            int pollTime, boolean reversed, String name) {
        super(name, pollTime, NUM_DATA);
        in = new DigitalInput(moduleNum, channel);
        this.reversed = reversed;
        listeners = new Vector();
    }

    /**
     * Instantiates a new GRTSwitch on the default digital module.
     *
     * @param channel channel on IO module switch is connected to
     * @param pollTime how often to poll the switch
     * @param reversed true if switch pulls input high, false otherwise
     * @param name name of switch
     */
    public GRTSwitch(int channel, int pollTime, boolean reversed, String name) {
        super(name, pollTime, NUM_DATA);
        in = new DigitalInput(channel);
        this.reversed = reversed;
        listeners = new Vector();
    }

    /**
     * Whether or not the switch is pressed.
     *
     * @return true if pressed, false otherwise
     */
    public boolean isPressed() {
        return reversed == in.get();
    }

    protected void poll() {
        setState(STATE, isPressed() ? TRUE : FALSE);
    }

    protected void notifyListeners(int id, double newDatum) {

        SwitchEvent e = new SwitchEvent(this, newDatum);

        for (Enumeration en = listeners.elements(); en.hasMoreElements();)
            ((SwitchListener) en.nextElement()).switchStateChanged(e);
    }
}
