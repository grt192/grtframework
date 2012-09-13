/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event.listeners;

import event.events.DrivingProfileEvent;

/**
 *
 * @author ajc
 */
public interface DrivingProfileListener {

    public void drivingProfileChanged(DrivingProfileEvent e);
}
