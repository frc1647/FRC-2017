package com.subsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

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
		openGearDoorState = toggleSolenoid(openGearDoor, stick2.getRawButton(1), openGearDoorState);
	}
	
	private void pushGearDoor() {
		pushGearDoorState = toggleSolenoid(pushGearDoor, stick2.getRawButton(2), pushGearDoorState);
	}
	
	public void autoOpenGearDoor(boolean state) {
		openGearDoorState = toggleSolenoid(openGearDoor, state, openGearDoorState);
	}
	
	public void autoPushGearDoor(boolean state) {
		pushGearDoorState = toggleSolenoid(pushGearDoor, state, pushGearDoorState);
		
	}

	public void output() {
		openGearDoor();
		pushGearDoor();
	}
	
	public void autoOutput() {
		autoOpenGearDoor(!getOpenGearDoorState());
		Timer.delay(0.1);
		autoPushGearDoor(!getPushGearDoorState());
	}
	
	public boolean getOpenGearDoorState() {
		return openGearDoorState;
	}
	
	public boolean getPushGearDoorState() {
		return pushGearDoorState;
	}
}
