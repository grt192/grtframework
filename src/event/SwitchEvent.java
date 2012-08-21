/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import core.PollingSensor;
import sensor.GRTSwitch;

/**
 *
 * @author gerberduffy
 */
public class SwitchEvent {
    
    private boolean state;
    private GRTSwitch sw;
    
    public SwitchEvent(GRTSwitch sw, double newState){
        state = newState == PollingSensor.TRUE;
        this.sw = sw;
    }
    
    public GRTSwitch getSource(){
        return this.sw;
    }
    
    public boolean getState(){
        return this.state;
    }
}
