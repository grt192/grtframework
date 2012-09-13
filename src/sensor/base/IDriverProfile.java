/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.base;

/**
 * An interface that scales controller input to motor output
 * 
 * @author ajc
 */
public interface IDriverProfile {

    /**
     * Scales controller input to motor output based on some function
     * 
     * @param controllerInput an input from the controller from [-1.0 - 1.0]
     * @return an output from [-1.0 - 1.0]
     */
    public double driveSpeed(double controllerInput);
}
