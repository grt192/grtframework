/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

/**
 *
 * @author dan
 */
public interface Attack3JoystickListener {
    public void XAxisMoved(Attack3JoystickEvent e);
    public void YAxisMoved(Attack3JoystickEvent e);
    public void AngleChanged(Attack3JoystickEvent e);
}
