package com.subsystem;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;

public class Drive {
	
	private CANTalon talon1;
	private CANTalon talon2;
	private CANTalon talon3;
	private CANTalon talon4;
	private RobotDrive drive;
	private Joystick ps3;
	
	public Drive() {
		talon1 = new CANTalon(1);
		talon2 = new CANTalon(2);
		talon3 = new CANTalon(3);
		talon4 = new CANTalon(4);
		drive = new RobotDrive(talon1, talon2, talon3, talon4);
		ps3 = new Joystick(0);
	}
	
	public void drive() {
		drive.tankDrive(ps3.getRawAxis(1), ps3.getRawAxis(3));
	}
}
