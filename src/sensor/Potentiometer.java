/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.Sensor;
import edu.wpi.first.wpilibj.AnalogChannel;

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
    }
}
