package com.autonomous;

import edu.wpi.first.wpilibj.Timer;

import com.subsystem.*;

public class Autonomous {
	String mode;
	Drive drive;
	GearOutput outPut;
	Timer autoTime;

	public static enum Mode {
		NONE, DRIVEFORWARD, TRACK;
	}

	public Autonomous(Drive drive, GearOutput outPut) {
		this.drive = drive;
		this.outPut = outPut;
	}
	
	public void startAutoTimer() {
		autoTime.start();
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
	
	public Mode getMode() {
		if (mode == Mode.DRIVEFORWARD.name()) {
			return Mode.DRIVEFORWARD;
		} else if (mode == Mode.TRACK.name()) {
			return Mode.TRACK;
		} else {
			return Mode.NONE;
		}
	}

	public void drive(Mode mode) {
		switch (mode) {
		case DRIVEFORWARD:
			drive.drive(0.5, 0.5);
			Timer.delay(5);
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
	
	public void execute(Mode mode, boolean pistonState) {
		
	}
}
