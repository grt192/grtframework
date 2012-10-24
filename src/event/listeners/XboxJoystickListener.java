package event.listeners;

import event.events.XboxJoystickEvent;

/**
 *
 * @author student
 */
public interface XboxJoystickListener {
    
    public void leftXAxisMoved(XboxJoystickEvent e);
    
    public void leftYAxisMoved(XboxJoystickEvent e);
    
    public void leftAngleChanged(XboxJoystickEvent e);

    public void rightXAxisMoved(XboxJoystickEvent e);
    
    public void rightYAxisMoved(XboxJoystickEvent e);

    public void padMoved(XboxJoystickEvent e);

    public void triggerMoved(XboxJoystickEvent e);
}