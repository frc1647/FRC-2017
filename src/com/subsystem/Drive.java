package com.subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

public class Drive extends Subsystem {
	
	private CANTalon talon1;
	private CANTalon talon2;
	private CANTalon talon3;
	private CANTalon talon4;
	private Solenoid superShifter;
	private RobotDrive drive;
	private boolean superShifterState;
	
	
	public Drive(Joystick ps3, Joystick stick2) {
		super(ps3, stick2);
		talon1 = new CANTalon(1);
		talon2 = new CANTalon(2);
		talon3 = new CANTalon(3);
		talon4 = new CANTalon(4);
		drive = new RobotDrive(talon1, talon2, talon3, talon4);
		superShifter = new Solenoid(0);
		superShifterState = false;
	}
	
	public void drive() {
		//drive.tankDrive(transformJoyInput(ps3.getRawAxis(1)), transformJoyInput(ps3.getRawAxis(3)));
		drive.tankDrive(transformJoyInput(-ps3.getRawAxis(3)), transformJoyInput(-ps3.getRawAxis(1)));
		superShifterState = toggleSolenoid(superShifter, ps3.getRawButton(5), superShifterState);
	}
	
	public void drive(double leftSpeed, double rightSpeed) {
		if(Math.abs(leftSpeed) <= 1 && Math.abs(rightSpeed) <= 1) {
			drive.tankDrive(-leftSpeed, -rightSpeed);
		} else {
			drive.tankDrive(0, 0);
		}
	}
	
	public boolean getSuperShifterState() {
		return superShifterState;
	}
}
