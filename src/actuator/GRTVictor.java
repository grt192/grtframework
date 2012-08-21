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
public class GRTVictor extends Actuator implements IMotor {

    Victor victor;

    public GRTVictor(int id, String name) {
        super(name);
        victor = new Victor(id);
    }

    public void executeCommand(double command) {
        if (enabled) {
            victor.set(command);
        }
    }

    /*
     * Set the Victor's speed
     * @param speed the new speed to set
     */
    public void setSpeed(double speed){
        if(enabled){
            victor.set(speed);
//            log(speed);
        }
    }
}
