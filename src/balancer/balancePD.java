/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balancer;
import balancer.RobotTiltAccel;
import core.EventController;
import event.RobotTiltListener;
import event.RobotTiltEvent;
import mechanism.GRTRobotBase;


/**
 *
 * @author calvin
 */
public class balancePD extends EventController implements RobotTiltListener{
    
    private double previousAngle;
    private double currentAngle;
    private double deltaAngle;
    private double P_CONSTANT = 1;
    private double D_CONSTANT = 0;
    private final RobotTiltAccel robotTilt;
    private final GRTRobotBase base;
    
    public balancePD(GRTRobotBase base, RobotTiltAccel robotTilt, String name){
        super(name);
        this.base = base;
        this.robotTilt = robotTilt;
    }
    
//    public void startBalancing(){
//        startListening();
//    }
//    
//    public void stopBalancing(){
//        stopListening();
//    }

    protected void startListening() {
        robotTilt.addRobotTiltListeners(this);
    }

    protected void stopListening() {
        robotTilt.removeRobotTiltListeners(this);
    }

    public void RobotTiltChange(RobotTiltEvent e) {
        currentAngle = e.getTilt();
        deltaAngle = currentAngle - previousAngle;
        double drivePower = P_CONSTANT * currentAngle - D_CONSTANT * deltaAngle;
        base.tankDrive(drivePower, drivePower);
    }
    
    
    
}
