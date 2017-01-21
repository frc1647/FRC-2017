package com.subsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

public abstract class Subsystem {
	public Joystick ps3;
	public Joystick stick2;
	
	
	public Subsystem (Joystick ps3, Joystick stick2) {
		this.ps3 = ps3;
		this.stick2 = stick2;
	}
	
    public static boolean toggleSolenoid(Solenoid piston, boolean buttonPressed, boolean buttonHeld) {
        if (buttonPressed && !buttonHeld) {
            buttonHeld = true;
            piston.set(!piston.get());
        } else if (!buttonPressed) {
            buttonHeld = false;
        }
        return buttonHeld;
    }
}
