package org.usfirst.frc.team1647.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import com.subsystem.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;

/**
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends SampleRobot {
	Joystick ps3;
	Joystick stick2;
	Drive drive;
	GearOutput gearOutput;
	Compressor air;
	
	public Robot() {
		ps3 = new Joystick(0);
		stick2 = new Joystick(1);
		drive = new Drive(ps3, stick2);
		gearOutput = new GearOutput(ps3, stick2);
		air = new Compressor();
	}

	@Override
	public void robotInit() {
		air.start();
	}

	@Override
	public void autonomous() {

	}

	@Override
	public void operatorControl() {
		while(isOperatorControl() && isEnabled()) {
			drive.drive();
			gearOutput.output();
		}
	}

	@Override
	public void test() {

	}
}
