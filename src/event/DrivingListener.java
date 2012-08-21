/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

/**
 * A high level interface for robot driving 
 * @author ajc
 */
public interface DrivingListener {
    
    /**
     * Called to set speed of left drivetrain
     * @param e 
     */
    public void driverLeftSpeed(DrivingEvent e);
    
    /**
     * Called to set speed of right drivetrain
     * @param e 
     */
    public void driverRightSpeed(DrivingEvent e);
    
}
