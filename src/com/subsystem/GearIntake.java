package com.subsystem;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Talon;

public class GearIntake extends Manipulator {
	// TODO: ask about sensor
	// TODO: limit switches?
	// TODO: servos?
	// TODO: auto turn off/operation with sensor
	CANTalon pickup;
	//CANTalon conveyor;
	Talon conveyor;
	
	public GearIntake(Joystick ps3, Joystick stick2) {
		super(ps3, stick2);
		pickup = new CANTalon(6);
		conveyor = new Talon(0);
		//conveyor = new CANTalon(7);
	}
	
	public void intake() {
		pickup();
		conveyor();
	}
	
	private void pickup() {
		pickup.set(stick2.getRawAxis(3));
	}
	
	private void conveyor() {
		conveyor.set(stick2.getRawAxis(2));
	}
	
	public void autoPickup(double speed) {
		if(Math.abs(speed) < 1) {
			pickup.set(speed);
		}
	}
	
	public void autoConveyor(double speed) {
		if(Math.abs(speed) < 1) {
			conveyor.set(speed);
		}
	}
}
