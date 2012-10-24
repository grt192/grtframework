package event.listeners;

import event.events.Attack3JoystickEvent;

/**
 *
 * @author dan
 */
public interface Attack3JoystickListener {

    public void XAxisMoved(Attack3JoystickEvent e);

    public void YAxisMoved(Attack3JoystickEvent e);

    public void AngleChanged(Attack3JoystickEvent e);
}
