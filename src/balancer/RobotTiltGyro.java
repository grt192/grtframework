/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balancer;
import core.Sensor;
import sensor.GRTGyro;
import event.GyroEvent;
import event.GyroListener;
import java.util.Vector;
import event.RobotTiltEvent;
import event.RobotTiltListener;

/**
 *
 * @author calvin
 */


public class RobotTiltGyro extends Sensor implements GyroListener{
    private Vector robotTiltListeners;
    private double angle;
    private double previousAngle;
    private GRTGyro g;
    
    public RobotTiltGyro(GRTGyro g, String name){
        super(name);
        this.g = g;
        robotTiltListeners = new Vector();
        angle = 0;
        previousAngle = 0;
    }
    public void angleChanged(GyroEvent e) {
        updateAngle();
    }
    
    private void updateAngle(){
        angle += g.getAngle() - previousAngle;
        previousAngle = g.getAngle();
        notifyStateChange(0, angle);
        notifyListeners(0, angle);
    }
    
    protected void notifyListeners(int id, double newDatum) {
        RobotTiltEvent e = new RobotTiltEvent(this, id, newDatum);
        for (int i = 0; i < robotTiltListeners.size(); i++) {
            ((RobotTiltListener) robotTiltListeners.elementAt(i)).RobotTiltChange(e);
        }


    }

    public void addRobotTiltListeners(RobotTiltListener l) {
        robotTiltListeners.addElement(l);
    }

    public void removeRobotTiltListeners(RobotTiltListener l) {
        robotTiltListeners.removeElement(l);
    }
    
    protected void startListening() {
        g.addListener(this);
    }

    protected void stopListening() {
        g.removeListener(this);
    }
}
