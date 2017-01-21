package com.subsystem;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.CANTalon;

public class GearIntake extends Manipulator {
	CANTalon pickup;
	CANTalon conveyor;
	
	public GearIntake(Joystick ps3, Joystick stick2) {
		super(ps3, stick2);
		pickup = new CANTalon(6);
		conveyor = new CANTalon(7);
	}
	
	public void intake() {
		pickup();
		conveyor();
	}
	
	public void pickup() {
		pickup.set(stick2.getRawAxis(3));
	}
	
	public void conveyor() {
		conveyor.set(stick2.getRawAxis(4));
	}
}
