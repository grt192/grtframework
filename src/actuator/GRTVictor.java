/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import edu.wpi.first.wpilibj.Victor;

/**
 * Victor wrapper class.
 *
 * @author ajc
 */
public class GRTVictor extends Motor {

    Victor victor;

    /**
     * Instantiates a Victor controller on the default digital module
     *
     * @param channel number of PWM output this Victor is attached to
     * @param name name of motor
     */
    public GRTVictor(int channel, String name) {
        super(name);
        victor = new Victor(channel);
    }

    /**
     * Instantiates a Victor controller
     *
     * @param slot digital module number
     * @param channel number of PWM output this Victor is attached to
     * @param name name of motor
     */
    public GRTVictor(int slot, int channel, String name) {
        super(name);
        victor = new Victor(slot, channel);
    }

    public void setSpeed(double speed) {
        if (enabled) {
            victor.set(speed);
        }
    }
}
