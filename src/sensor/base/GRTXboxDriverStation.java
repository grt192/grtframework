/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

import event.events.ButtonEvent;
import event.listeners.ButtonListener;
import event.events.XboxJoystickEvent;
import event.listeners.XboxJoystickListener;
import sensor.GRTXBoxJoystick;

/**
 * Driverstation using XBoxJoysticks
 *
 * @author ajc
 */
public class GRTXboxDriverStation extends GRTDriverStation implements XboxJoystickListener, ButtonListener {

    private final GRTXBoxJoystick primary;
    private final GRTXBoxJoystick secondary;

    /**
     * 
     * @param primary
     * @param secondary
     * @param profileButtons
     * @param curves
     * @param name
     */
    public GRTXboxDriverStation(GRTXBoxJoystick primary,
            GRTXBoxJoystick secondary,
            String name) {
        super(name);
        this.primary = primary;
        this.secondary = secondary;

    }

    protected void startListening() {
        primary.addJoystickListener(this);
        primary.addButtonListener(this);
    }

    protected void stopListening() {
        primary.removeJoystickListener(this);
        primary.removeButtonListener(this);
    }

    /*
     * JOYSTICK EVENTS
     */
    public void leftXAxisMoved(XboxJoystickEvent e) {
    }

    public void leftYAxisMoved(XboxJoystickEvent e) {
        if (e.getSource() == primary) {
            notifyLeftDriveSpeed(e.getValue());
            notifyStateChange(KEY_LEFT_VELOCITY, e.getValue());
        }
    }

    public void rightXAxisMoved(XboxJoystickEvent e) {
    }

    public void rightYAxisMoved(XboxJoystickEvent e) {
        if (e.getSource() == primary) {
            notifyRightDriveSpeed(e.getValue());
            notifyStateChange(KEY_RIGHT_VELOCITY, e.getValue());
        }
    }

    public void padMoved(XboxJoystickEvent e) {
    }

    public void triggerMoved(XboxJoystickEvent e) {
    }

    /*
     * BUTTON EVENTS
     */
    public void buttonPressed(ButtonEvent e) {
    }

    public void buttonReleased(ButtonEvent e) {
    }

    /**
     *
     * @param array
     * @param value
     * @return
     */
    private static int getIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public void leftAngleChanged(XboxJoystickEvent e) {
    }
}
