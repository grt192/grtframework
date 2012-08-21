/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import sensor.GRTEncoder;

/**
 *
 * @author gerberduffy
 */
public class EncoderEvent {
    
    public static final int ROTATION_STARTED = 0;
    public static final int ROTATION_STOPPED = 1;
    public static final int DEGREE_CHANGE = 2;
    
    public EncoderEvent(GRTEncoder source){
        
    }
    
}
