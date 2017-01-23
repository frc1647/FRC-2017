package com.subsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

public class GearOutput extends Manipulator {
	
	Solenoid openGearDoor;
	Solenoid pushGearDoor;
	boolean openGearDoorState;
	boolean pushGearDoorState;
	
	public GearOutput(Joystick ps3, Joystick stick2) {
		super(ps3, stick2);
		openGearDoor = new Solenoid(1);
		pushGearDoor = new Solenoid(2);
		openGearDoorState = false;
		pushGearDoorState = false;
	}
	
	private void openGearDoor() {
		openGearDoorState = toggleSolenoid(openGearDoor, ps3.getRawButton(1), openGearDoorState);
	}
	
	private void pushGearDoor() {
		pushGearDoorState = toggleSolenoid(pushGearDoor, ps3.getRawButton(2), pushGearDoorState);
	}
	
	public void output() {
		openGearDoor();
		pushGearDoor();
	}
	
	public boolean getOpenGearDoorState() {
		return openGearDoorState;
	}
	
	public boolean getPushGearDoorState() {
		return pushGearDoorState;
	}
}
