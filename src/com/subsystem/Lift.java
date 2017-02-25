package com.subsystem;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

public class Lift extends Manipulator {

	// TODO: auto operation
	CANTalon lift;
	AnalogInput pot;
	AnalogInput lightSensor;
	DigitalInput limitSwitch1;
	DigitalInput limitSwitch2;
	
	public Lift(Joystick ps3, Joystick stick2) {
		super(ps3, stick2);
		lift = new CANTalon(5);
		pot = new AnalogInput(0);
		lightSensor = new AnalogInput(3);
	}
	
	public double getPot() {
		return pot.getVoltage();
	}
	
	public double getLightSensor() {
		return lightSensor.getVoltage();
	}
	
	public double getEncoderPosition() {
		return lift.getEncPosition(); 
	}
	
	public void lift() {
		lift.set(-Math.abs(stick2.getRawAxis(1)));
	}
}