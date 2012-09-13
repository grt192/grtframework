/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import core.Actuator;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author ajc
 */
public class GRTVictor extends IMotor {

    Victor victor;

    public GRTVictor(int id, String name) {
        super(name);
        victor = new Victor(id);
    }

	/**
	 * Set the Victor's speed
	 * Identical to setSpeed(command)
	 * @param speed set the new speed to command
	 */
    public void executeCommand(double command) {
        if (enabled) {
            victor.set(command);
        }
    }

    /**
     * Set the Victor's speed (-1 to 1)
     * @param speed the new speed to set
     */
    public void setSpeed(double speed) {
        executeCommand(speed);
    }
}
