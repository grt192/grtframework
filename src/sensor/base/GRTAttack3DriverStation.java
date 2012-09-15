/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

import event.events.Attack3JoystickEvent;
import event.listeners.Attack3JoystickListener;
import event.events.ButtonEvent;
import event.listeners.ButtonListener;
import sensor.GRTAttack3Joystick;

/**
 * Driver station using 2 Logitech Attack 3 Joysticks
 * @author dan
 */
public class GRTAttack3DriverStation extends GRTDriverStation implements Attack3JoystickListener, ButtonListener{
    private final GRTAttack3Joystick left;
    private final GRTAttack3Joystick right;
    
    public GRTAttack3DriverStation(GRTAttack3Joystick left, GRTAttack3Joystick right,String name){
        super(name);
        this.left= left;
        this.right = right;
    }
    
    protected void startListening() {
        left.addJoystickListener(this);
        left.addButtonListener(this);
        right.addJoystickListener(this);
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
        if (e.getSource()==left){
            notifyLeftDriveSpeed(e.getValue());
            notifyStateChange(KEY_LEFT_VELOCITY, e.getValue());
        }
        else if (e.getSource() ==right){
            notifyRightDriveSpeed(e.getValue());
            notifyStateChange(KEY_RIGHT_VELOCITY, e.getValue());
        }
    }

    public void AngleChanged(Attack3JoystickEvent e) {
    }

    public void buttonPressed(ButtonEvent e) {
    }

    public void buttonReleased(ButtonEvent e) {
    }
	
    private static int getIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }
        return -1;
    }
    
}
