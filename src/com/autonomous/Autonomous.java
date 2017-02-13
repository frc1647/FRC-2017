package com.autonomous;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

import com.subsystem.*;

public class Autonomous {
	String mode;
	Drive drive;
	GearOutput outPut;
	boolean value1;
	boolean value2;
	boolean value3;

	public static enum Mode {
		NONE, DRIVEFORWARD, TRACK;
	}

	public Autonomous(DigitalInput switch1, DigitalInput switch2, DigitalInput switch3, Drive drive,
			GearOutput outPut) {
		value1 = switch1.get();
		value2 = switch2.get();
		value3 = switch3.get();
		//setAutoMode(value1, value2, value3);
		this.drive = drive;
		this.outPut = outPut;
	}

	public void setAutoMode(boolean value1, boolean value2, boolean value3) {
		if (!value1 && !value2 && value3) {
			mode = Mode.TRACK.name();
		} else if (!value1 && value2 && !value3) {
			mode = Mode.DRIVEFORWARD.name();
		} else {
			mode = Mode.NONE.name();
		}
	}

	public void drive(Mode mode) {
		switch (mode) {
		case DRIVEFORWARD:
			drive.drive(1, 1);
			break;
		case TRACK:
			drive.drive(1, 0);
			break;
		default:
			drive.drive(0, 0);
		}
		System.out.println(mode);
	}
	
	

	public void outPut(Mode mode, boolean pistonState) {
		switch (mode) {
		case TRACK:
			outPut.autoOpenGearDoor(pistonState);
			Timer.delay(0.5);
			outPut.autoPushGearDoor(pistonState);
			break;
		default:
		}
	}

	public Mode getMode() {
		if (mode == Mode.DRIVEFORWARD.name()) {
			return Mode.DRIVEFORWARD;
		} else if (mode == Mode.TRACK.name()) {
			return Mode.TRACK;
		} else {
			return Mode.NONE;
		}
	}
	
	public boolean getSwitch1(){
		return value1;
	}
	
	public boolean getSwitch2(){
		return value2;
	}
	
	public boolean getSwitch3(){
		return value3;
	}
	
	public void setSwitch1(boolean val){
		value1 = val;
	}
	
	public void setSwitch2(boolean val){
		value2 = val;
	}
	
	public void setSwitch3(boolean val){
		value3 = val;
	}
}
