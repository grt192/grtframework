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

    private static final int MAX_BRIGHTNESS = 255;
	private static final int DEFAULT_BRIGHTNESS = MAX_BRIGHTNESS;
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
	
	public GRTLed(int channel, double brightness, String id){
        this(channel, (int) brightness * MAX_BRIGHTNESS, id);
    }
    
	/**
	 * Sets the brightness of this LED
	 * @param bright brightness of LED by varying duty cycle (0-255)
	 */
    public void setToBrightness(int bright){
        if (bright > MAX_BRIGHTNESS){
            bright = MAX_BRIGHTNESS;
        } else if (bright < OFF_BRIGHTNESS){
            bright = OFF_BRIGHTNESS;
        } 
        
        led.setRaw(bright);
        
        this.brightness = bright;
    }
    
	/**
	 * Turns LED off if it is on, and vice versa.
	 */
    public void toggleState(){
        if (isOn()){
            led.setRaw(OFF_BRIGHTNESS);
            brightness = OFF_BRIGHTNESS;
            
        } else {
            led.setRaw(MAX_BRIGHTNESS);
            brightness = MAX_BRIGHTNESS;            
        }
    }
    
	/**
	 * Gives the current brightness of the LED
	 * @return brightness (0-255)
	 */
    public int getBrightness(){
        return this.brightness;
    }
    
	/**
	 * Returns true iff brightness is greater than 0.
	 * @return true if on, false if off
	 */
    public boolean isOn(){
        return this.brightness > OFF_BRIGHTNESS;
    }
    
    public void executeCommand(double command) {
        brightness = (int) command * MAX_BRIGHTNESS;
		led.setRaw(brightness);
        
    }
    
}
