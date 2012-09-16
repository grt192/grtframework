/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import core.Sensor;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import event.events.ADXL345Event;
import event.listeners.ADXL345Listener;
import java.util.Vector;
/**
 * Wrapper for the ADXL345 accelerometer.
 * Measures X, Y, and Z accelerations in G's
 * (doesn't account for gravity)
 * 
 * @author gerberduffy
 */
public class GRTADXL345  extends Sensor{
    
    private ADXL345_I2C accelerometer;
    
    private static final int X_AXIS = 0;
    private static final int Y_AXIS = 1;
    private static final int Z_AXIS = 2;
    private static final int NUM_DATA = 3;
    
    private Vector listeners;
    
    public GRTADXL345(int slot, int pollTime, String id){
        super (id, pollTime, NUM_DATA);
        accelerometer = new ADXL345_I2C(slot, ADXL345_I2C.DataFormat_Range.k2G);
        
        listeners = new Vector();
    }

    protected void poll() {
        setState(X_AXIS, accelerometer.getAcceleration(ADXL345_I2C.Axes.kX));
        setState(Y_AXIS, accelerometer.getAcceleration(ADXL345_I2C.Axes.kY));
        setState(Z_AXIS, accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ));
        
        System.out.println("ADXL345:\t" + getState(X_AXIS) +
                "\t" + getState(Y_AXIS) +
                "\t" + getState(Z_AXIS));
    }
    
    public void addADXL345Listener(ADXL345Listener l){
        listeners.addElement(l);
    }
    
    public void removeADXL345Listener(ADXL345Listener l){
        listeners.removeElement(l);
    }

    protected void notifyListeners(int id, double oldDatum, double newDatum) {
        ADXL345Event e = new ADXL345Event(this, id, newDatum);
        
        switch (id){
            case X_AXIS: {
                for (int i=0; i < listeners.size(); i++){
                    ((ADXL345Listener)listeners.elementAt(i)).XAccelChange(e);
                }
            }
            
            case Y_AXIS: {
                for (int i=0; i < listeners.size(); i++){
                    ((ADXL345Listener)listeners.elementAt(i)).YAccelChange(e);
                }
            }
            
            case Z_AXIS: {
                for (int i=0; i < listeners.size(); i++){
                    ((ADXL345Listener)listeners.elementAt(i)).ZAccelChange(e);
                }
            }
        }
    }
    
}
