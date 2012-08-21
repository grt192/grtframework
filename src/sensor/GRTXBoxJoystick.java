/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.PollingSensor;
import edu.wpi.first.wpilibj.Joystick;
import event.ButtonEvent;
import event.ButtonListener;
import event.XboxJoystickEvent;
import event.XboxJoystickListener;
import java.util.Vector;

/**
 * 
 * @author ajc
 */
public class GRTXBoxJoystick extends PollingSensor {

    /**
     * Keys of data
     */
    public static final int KEY_BUTTON_0 = 0;
    public static final int KEY_BUTTON_1 = 1;
    public static final int KEY_BUTTON_2 = 2;
    public static final int KEY_BUTTON_3 = 3;
    public static final int KEY_BUTTON_4 = 4;
    public static final int KEY_BUTTON_5 = 5;
    public static final int KEY_BUTTON_6 = 6;
    public static final int KEY_BUTTON_7 = 7;
    public static final int KEY_BUTTON_8 = 8;
    public static final int KEY_BUTTON_9 = 9;
    public static final int KEY_LEFT_X = 10;
    public static final int KEY_LEFT_Y = 11;
    public static final int KEY_RIGHT_X = 12;
    public static final int KEY_RIGHT_Y = 13;
    public static final int KEY_JOYSTICK_ANGLE = 14;
    public static final int KEY_TRIGGER = 15;
    public static final int KEY_PAD = 16;
    
    private static final int NUM_DATA = 17;
    private static final int NUM_OF_BUTTONS = 10;

    /**
     * State definitions
     */
    public static final double PRESSED = TRUE;
    public static final double RELEASED = FALSE;

    private final Joystick joystick;
    private final Vector buttonListeners;
    private final Vector joystickListeners;

    public GRTXBoxJoystick(int channel, int pollTime, String name) {
        super(name, pollTime, NUM_DATA);
        joystick = new Joystick(channel);
        
        buttonListeners = new Vector();
        joystickListeners = new Vector();
    }

    protected void poll() {
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            //if we measure true, this indicates pressed state
            setState(i, joystick.getRawButton(i) ? PRESSED : RELEASED);
        }
        setState(KEY_LEFT_X, joystick.getX());
        setState(KEY_LEFT_Y, joystick.getY());
        setState(KEY_RIGHT_X, joystick.getRawAxis(4));
        setState(KEY_RIGHT_Y, joystick.getRawAxis(5));
        setState(KEY_JOYSTICK_ANGLE, joystick.getDirectionRadians());
        setState(KEY_TRIGGER, joystick.getZ());
        setState(KEY_PAD, joystick.getRawAxis(6));
    }

    protected void notifyListeners(int id, double oldDatum, double newDatum) {
        if (id < NUM_OF_BUTTONS) {
            //ID maps directly to button ID
            ButtonEvent e = new ButtonEvent(this, id, newDatum == PRESSED);
            if (newDatum == PRESSED) { //true
                for (int i = 0; i < buttonListeners.size(); i++) {
                    ((ButtonListener) buttonListeners.elementAt(i)).buttonPressed(e);
                }
            } else {
                for (int i = 0; i < buttonListeners.size(); i++) {
                    ((ButtonListener) buttonListeners.elementAt(i)).buttonReleased(e);
                }
            }

        } else { //we are now a joystick
            //only reach here if not a button
            XboxJoystickEvent e = new XboxJoystickEvent(this, id, newDatum);

            //call various events based on which datum we are
            switch (id) {
                case KEY_LEFT_X: {
                    for (int i = 0; i < joystickListeners.size(); i++) {
                        ((XboxJoystickListener) joystickListeners.elementAt(i)).leftXAxisMoved(e);
                    }

                }
                case KEY_LEFT_Y: {
                    for (int i = 0; i < joystickListeners.size(); i++) {
                        ((XboxJoystickListener) joystickListeners.elementAt(i)).leftYAxisMoved(e);
                    }
                    break;
                }
                case KEY_RIGHT_X: {
                    for (int i = 0; i < joystickListeners.size(); i++) {
                        ((XboxJoystickListener) joystickListeners.elementAt(i)).rightXAxisMoved(e);
                    }
                    break;
                }
                case KEY_RIGHT_Y: {
                    for (int i = 0; i < joystickListeners.size(); i++) {
                        ((XboxJoystickListener) joystickListeners.elementAt(i)).rightYAxisMoved(e);
                    }
                    break;
                }
                case KEY_JOYSTICK_ANGLE: {
                    for (int i = 0; i < joystickListeners.size(); i++) {
                        ((XboxJoystickListener) joystickListeners.elementAt(i)).leftAngleChanged(e);
                    }
                    break;
                }
                case KEY_TRIGGER: {
                    for (int i = 0; i < joystickListeners.size(); i++) {
                        ((XboxJoystickListener) joystickListeners.elementAt(i)).triggerMoved(e);
                    }
                    break;
                }
                case KEY_PAD: {
                    for (int i = 0; i < joystickListeners.size(); i++) {
                        ((XboxJoystickListener) joystickListeners.elementAt(i)).padMoved(e);
                    }
                    break;
                }

            }
        }


    }

    public void addButtonListener(ButtonListener b) {
        buttonListeners.addElement(b);
    }

    public void removeButtonListener(ButtonListener b) {
        buttonListeners.removeElement(b);
    }

    public void addJoystickListener(XboxJoystickListener l) {
        joystickListeners.addElement(l);
    }

    public void removeJoystickListener(XboxJoystickListener l) {
        joystickListeners.removeElement(l);
    }
}
