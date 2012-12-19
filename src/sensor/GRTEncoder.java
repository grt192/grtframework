package sensor;

import core.Sensor;
import edu.wpi.first.wpilibj.Encoder;
import event.events.EncoderEvent;
import event.listeners.EncoderListener;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Wrapper class for a quadrature encoder.
 *
 * @author gerberduffy
 */
public class GRTEncoder extends Sensor {

    private Encoder rotaryEncoder;
    private double distancePerPulse;
    public static final int DISTANCE = 0;
    public static final int DEGREES = 1;
    public static final int DIRECTION = 2;
    public static final int STOPPED = 3;
    public static final int NUM_DATA = 4;
    private Vector encoderListeners;

    /**
     * Instantiates an encoder on the default digital module.
     *
     * @param channelA digital channel for encoder channel A
     * @param channelB digital channel for encoder channel B
     * @param pulseDistance distance traveled for each pulse (typically 1 degree
     * of rotation per pulse)
     * @param pollTime how often to poll
     * @param name name of encoder
     */
    public GRTEncoder(int channelA, int channelB,
            double pulseDistance, int pollTime, String name) {
        super(name, pollTime, NUM_DATA);
        rotaryEncoder = new Encoder(channelA, channelB);
        rotaryEncoder.start();

        distancePerPulse = pulseDistance;
    }

    /**
     * Instantiates an encoder.
     *
     * @param moduleNum number of digital module
     * @param channelA digital channel for encoder channel A
     * @param channelB digital channel for encoder channel B
     * @param pulseDistance distance traveled for each pulse (typically 1 degree
     * of rotation per pulse)
     * @param pollTime how often to poll
     * @param name name of encoder
     */
    public GRTEncoder(int moduleNum, int channelA, int channelB,
            double pulseDistance, int pollTime, String name) {
        super(name, pollTime, NUM_DATA);
        rotaryEncoder = new Encoder(moduleNum, channelA, moduleNum, channelB);
        rotaryEncoder.start();

        distancePerPulse = pulseDistance;
    }

    protected void poll() {
        setState(DISTANCE, rotaryEncoder.getDistance());
        setState(DEGREES, rotaryEncoder.getDistance() / distancePerPulse);
        setState(DIRECTION, rotaryEncoder.getDirection() ? TRUE : FALSE);
        setState(STOPPED, rotaryEncoder.getStopped() ? TRUE : FALSE);
    }

    protected void notifyListeners(int id, double newDatum) {
        EncoderEvent e = new EncoderEvent(this, id, newDatum);

        switch (id) {
            case DEGREES:
                for (Enumeration en = encoderListeners.elements(); en.
                        hasMoreElements();)
                    ((EncoderListener) en.nextElement()).degreeChanged(e);
                break;
            case DISTANCE:
                for (Enumeration en = encoderListeners.elements(); en.
                        hasMoreElements();)
                    ((EncoderListener) en.nextElement()).distanceChanged(e);
                break;
            case STOPPED:
                if (newDatum == TRUE)
                    for (Enumeration en = encoderListeners.elements(); en.
                            hasMoreElements();)
                        ((EncoderListener) en.nextElement()).rotationStopped(e);
                else
                    for (Enumeration en = encoderListeners.elements(); en.
                            hasMoreElements();)
                        ((EncoderListener) en.nextElement()).rotationStarted(e);
                break;
        }
    }

    public void addListener(EncoderListener l) {
        encoderListeners.addElement(l);
    }

    public void removeListener(EncoderListener l) {
        encoderListeners.removeElement(l);
    }
}
