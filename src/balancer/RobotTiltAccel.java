/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//DO NOT USE--use robotTiltGyro instead
package balancer;

import com.sun.squawk.util.MathUtils;
import core.Sensor;
import event.ADXL345Event;
import event.ADXL345Listener;
import event.RobotTiltEvent;
import event.RobotTiltListener;
import java.util.Vector;
import sensor.GRTADXL345;

/**
 *
 * @author calvin
 */
public class RobotTiltAccel extends Sensor implements ADXL345Listener {

    private Vector robotTiltListeners;
    private static final int NUM_DATA = 1;//TODO change
    public static final int KEY_ANGLE = 0;
    private double angle;
    private double xAccel;
    private double yAccel;
    private double zAccel;
    private final GRTADXL345 accelerometer;

    public RobotTiltAccel(GRTADXL345 accelerometer, String name) {
        super(name);
        this.accelerometer = accelerometer;
        robotTiltListeners = new Vector();
    }

//    protected void notifyListeners(int id, double oldDatum, double newDatum) {
//        RobotTiltEvent e = new RobotTiltEvent(this, id, newDatum);
//        for (int i = 0; i < robotTiltListeners.size(); i++) {
//            ((RobotTiltListener) robotTiltListeners.elementAt(i)).RobotTiltChange(e);
//        }
//    }

    private void updateAngle() {
        //magnitude of vector sum of x, y accelerations
        double normalDeviation = Math.sqrt(((xAccel) * (xAccel))
                + ((yAccel) * (yAccel)));
//        double oldAngle = angle;
        angle = MathUtils.atan(normalDeviation / zAccel);
        //angle is angle between acceleration vector and z axis
        notifyStateChange(KEY_ANGLE, angle);
    }

    public double getTilt() {
        return angle;
    }

    public void addRobotTiltListeners(RobotTiltListener l) {
        robotTiltListeners.addElement(l);
    }

    public void removeRobotTiltListeners(RobotTiltListener l) {
        robotTiltListeners.removeElement(l);
    }

    //@Override
    protected void startListening() {
        accelerometer.addADXL345Listener(this);
    }

    protected void stopListening() {
        accelerometer.removeADXL345Listener(this);
    }

    public void XAccelChange(ADXL345Event e) {
        xAccel = e.getAcceleration();
        updateAngle();
    }

    public void YAccelChange(ADXL345Event e) {
        yAccel = e.getAcceleration();
        updateAngle();
    }

    public void ZAccelChange(ADXL345Event e) {
        zAccel = e.getAcceleration();
        updateAngle();
    }
}