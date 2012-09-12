/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import core.Actuator;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author gerberduffy
 */
public class GRTSolenoid extends Actuator {

    private Solenoid sol;
    
    public static final double ON = 1.0;
    public static final double OFF = 0.0;

    public GRTSolenoid(int slot, int channel, String id) {
        super(id);

        sol = new Solenoid(slot, channel);
    }

    /**
     * Engage or disengage the solenoid
	 * @param command ON if on, OFF if off
     */
    public void executeCommand(double command) {
        if (command == ON) {
            sol.set(true);
        } else if (command == OFF) {
            sol.set(false);
        }
    }


//    @Override
	/**
	 * Halts the solenoid, and moves it to original position
	 */
    public void halt() {
        sol.set(false);

        super.halt();

    }
}
