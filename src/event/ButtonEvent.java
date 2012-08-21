/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import core.Sensor;

/**
 *
 * @author ajc
 */
public class ButtonEvent {
    private final Sensor source;
    private final int id;
    private final boolean pressed;
    
    public ButtonEvent(Sensor source, int id, boolean pressed){
        this.source = source;
        this.id = id;
        this.pressed = pressed;
    }
    
    public Sensor getSource(){
        return source;
    }
    
    public int getButtonID(){
        return id;
    }
    
    public boolean isPressed(){
        return pressed;
    }
    
    public boolean isReleased(){
        return !pressed;
    }
    
}
