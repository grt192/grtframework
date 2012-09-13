/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package event.listeners;

import event.events.ButtonEvent;


/**
 *
 * @author anand
 */
public interface ButtonListener {
    
    public void buttonPressed(ButtonEvent e);
    
    public void buttonReleased(ButtonEvent e);
}
