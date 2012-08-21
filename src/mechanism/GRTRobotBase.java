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

    public GRTRobotBase(GRTDriveTrain dt, GRTBatterySensor s) {
        this.dt = dt;
        this.s = s;
    }

    public GRTDriveTrain getDriveTrain() {
        return dt;
    }
    
    public GRTBatterySensor getBatterySensor(){
        return s;
    }
    
    public void tankDrive(double leftVelocity, double rightVelocity){
        dt.tankDrive(leftVelocity, rightVelocity);
    }
}
