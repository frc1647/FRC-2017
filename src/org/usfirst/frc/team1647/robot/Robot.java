package org.usfirst.frc.team1647.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SerialPort;

import com.subsystem.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import com.kauailabs.navx.frc.*;
import com.autonomous.*;
import com.autonomous.Autonomous.Mode;

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
	GearIntake gearIntake;
	GearOutput gearOutput;
	Lift lift;
	Compressor air;
	Messenger sd;
	AHRS ahrs;
	DigitalInput switch1;
	DigitalInput switch2;
	DigitalInput switch3;
	Autonomous auto;
	
	public Robot() {
		ps3 = new Joystick(0);
		stick2 = new Joystick(1);
		drive = new Drive(ps3, stick2);
		gearIntake = new GearIntake(ps3, stick2);
		gearOutput = new GearOutput(ps3, stick2);
		lift = new Lift(ps3, stick2);
		air = new Compressor();
		sd = new Messenger();
		ahrs = new AHRS(SerialPort.Port.kMXP);
		switch1 = new DigitalInput(0);
		switch2 = new DigitalInput(1);
		switch3 = new DigitalInput(2);
		auto = new Autonomous(switch1, switch2, switch3, drive, gearOutput);
	}

	@Override
	public void robotInit() {
		air.start();
	}

	@Override
	public void autonomous() {
		auto.setSwitch1(switch1.get());
		auto.setSwitch2(switch2.get());
		auto.setSwitch3(switch3.get());
		auto.setAutoMode(auto.getSwitch1(), auto.getSwitch2(), auto.getSwitch3());
		Mode autoMode = auto.getMode();
		while(isAutonomous() && isEnabled()){
			auto.drive(autoMode);
			System.out.println("Switch 1: " + auto.getSwitch1() + "   Switch 2: " + auto.getSwitch2() + "   Switch3: " + auto.getSwitch3());
		}
	}

	@Override
	public void operatorControl() {
		while(isOperatorControl() && isEnabled()) {
			// TODO: finalize joystick axes
			drive.drive();
			gearIntake.intake();
			gearOutput.output();
			lift.lift();
			sd.setData(drive.getSuperShifterState(), gearOutput.getOpenGearDoorState(), gearOutput.getPushGearDoorState(), lift.getPot(), lift.getColorSensor());
			sd.putData();
		}
	}

	@Override
	public void test() {

	}
}
