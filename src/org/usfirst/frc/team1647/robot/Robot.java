package org.usfirst.frc.team1647.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

import com.autonomous.Autonomous;
import com.autonomous.Autonomous.Mode;
import com.kauailabs.navx.frc.AHRS;
import com.subsystem.Drive;
import com.subsystem.GearOutput;
import com.subsystem.Lift;
import com.subsystem.Messenger;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import pathfinding.AutoPath;

/**
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends SampleRobot {
	Joystick ps3;
	Joystick stick2;
	Drive drive;
	// GearIntake gearIntake;
	GearOutput gearOutput;
	Lift lift;
	Compressor air;
	Messenger sd;
	AHRS ahrs;
	DigitalInput switch1;
	DigitalInput switch2;
	DigitalInput switch3;
	Autonomous auto;
	ArrayList<Rectangle> rectangles;
	Timer autotime;

	public Robot() {
		ps3 = new Joystick(0);
		stick2 = new Joystick(1);
		drive = new Drive(ps3, stick2);
		// gearIntake = new GearIntake(ps3, stick2);
		gearOutput = new GearOutput(ps3, stick2);
		lift = new Lift(ps3, stick2);
		air = new Compressor();
		sd = new Messenger();
		// ahrs = new AHRS(SerialPort.Port.kMXP);
		ahrs = new AHRS(SPI.Port.kMXP);
		switch1 = new DigitalInput(8);
		switch2 = new DigitalInput(7);
		switch3 = new DigitalInput(9);
		auto = new Autonomous(drive, gearOutput);
		rectangles = new ArrayList<Rectangle>();
		autotime = new Timer();
	}

	@Override
	public void robotInit() {
		air.start();
	}

	@Override
	public void autonomous() {
		// auto.setAutoMode(switch1.get(), switch2.get(), switch3.get());
		// Mode autoMode = auto.getMode();

		int autoMode = -1;
		
		if (!switch1.get() && !switch2.get() && !switch3.get()) {
			//if all the switches are off
			autoMode = 0;
		} else if (switch1.get() && !switch2.get() && !switch3.get()) {
			autoMode = 1;
		} else if (!switch1.get() && switch2.get() && !switch3.get()) {
			autoMode = 2;
		} else if (switch1.get() && switch2.get() && !switch3.get()) {
			autoMode = 3;
		}

		autotime.start();

		// double[] wheelSpeeds = new double[2];
		// double[] startCoords = {0, 0, Math.PI / 2};
		// double[] endCoords = {0, 1, Math.PI / 2};
		// AutoPath pathAuto = new AutoPath(startCoords, endCoords,
		// ahrs.getAngle());
		//
		// int i = 0;
		// boolean successfullyGrabbedRectangles = false;
		
		boolean shotGear = false;
		//boolean hasBackedUp = false;
		while (isAutonomous() && isEnabled()) {
			// auto.drive(autoMode);

			// while(autotime.get() < 3) {
			// drive.drive(-0.9, -0.9);
			// }

			// grab the current switch status

			switch (autoMode) {
			case 0:
				//Mark: Drive forward

				if (autotime.get() < 3) {
					drive.drive(-0.9, -0.9);
				}
				break;
			case 1:
				// Mark: Drive Forward, NO turning, and shoot gear onto peg

				if (autotime.get() < 3) {
					drive.drive(-0.75, -0.75);
				} else if(!shotGear) {
					// shoot the gear onto the peg
					gearOutput.autoOutput();
					shotGear = true;
				}else if(autotime.get() < 3.67) {
					drive.drive(0.50, 0.50);
				}else{
					drive.drive(0,0);
				}

				break;
			case 2:
				//Mark: start LEFT of center, drive forward, turn -60 degrees, drive forward, shoot gear on peg
				if (autotime.get() < 2) {
					drive.drive(-0.75, -0.75);
				} else if (autotime.get() < 2.5) {
					drive.drive(1, 0);
				}else if(autotime.get() < 3.5) { 
					drive.drive(-.75,-.75);
				} else if (!shotGear) {
					gearOutput.autoOutput();
					shotGear = true;
				}else if(autotime.get() < 4.5) {
					drive.drive(0.75, 0.75);
				}else{
					drive.drive(0,0);
				}
				break;
			case 3:
				//Mark: start RIGHT of center, drive forward, turn 60 degrees, drive forward, shoot gear on peg
				if (autotime.get() < 2) {
					drive.drive(-0.75, -0.75);
				} else if (autotime.get() < 2.25) {
					drive.drive(0, 1);
				}else if(autotime.get() < 4) { 
					drive.drive(-.75,-.75);
				} else if (!shotGear) {
					gearOutput.autoOutput();
					shotGear = true;
				}else if(autotime.get() < 4.5) {
					drive.drive(0.75, 0.75);
				}else{
					drive.drive(0,0);
				}
				break;
			default:
				break;
			}

			// if(!successfullyGrabbedRectangles) {
			// Rectangle[] targets = grabRectangles();
			//
			//// System.out.println("-----");
			//// for(Rectangle r : testing) {
			//// System.out.println(r.getArea());
			//// }
			//// System.out.println("-----");
			//
			// if(targets[0].getArea() == 0) {
			// //since grabRectangles returns 1 rectangle with area of 0, so if
			// one of the area's
			// //equals 0, then it was a failed response
			// successfullyGrabbedRectangles = false;
			// }else{
			//
			// successfullyGrabbedRectangles = true;
			//
			// //call auto path to get the wheel speeds from the rectangle
			// pathAuto.makeNewPath(targets[0].getWidth(),
			// targets[1].getWidth(), targets[0].getXPos() - 240,
			// targets[1].getXPos() - 240, ahrs.getAngle());
			// //drive.drive(wheelSpeeds[0], wheelSpeeds[1]);
			//
			// }
			// }else{
			// //drive.drive(0,0);
			//
			// double[] wheelSpeedsFromPath = pathAuto.getWheelSpeeds(.4); //the
			// .4 doesn't mean anything at all; Beitel is just weird :^)
			// drive.drive(wheelSpeedsFromPath[0], wheelSpeedsFromPath[1]);
			// //System.out.println("Left:" + wheelSpeedsFromPath[0]);
			// //System.out.println("Right: " + wheelSpeedsFromPath[1]);
			// }

		}
		
	}

	@Override
	public void operatorControl() {
		autotime.stop();
		while (isOperatorControl() && isEnabled()) {
			// TODO: finalize joystick axes
			drive.drive();
			// gearIntake.intake();
			gearOutput.output();
			lift.lift();
			sd.setData(drive.getSuperShifterState(), gearOutput.getOpenGearDoorState(),
					gearOutput.getPushGearDoorState(), lift.getPot(), lift.getLightSensor(), ahrs.getAngle());
			sd.setData(switch1.get(), switch2.get(), switch3.get());
			sd.putData();
			Timer.delay(0.005);
		}
	}

	@Override
	public void test() {

	}

	public Rectangle[] grabRectangles() {
		// returns the 2 most similar rectangles (similarity is based off of
		// area)

		Rectangle[] failureArray = { new Rectangle(0, 0, 0, 0, 0) };

		try {
			URL url = new URL("http://10.16.47.20/outputRectWidth.txt");
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			if (br.ready()) {
				String[] seperateRectArr = br.readLine().split(Pattern.quote("|"));
				for (String str : seperateRectArr) {
					System.out.println(str);
					String[] rectArgsArr = str.split(Pattern.quote(","));

					int xPos = 0;
					int yPos = 0;
					int width = 0;
					int height = 0;
					int xOffset = 0;

					for (int i = 0; i < rectArgsArr.length; i++) {

						int value = Integer.parseInt(rectArgsArr[i].substring(rectArgsArr[i].indexOf(":") + 1));

						switch (i) {
						case 0:
							xPos = value;
							break;
						case 1:
							yPos = value;
							break;
						case 2:
							width = value;
							break;
						case 3:
							height = value;
							break;
						case 4:
							xOffset = value;
							break;
						}
					}

					System.out.println(xPos + "," + yPos + "," + width + "," + height + "," + xOffset);
					Rectangle r = new Rectangle(xPos, yPos, width, height, xOffset);
					rectangles.add(r);
				}

				if (rectangles.size() >= 2) {
					ArrayList<Double> rectsArea = new ArrayList<Double>();
					for (Rectangle r : rectangles) {
						rectsArea.add(r.getArea());
					}

					// System.out.println("arr = " +
					// Arrays.toString(rectsArea.toArray()));

					Collections.sort(rectsArea);
					double val = 0;
					double val2 = 0;
					double delta = Integer.MAX_VALUE;
					double d = 0;
					for (int i = 0; i < rectsArea.size() - 1; i++) {
						d = rectsArea.get(i + 1) - rectsArea.get(i);
						if (d < delta) {
							delta = d;
							val = rectsArea.get(i);
							val2 = rectsArea.get(i + 1);
						}
					}

					System.out.println("val2 :" + val2);
					System.out.println("val : " + val);

					// val and delta are the areas of the 2 most similar
					// rectangles
					// convert area to width and height
					Rectangle valRect = null;
					Rectangle val2Rect = null;
					for (Rectangle r : rectangles) {
						if (r.width * r.height == val2 && val2Rect == null) {
							val2Rect = r;
						} else if (r.width * r.height == val && valRect == null) {
							valRect = r;
						}
					}

					in.close();
					isr.close();
					br.close();

					Rectangle[] returnArr = { valRect, val2Rect };
					rectangles.clear();
					return returnArr;
				} else {
					return failureArray;
				}
			}

		} catch (Exception e) {
			System.err.println("caught excpection " + e.getMessage());
		}
		return failureArray;
	}
}
