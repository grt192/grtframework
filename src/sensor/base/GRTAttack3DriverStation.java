/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

import event.events.Attack3JoystickEvent;
import event.events.ButtonEvent;
import event.listeners.Attack3JoystickListener;
import event.listeners.ButtonListener;
import sensor.GRTAttack3Joystick;

/**
 * Driver station using 2 Logitech Attack 3 Joysticks
 * @author dan
 */
public class GRTAttack3DriverStation extends GRTDriverStation
        implements Attack3JoystickListener, ButtonListener {
    private final GRTAttack3Joystick left;
    private final GRTAttack3Joystick right;
    
    public GRTAttack3DriverStation(GRTAttack3Joystick left,
            GRTAttack3Joystick right,
            String name){
        super(name);
        this.left= left;
        this.right = right;
    }
    
    protected void startListening() {
        left.addJoystickListener(this);
        right.addJoystickListener(this);
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
        if (e.getSource() == left){
            notifyLeftDriveSpeed(e.getData());
        }
        else if (e.getSource() == right){
            notifyRightDriveSpeed(e.getData());
        }
    }

    public void AngleChanged(Attack3JoystickEvent e) {
    }

    public void buttonPressed(ButtonEvent e) {
    }

    public void buttonReleased(ButtonEvent e) {
    }
	
}
