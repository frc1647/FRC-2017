package com.subsystem;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.AnalogInput;

public class Lift extends Manipulator {
	// TODO: ask about sensor/limit switch for top
	// TODO: auto operation
	CANTalon lift;
	AnalogInput pot;
	AnalogInput colorSensor;
	
	public Lift(Joystick ps3, Joystick stick2) {
		super(ps3, stick2);
		lift = new CANTalon(5);
		pot = new AnalogInput(0);
		colorSensor = new AnalogInput(3);
	}
	
	public double getPot() {
		return pot.getVoltage();
	}
	
	public double getColorSensor() {
		return colorSensor.getVoltage();
	}
	
	public double getEncoderPosition() {
		return lift.getEncPosition(); 
	}
	
	public void lift() {
		lift.set(stick2.getRawAxis(1));
		System.out.println("Lift Enc x: " + getEncoderPosition());
	}
}