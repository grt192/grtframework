/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import edu.wpi.first.wpilibj.Relay;
import core.Actuator;

/**
 *
 * @author calvin
 */
public class GRTRelay extends Actuator{
    private Relay relay;
    
    private static final double FORWARD = 1.0;
    private static final double REVERSE = -1.0;
    private static final double OFF = 0.0;
    
    public GRTRelay(int channel, String name){
        super(name);
        relay = new Relay(channel);
    }
    
    /**
 * Sends command to relay.  If the value of the command is -1.0 the relay is in reverse,
 * 1.0 is forward, and 0.0 is off.
 * @param c
 */
    public void executeCommand(double command) {
        if (command == OFF) {
            relay.set(Relay.Value.kOff);
        } else if(command == FORWARD) {
            relay.set(Relay.Value.kForward);
        } else if(command == REVERSE) {
            relay.set(Relay.Value.kReverse);
        }
    }
/**
 * SEts the state of the relay to off
 */
    public void halt() {
        relay.set(Relay.Value.kOff);
    }
/**
 * Returns String
 * @return
 */
    public String toString() {
        return "Relay";
    }
}
