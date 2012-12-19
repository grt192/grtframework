package event.events;

import sensor.base.GRTDriverStation;

/**
 * Event specifying the speed of either the left or right side of the robot.
 *
 * @author ajc
 */
public class ShiftEvent extends SensorEvent {

	public static final int KEY_SHIFT_UP = 1;
	public static final int KEY_SHIFT_DOWN = 0;
	
    /**
     * Creates a new DrivingEvent.
     *
     * @param source source of event
     * @param sideID left or right side
     * @param shiftDirection the key indicating shift up or down
     */
    public ShiftEvent(GRTDriverStation source, int sideID, double shiftDirection) {
        super(source, sideID, shiftDirection);
    }

    /**
     * Whether or not this is a left side event or a right side event.
     *
     * @return DrivingEvent.SIDE_LEFT or DrivingEvent.SIDE_RIGHT
     */
    public int getSide() {
        return getID();
    }

    /**
     * Returns the speed specified by this event.
     *
     * @return speed of side, as number from -1 to 1
     */
    public double getShiftDirection() {
        return getData();
    }
}
