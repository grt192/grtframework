package actuator;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Wrapper class for the Jag speed controller on a CAN bus.
 *
 * @author Calvin
 */
public class GRTCANJag extends Motor {

    private final CANJaguar jag;

    /**
     * Instantiates a new CAN Jag.
     *
     * @param id id of Jag on CAN bus.
     * @param name name of motor.
     * @throws CANTimeoutException
     */
    public GRTCANJag(int id, String name) throws CANTimeoutException {
        super(name);
        jag = new CANJaguar(id);
    }

    public void setSpeed(double speed) {
        if (enabled) {
            try {
                jag.setX(speed);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        }
    }
}
