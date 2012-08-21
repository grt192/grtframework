/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;
import sensor.GRTAttack3Joystick;
/**
 *
 * @author dan
 */
public class Attack3JoystickEvent {
    public static final int DEFAULT = 0;
    private int id;
    private double value;
    private GRTAttack3Joystick source;
    
    public Attack3JoystickEvent(GRTAttack3Joystick source, int id, double value){
        this.source = source;
        this.id = id;
        this.value = value;
    }
    public int getId() {
        return id;
    }
    public GRTAttack3Joystick getSource(){
        return source;
    }
    public double getValue() {
        return value;
    }
}
