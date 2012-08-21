/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.PollingSensor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
/**
 *
 * @author gerberduffy
 */
public class GRTEncoder extends PollingSensor {

    private Encoder rotaryEncoder;
    private double distancePerPulse;
    
    public static final int DISTANCE = 0;
    public static final int DEGREES = 1;
    public static final int DIRECTION = 2;
    
    public static final int NUM_DATA = 3;
    
    public GRTEncoder(int channelA, int channelB, double pulseDistance, int pollTime, String id){
        super(id, pollTime, NUM_DATA);
        rotaryEncoder = new Encoder(channelA, channelB);
        rotaryEncoder.start();
        
        distancePerPulse = pulseDistance;
    }
    

    protected void poll() {
        setState(DISTANCE, rotaryEncoder.getDistance());
        setState(DEGREES, rotaryEncoder.getDistance()/distancePerPulse);
        setState(DIRECTION, rotaryEncoder.getDirection() ? TRUE : FALSE);
        
        System.out.println(getState(DISTANCE) + "\t" + getState(DEGREES) + "\t" + getState(DIRECTION));
    }

    protected void notifyListeners(int id, double oldDatum, double newDatum) {
    }
    
}
