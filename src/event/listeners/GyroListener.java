/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event.listeners;

import event.events.GyroEvent;

/**
 *
 * @author calvin
 */
public interface GyroListener {

    public void angleChanged(GyroEvent e);
}
