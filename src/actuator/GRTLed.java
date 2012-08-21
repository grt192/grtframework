/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actuator;

import core.Actuator;
import edu.wpi.first.wpilibj.PWM;

/**
 * Abstraction of a standard LED
 * @author gerberduffy
 */
public class GRTLed extends Actuator {

    private static final int DEFAULT_BRIGHTNESS = 255;
    private static final int HIGHEST_BRIGHTNESS = 255;
    private static final int OFF_BRIGHTNESS = 0;
    
    
    private final PWM led;      //The PWM input controlling the LED
    private int brightness;
    
    public GRTLed(int channel, String id){
        this(channel, DEFAULT_BRIGHTNESS, id);
    }
    
    public GRTLed(int channel, int brightness, String id){
        super(id);
        
        this.brightness = brightness;
        
        led = new PWM(channel);
        led.setRaw(brightness);
    }
    
    public void setToBrightness(int bright){
        if (bright > HIGHEST_BRIGHTNESS){
            bright = HIGHEST_BRIGHTNESS;
        } else if (bright < OFF_BRIGHTNESS){
            bright = OFF_BRIGHTNESS;
        } 
        
        led.setRaw(bright);
        
        this.brightness = bright;
    }
    
    public void toggleState(){
        if (isOn()){
            led.setRaw(OFF_BRIGHTNESS);
            brightness = OFF_BRIGHTNESS;
            
        } else {
            led.setRaw(HIGHEST_BRIGHTNESS);
            brightness = HIGHEST_BRIGHTNESS;            
        }
    }
    
    public int getBrightness(){
        return this.brightness;
    }
    
    public boolean isOn(){
        return this.brightness > OFF_BRIGHTNESS;
    }
    
    public void executeCommand(double command) {
        led.setRaw((int) command);
        brightness = (int) command;
    }
    
}
