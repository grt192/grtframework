/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanism;

import actuator.GRTSolenoid;
import actuator.Motor;

/**
 *
 * @author student
 */
public class ShiftingDriveTrain extends GRTDriveTrain {
	private final GRTSolenoid leftShifter;
	private final GRTSolenoid rightShifter;
	
	public ShiftingDriveTrain(Motor leftFront, Motor leftBack,
			Motor rightFront, Motor rightBack, GRTSolenoid leftShifter,
			GRTSolenoid rightShifter) {
		super(leftFront, leftBack, rightFront, rightBack);
		this.leftShifter = leftShifter;
		this.rightShifter = rightShifter;
	}
	
	public void shiftUp() {
		leftShifter.engage(false);
		rightShifter.engage(false);
	}
	
	public void shiftDown() {
		leftShifter.engage(true);
		rightShifter.engage(true);
	}
}
