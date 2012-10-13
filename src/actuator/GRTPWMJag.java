/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import edu.wpi.first.wpilibj.Jaguar;

/**
 * Wrapper class for PWM Jag.
 *
 * @author Calvin
 */
public class GRTPWMJag extends Motor {

    private final Jaguar jag;

    /**
     * Instantiates a Jag controller on the default digital module.
     *
     * @param channel number of PWM output this Jag is attached to
     * @param name name of motor
     */
    public GRTPWMJag(int channel, String name) {
        super(name);
        jag = new Jaguar(channel);
    }

    /**
     * Instantiates a Jag controller.
     *
     * @param moduleNum digital module number
     * @param channel number of PWM output this Jag is attached to
     * @param name name of motor
     */
    public GRTPWMJag(int moduleNum, int channel, String name) {
        super(name);
        jag = new Jaguar(moduleNum, channel);
    }

    public void setSpeed(double speed) {
        if (enabled) {
            jag.set(speed);
        }
    }
}
