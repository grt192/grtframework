/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Calvin
 */
public class GRTPWMJag extends IMotor {
    
    private final Jaguar jag;
    
    public GRTPWMJag(int id, String name){
        super(name);
        jag = new Jaguar(id);
    }

    public void setSpeed(double speed) {
        if(enabled) {
            jag.set(speed);
        }
    }

    public void executeCommand(double command) {
        setSpeed(command);
    }
    
    
    
}
