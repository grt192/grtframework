package event.listeners;

import event.events.EncoderEvent;

/**
 *
 * @author gerberduffy
 */
public interface EncoderListener {

    public void rotationStarted(EncoderEvent e);

    public void degreeChanged(EncoderEvent e);
    
    public void distanceChanged(EncoderEvent e);

    public void rotationStopped(EncoderEvent e);
}
