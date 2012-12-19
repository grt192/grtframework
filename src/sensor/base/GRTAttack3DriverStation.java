package sensor.base;

import event.events.Attack3JoystickEvent;
import event.events.ButtonEvent;
import event.events.ShiftEvent;
import event.listeners.Attack3JoystickListener;
import event.listeners.ButtonListener;
import logger.GRTLogger;
import sensor.GRTAttack3Joystick;

/**
 * Driver station using 2 Logitech Attack 3 Joysticks
 *
 * @author dan
 */
public class GRTAttack3DriverStation extends GRTDriverStation
        implements Attack3JoystickListener, ButtonListener {
    
    private final GRTAttack3Joystick left;
    private final GRTAttack3Joystick right;

    public GRTAttack3DriverStation(GRTAttack3Joystick left,
            GRTAttack3Joystick right,
            String name) {
        super(name);
        this.left = left;
        this.right = right;
    }

    protected void startListening() {
        left.addJoystickListener(this);
        right.addJoystickListener(this);
		left.addButtonListener(this);
        right.addButtonListener(this);
    }

    protected void stopListening() {
        left.removeJoystickListener(this);
        left.removeButtonListener(this);
        right.removeJoystickListener(this);
        right.removeButtonListener(this);
    }

    public void XAxisMoved(Attack3JoystickEvent e) {
    }

    public void YAxisMoved(Attack3JoystickEvent e) {
        if (e.getSource() == left)
            notifyLeftDriveSpeed(e.getData());
        else if (e.getSource() == right)
            notifyRightDriveSpeed(e.getData());
    }

    public void AngleChanged(Attack3JoystickEvent e) {
    }

    public void buttonPressed(ButtonEvent e) {
		if(e.getButtonID() == GRTAttack3Joystick.KEY_BUTTON_1 && e.getSource() == left){
            notifyListeners(KEY_LEFT_SHIFT, ShiftEvent.KEY_SHIFT_UP);
            notifyListeners(KEY_RIGHT_SHIFT, ShiftEvent.KEY_SHIFT_UP);

        } else if (e.getButtonID() == GRTAttack3Joystick.KEY_BUTTON_1 && e.getSource() == right){
            notifyListeners(KEY_LEFT_SHIFT, ShiftEvent.KEY_SHIFT_DOWN);
            notifyListeners(KEY_RIGHT_SHIFT, ShiftEvent.KEY_SHIFT_DOWN);

        }
    }

    public void buttonReleased(ButtonEvent e) {
    }
}
