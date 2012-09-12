package actuator;

import core.Actuator;
import edu.wpi.first.wpilibj.Relay;

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
	 * Sends command to relay. If command is FORWARD (1.0),
	 * relay is turned on; if command is REVERSE (-1.0),
	 * relay is turned on in reverse; if command is OFF (0.0)
	 * output is disabled.
	 * 
	 * @param command command to send to relay
	 */
    public void executeCommand(double command) {
		if (enabled) {
			if (command == OFF) {
				relay.set(Relay.Value.kOff);
			} else if (command == FORWARD) {
				relay.set(Relay.Value.kForward);
			} else if (command == REVERSE) {
				relay.set(Relay.Value.kReverse);
			}
		}
    }

	/**
	 * Sets the state of the relay to off, and disables it
	 */
    public void halt() {
        relay.set(Relay.Value.kOff);
		super.halt();
    }

}
