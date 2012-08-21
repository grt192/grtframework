/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.PollingSensor;
import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.Gyro;
import java.util.Vector;
import event.GyroEvent;
import event.GyroListener;

/**
 * Provides angular position along a single axis through an analog sensor
 * @author calvin
 */
public class GRTGyro extends PollingSensor {

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
        
        System.out.println("Gyro: \t" +  getState(KEY_ANGLE));
    }

    protected void notifyListeners(int id, double oldDatum, double newDatum) {
        if (id == KEY_ANGLE) {
            GyroEvent e = new GyroEvent(this, newDatum);
            for (int i = 0; i < gyroListeners.size(); i++) {
                ((GyroListener) gyroListeners.elementAt(i)).angleChanged(e);
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
