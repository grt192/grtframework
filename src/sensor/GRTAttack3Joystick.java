/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.PollingSensor;
import edu.wpi.first.wpilibj.Joystick;
import event.*;
import java.util.Vector;

/**
 *
 * @author dan
 */
public class GRTAttack3Joystick extends PollingSensor {
    private final Vector joystickListeners;
    private final Vector buttonListeners;
    private final Joystick joystick;
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
    public static final int KEY_X = 10;
    public static final int KEY_Y = 11;
    public static final int KEY_JOYSTICK_ANGLE = 12;
    private final static int NUM_OF_BUTTONS=10;
    private final static int NUM_DATA=13;
    public static final double PRESSED = TRUE;
    public static final double RELEASED = FALSE;

    public GRTAttack3Joystick(int channel, int pollTime, String name){
        super(name, pollTime, NUM_DATA);
        joystick = new Joystick(channel);
        joystickListeners = new Vector();
        buttonListeners = new Vector();
        
    }
    
    protected void poll() {
        for (int i = 0; i < NUM_OF_BUTTONS; ++i){
            setState(i, joystick.getRawButton(i)?PRESSED:RELEASED);
        }
        setState(KEY_X, joystick.getX());
        setState(KEY_Y, joystick.getY());
        setState(KEY_JOYSTICK_ANGLE, joystick.getDirectionRadians());
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
         Attack3JoystickEvent e = new Attack3JoystickEvent(this, id, newDatum);
         switch (id){
             case (KEY_X):
                 for (int i = 0; i < joystickListeners.size(); i++) {
                    ((Attack3JoystickListener)joystickListeners.elementAt(i)).XAxisMoved(e);
                 }
                 break;
             case (KEY_Y):
                 for (int i = 0; i < joystickListeners.size(); i++) {
                    ((Attack3JoystickListener)joystickListeners.elementAt(i)).YAxisMoved(e);
                 }
                 break;
             case (KEY_JOYSTICK_ANGLE):
                 for (int i = 0; i < joystickListeners.size(); i++) {
                    ((Attack3JoystickListener)joystickListeners.elementAt(i)).AngleChanged(e);
                 }
                 break;
         }
        }
    }
     public void addButtonListener(ButtonListener b) {
        buttonListeners.addElement(b);
    }

    public void removeButtonListener(ButtonListener b) {
        buttonListeners.removeElement(b);
    }

    public void addJoystickListener(Attack3JoystickListener l) {
        joystickListeners.addElement(l);
    }

    public void removeJoystickListener(Attack3JoystickListener l) {
        joystickListeners.removeElement(l);
    }
    
}
