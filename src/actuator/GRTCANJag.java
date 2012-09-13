/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Wrapper class for the Jag speed controller on a CAN bus
 * 
 * @author Calvin
 */
public class GRTCANJag extends IMotor {

    private final CANJaguar jag;
    
    public GRTCANJag(int id, String name) throws CANTimeoutException {
        super(name);
        jag = new CANJaguar(id);
    }
    
    public void setSpeed(double speed) {
        if(enabled) {
            try {
                jag.setX(speed);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void executeCommand(double command) {
        setSpeed(command);
    }
    
    
}
