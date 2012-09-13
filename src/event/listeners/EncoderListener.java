/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event.listeners;

import event.events.EncoderEvent;

/**
 *
 * @author gerberduffy
 */
public interface EncoderListener {
    
    public void rotationStarted(EncoderEvent e);
    
    public void degreeChanged(EncoderEvent e);
    
    public void rotationStopped(EncoderEvent e);
    
}