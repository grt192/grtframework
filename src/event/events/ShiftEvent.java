package event.events;

import sensor.base.GRTDriverStation;

/**
 * Event specifying the the gear of the right or left side of the robot.
 *
 * @author danfrei
 */
public class ShiftEvent extends SensorEvent {

    public static final int KEY_SHIFT_UP = 1;
    public static final int KEY_SHIFT_DOWN = 0;
    public static final int SIDE_LEFT = 0;
    public static final int SIDE_RIGHT = 1;

    /**
     * Creates a new ShiftEvent.
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
     * @return ShiftEvent.SIDE_LEFT or ShiftEvent.SIDE_RIGHT
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
