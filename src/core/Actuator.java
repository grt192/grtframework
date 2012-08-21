/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 * An actuator is a driver for low level hardware that directly influences the 
 * physical world. Actuators can perform actions.
 * @author ajc
 */
public abstract class Actuator extends GRTLoggedProcess {
    
    
    public Actuator(String name){
        super(name);
    }
    
    /**
     * Performs an action. Actuator must be enabled for this to succeed.
     * @param command 
     */
    public abstract void executeCommand(double command);
    
}
