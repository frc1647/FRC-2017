package com.subsystem;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.AnalogInput;

public class Lift extends Manipulator {
	// TODO: ask about sensor/limit switch for top
	// TODO: auto operation
	CANTalon lift;
	AnalogInput pot;
	
	public Lift(Joystick ps3, Joystick stick2) {
		super(ps3, stick2);
		lift = new CANTalon(5);
		pot = new AnalogInput(0);
	}
	
	public double getPot() {
		return pot.getVoltage();
	}
	
	public void lift() {
		lift.set(stick2.getRawAxis(1));
	}
}