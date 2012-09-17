/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanism;

import sensor.GRTBatterySensor;

/**
 * Encapsulates all components on the robot base.
 * 
 * @author ajc
 */
public class GRTRobotBase {

    private final GRTDriveTrain dt;
    private final GRTBatterySensor s;

    /**
     * Constructs a new robot base.
     * 
     * @param dt robot drivetrain
     * @param s battery sensor
     */
    public GRTRobotBase(GRTDriveTrain dt, GRTBatterySensor s) {
        this.dt = dt;
        this.s = s;
    }

    /**
     * Returns the drivetrain
     * 
     * @return drivetrain of this base
     */
    public GRTDriveTrain getDriveTrain() {
        return dt;
    }
    
    /**
     * Returns the battery sensor
     * 
     * @return battery sensor of this base
     */
    public GRTBatterySensor getBatterySensor(){
        return s;
    }
    
    /**
     * Drives the robot.
     * 
     * @param leftVelocity velocity of left drivetrain
     * @param rightVelocity velocity of right drivetrain
     */
    public void tankDrive(double leftVelocity, double rightVelocity){
        dt.tankDrive(leftVelocity, rightVelocity);
    }
}
