package org.usfirst.frc.team1647.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import com.subsystem.*;

/**
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends SampleRobot {
	
	Drive drive = new Drive();
	
	public Robot() {

	}

	@Override
	public void robotInit() {

	}

	@Override
	public void autonomous() {

	}

	@Override
	public void operatorControl() {
		drive.drive();
	}

	@Override
	public void test() {

	}
}
