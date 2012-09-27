/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import core.Actuator;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Solenoid wrapper class
 *
 * @author gerberduffy
 */
public class GRTSolenoid extends Actuator {

    private Solenoid sol;
    public static final double ON = 1.0;
    public static final double OFF = 0.0;

    /**
     * Instantiates a solenoid on the HV digital module.
     *
     * @param channel channel solenoid is connected to
     * @param name name of solenoid
     */
    public GRTSolenoid(int channel, String name) {
        super(name);
        sol = new Solenoid(channel);
    }

    /**
     * Instantiates a solenoid.
     *
     * @param slot slot of HV digital module
     * @param channel channel solenoid is connected to
     * @param name name of solenoid
     */
    public GRTSolenoid(int slot, int channel, String name) {
        super(name);

        sol = new Solenoid(slot, channel);
    }

    /**
     * Engage or disengage the solenoid.
     *
     * @param command ON if on, OFF if off
     */
    public void executeCommand(double command) {
        if (enabled) {
            if (command == ON)
                sol.set(true);
            else if (command == OFF)
                sol.set(false);
        }
    }

    /**
     * Engage or disengage the solenoid.
     *
     * @param command true if on, false if off
     */
    public void engage(boolean command) {
        if (enabled)
            sol.set(command);
    }
    
    /**
     * Toggles the solenoid--turns on if currently off,
     * turns off if currently on
     */
    public void toggle() {
        if (enabled)
            sol.set(!sol.get());
    }

    /**
     * Halts the solenoid, and moves it to original position
     */
    public void halt() {
        sol.set(false);
        super.halt();
    }
}
