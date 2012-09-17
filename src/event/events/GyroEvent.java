/** To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event.events;

import sensor.GRTGyro;

/**
 *
 * @author calvin
 */
public class GyroEvent extends SensorEvent {

    public GyroEvent(GRTGyro source, double rotation) {
        super(source, 0, rotation);
    }

    public double getAngle() {
        return getData();
    }
}
