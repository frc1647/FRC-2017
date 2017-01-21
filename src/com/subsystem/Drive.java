package com.subsystem;

import com.ctre.CANTalon;
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
	
	
	public Drive(Joystick ps3, Joystick stick2) {
		super(ps3, stick2);
		talon1 = new CANTalon(1);
		talon2 = new CANTalon(2);
		talon3 = new CANTalon(3);
		talon4 = new CANTalon(4);
		drive = new RobotDrive(talon1, talon2, talon3, talon4);
		superShifter = new Solenoid(0);
	}
	
	public void drive() {
		// TODO: supershifter code
		drive.tankDrive(ps3.getRawAxis(1), ps3.getRawAxis(3));
		
	}
}
