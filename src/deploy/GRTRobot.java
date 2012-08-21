/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package deploy;

import core.EventController;
import edu.wpi.first.wpilibj.SimpleRobot;
import java.util.Vector;

/**
 *
 * GRTRobot is the entry point.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public abstract class GRTRobot extends SimpleRobot {

    private Vector autoControllers;
    private Vector teleopControllers;

    public GRTRobot() {
        autoControllers = new Vector();
        teleopControllers = new Vector();

    }

    /**
     * This function is called once each time the robot enters autonomous mode.
     * All Autonomous controllers are started/resumed, and all teleop controllers are paused.
     */
    public void autonomous() {
        for (int i = 0; i < teleopControllers.size(); i++) {
            ((EventController) teleopControllers.elementAt(i)).disable();
        }
        for (int i = 0; i < autoControllers.size(); i++) {
            ((EventController) autoControllers.elementAt(i)).enable();
        }

    }

    /**
     * This function is called once each time the robot enters operator control.
     * All Teleop controllers are started/resumed, and all autonomous controllers are paused.
     */
    public void operatorControl() {
        for (int i = 0; i < autoControllers.size(); i++) {
            ((EventController) autoControllers.elementAt(i)).disable();
        }
        for (int i = 0; i < teleopControllers.size(); i++) {
            ((EventController) teleopControllers.elementAt(i)).enable();
        }
        
    }

    public void addAutonomousController(EventController c) {
        autoControllers.addElement(c);
    }

    public void addTeleopController(EventController c) {
        teleopControllers.addElement(c);
    }
}
