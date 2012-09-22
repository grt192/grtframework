/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.Sensor;
import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.Gyro;
import event.events.GyroEvent;
import event.listeners.GyroListener;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Provides angular position along a single axis through an analog sensor
 * 
 * @author Calvin
 */
public class GRTGyro extends Sensor {

    public static final int KEY_ANGLE = 0;
    public static final int NUM_DATA = 1;
    private Gyro gyro;
    private Vector gyroListeners;

    public GRTGyro(int channel, int pollTime, String name) {
        super(name, pollTime, NUM_DATA);
        gyro = new Gyro(AnalogModule.getDefaultAnalogModule(), channel);
        gyroListeners = new Vector();
    }
    
    public double getAngle(){
        return gyro.getAngle();
    }

    protected void poll() {
        setState(KEY_ANGLE, gyro.getAngle());
    }

    protected void notifyListeners(int id, double oldDatum, double newDatum) {
        if (id == KEY_ANGLE) {
            GyroEvent e = new GyroEvent(this, newDatum);
            for (Enumeration en = gyroListeners.elements(); en.hasMoreElements();) {
                ((GyroListener) en.nextElement()).angleChanged(e);
            }
        }
    }

    public void addListener(GyroListener l) {
        gyroListeners.addElement(l);
    }

    public void removeListener(GyroListener l) {
        gyroListeners.removeElement(l);
    }
}
