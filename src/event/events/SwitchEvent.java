/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event.events;

import core.Sensor;
import sensor.GRTSwitch;

/**
 *
 * @author gerberduffy
 */
public class SwitchEvent {
    
    private boolean state;
    private GRTSwitch sw;
    
    public SwitchEvent(GRTSwitch sw, double newState){
        state = newState == Sensor.TRUE;
        this.sw = sw;
    }
    
    public GRTSwitch getSource(){
        return this.sw;
    }
    
    public boolean getState(){
        return this.state;
    }
}
