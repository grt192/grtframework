package event.listeners;

import event.events.ADXL345Event;

/**
 *
 * @author calvin
 */
public interface ADXL345Listener {

    public void XAccelChange(ADXL345Event e);

    public void YAccelChange(ADXL345Event e);

    public void ZAccelChange(ADXL345Event e);
}
