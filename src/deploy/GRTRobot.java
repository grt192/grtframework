/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package deploy;

import core.EventController;
import edu.wpi.first.wpilibj.SimpleRobot;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Abstract robot class.
 * 
 * Has sets of autonomous and teleop controllers, which are enabled
 * and disabled during autonomous and teleop periods, respectively.
 */
public abstract class GRTRobot extends SimpleRobot {

    private final Vector autoControllers;
    private final Vector teleopControllers;

    public GRTRobot() {
        autoControllers = new Vector();
        teleopControllers = new Vector();
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     * All Autonomous controllers are started/resumed, and all teleop controllers are paused.
     */
    public void autonomous() {
        for (Enumeration en = teleopControllers.elements(); en.hasMoreElements();) {
            ((EventController) en.nextElement()).disable();
        }
        for (Enumeration en = autoControllers.elements(); en.hasMoreElements();) {
            ((EventController) en.nextElement()).enable();
        }

    }

    /**
     * This function is called once each time the robot enters operator control.
     * All Teleop controllers are started/resumed, and all autonomous controllers are paused.
     */
    public void operatorControl() {
        for (Enumeration en = autoControllers.elements(); en.hasMoreElements();) {
            ((EventController) en.nextElement()).disable();
        }
        for (Enumeration en = teleopControllers.elements(); en.hasMoreElements();) {
            ((EventController) en.nextElement()).enable();
        }
        
    }

    public void addAutonomousController(EventController c) {
        autoControllers.addElement(c);
    }

    public void addTeleopController(EventController c) {
        teleopControllers.addElement(c);
    }
}
