/** To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import sensor.GRTGyro;

/**
 *
 * @author calvin
 */
public class GyroEvent {

    private GRTGyro source;
    private double rotation;

    public GyroEvent(GRTGyro source, double rotation) {
        this.source = source;
        this.rotation = rotation;
    }

    public double getAngle() {
        return rotation;
    }

    public GRTGyro getSource() {
        return source;
    }
}
