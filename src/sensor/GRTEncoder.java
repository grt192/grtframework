/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.Sensor;
import edu.wpi.first.wpilibj.Encoder;

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
    
    public static final int NUM_DATA = 3;
    
    /**
     * Instantiates an encoder on the default digital module.
     * 
     * @param channelA digital channel for encoder channel A
     * @param channelB digital channel for encoder channel B
     * @param pulseDistance distance traveled for each pulse
     * (typically 1 degree of rotation per pulse)
     * @param pollTime how often to poll
     * @param name name of encoder
     */
    public GRTEncoder(int channelA, int channelB,
            double pulseDistance, int pollTime, String name){
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
     * @param pulseDistance distance traveled for each pulse
     * (typically 1 degree of rotation per pulse)
     * @param pollTime how often to poll
     * @param name name of encoder
     */
    public GRTEncoder(int moduleNum, int channelA, int channelB,
            double pulseDistance, int pollTime, String name){
        super(name, pollTime, NUM_DATA);
        rotaryEncoder = new Encoder(moduleNum, channelA, moduleNum, channelB);
        rotaryEncoder.start();
        
        distancePerPulse = pulseDistance;
    }
    

    protected void poll() {
        setState(DISTANCE, rotaryEncoder.getDistance());
        setState(DEGREES, rotaryEncoder.getDistance() / distancePerPulse);
        setState(DIRECTION, rotaryEncoder.getDirection() ? TRUE : FALSE);
    }

    protected void notifyListeners(int id, double oldDatum, double newDatum) {
        //TODO
    }
    
}
