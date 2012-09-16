/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

import event.events.ButtonEvent;
import event.events.XboxJoystickEvent;
import event.listeners.ButtonListener;
import event.listeners.XboxJoystickListener;
import sensor.GRTXBoxJoystick;

/**
 * Driverstation using XBoxJoysticks
 *
 * @author ajc
 */
public class GRTXboxDriverStation extends GRTDriverStation
        implements XboxJoystickListener, ButtonListener {

    private final GRTXBoxJoystick primary;
    private final GRTXBoxJoystick secondary;

    /**
     * Creates a new driver station using XBox controllers.
     * 
     * @param primary primary controller.
     * @param secondary secondary controller.
     * @param name name of driver station.
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
        }
    }

    public void rightXAxisMoved(XboxJoystickEvent e) {
    }

    public void rightYAxisMoved(XboxJoystickEvent e) {
        if (e.getSource() == primary) {
            notifyRightDriveSpeed(e.getValue());
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

    public void leftAngleChanged(XboxJoystickEvent e) {
    }
}
