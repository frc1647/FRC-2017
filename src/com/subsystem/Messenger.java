package com.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.DriverStation;

public class Messenger {
	boolean gear;
	boolean openGearDoor;
	boolean pushGearDoor;
	double liftPot;
	
	public Messenger() {
		DriverStation.getInstance();
	}
	
	public void putData() {
		try {
			SmartDashboard.putBoolean("Drivetrain Gear", gear);
			SmartDashboard.putBoolean("Gear Door Open/Close", openGearDoor);
			SmartDashboard.putString("Push Gear Door", pushGearDoor ? "retracted" : "extended");
			SmartDashboard.putNumber("Lift Pot", liftPot);
		} catch (Exception e) {
			System.out.println("Error with SmartDashboard");
		}
	}
	
	public void setData(boolean gear, boolean openGearDoor, boolean pushGearDoor, double liftPot) {
		this.gear = gear;
		this.openGearDoor = openGearDoor;
		this.pushGearDoor = pushGearDoor;
		this.liftPot = liftPot;
	}
}
