package com.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.networktables.*;

public class Messenger {
	boolean gear;
	boolean openGearDoor;
	boolean pushGearDoor;
	double liftPot;
	NetworkTable vision;
	boolean switch1;
	boolean switch2;
	boolean switch3;
	double colorSensor;
	double ahrsAngle;
	
	public Messenger() {
		DriverStation.getInstance();
	}
	
	public void putData() {
		try {
			SmartDashboard.putBoolean("Drivetrain Gear", gear);
			SmartDashboard.putBoolean("Gear Door Open/Close", openGearDoor);
			SmartDashboard.putString("Push Gear Door", pushGearDoor ? "retracted" : "extended");
			SmartDashboard.putNumber("Lift Pot", liftPot);
			SmartDashboard.putNumber("Color Sensor", colorSensor);
			SmartDashboard.putNumber("AHRS Angle", ahrsAngle);
		} catch (Exception e) {
			System.out.println("Error with SmartDashboard");
		}
	}
	
	public void setData(boolean gear, boolean openGearDoor, boolean pushGearDoor, double liftPot, double colorSensor, double ahrsAngle) {
		this.gear = gear;
		this.openGearDoor = openGearDoor;
		this.pushGearDoor = pushGearDoor;
		this.liftPot = liftPot;
		this.colorSensor = colorSensor;
		this.ahrsAngle = ahrsAngle;
	}
	
	public void setData(boolean switch1, boolean switch2, boolean switch3){
		this.switch1 = switch1;
		this.switch2 = switch2;
		this.switch3 = switch3;
	}
	
	public void putAutoData(){
		try {
			SmartDashboard.putBoolean("switch 1", switch1);
			SmartDashboard.putBoolean("switch 2", switch2);
			SmartDashboard.putBoolean("switch 3", switch3);
		} catch (Exception e) {
			System.out.println("Error with SmartDashboard");
		}
	}
	
	public NetworkTable getVisionData() {
		// TODO: format data in a more useable way
		
		return vision;
	}
}
