package org.usfirst.frc.team514.robot;

import org.usfirst.frc.team514.robot.commands.CalibrateGyro;
import org.usfirst.frc.team514.robot.commands.DriveArcade;
import org.usfirst.frc.team514.robot.commands.DriveTank;
import org.usfirst.frc.team514.robot.commands.MagIn;
import org.usfirst.frc.team514.robot.commands.MagOut;
import org.usfirst.frc.team514.robot.commands.PressArm;
import org.usfirst.frc.team514.robot.commands.ResetEncoders;
import org.usfirst.frc.team514.robot.commands.ResetGyro;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	Joystick leftStick, rightStick, controller;
	
	JoystickButton tank, arcade, magforward, magreverse, resetencoders, autocmd1, calibrategyro, resetgyro, press;
	
	public OI(){
	//Because Joysticks can be remapped in the Driver Station,
	//RobotMap Interface definitions are not needed.
		leftStick = new Joystick(1);
		rightStick = new Joystick(2);
		controller = new Joystick(3);
		
		tank = new JoystickButton(rightStick, RobotMap.tankMode);
		arcade = new JoystickButton(rightStick, RobotMap.arcadeMode);
		magforward = new JoystickButton(controller, RobotMap.magIn);
		magreverse = new JoystickButton(controller, RobotMap.magOut);
		resetencoders = new JoystickButton(controller, RobotMap.resetEncoders);
		autocmd1 = new JoystickButton(controller, RobotMap.autoCMD1);
		calibrategyro = new JoystickButton(leftStick, RobotMap.calibrateGyro);
		resetgyro = new JoystickButton(leftStick, RobotMap.resetGyro);
		press = new JoystickButton(controller, RobotMap.kLeftBumperNum);
		
		tank.whenPressed(new DriveTank());
		arcade.whenPressed(new DriveArcade());
		magforward.whileHeld(new MagIn());
		magreverse.whileHeld(new MagOut());
		resetencoders.whenPressed(new ResetEncoders());
		//autocmd1.whenPressed(new DriveEncoder());
		calibrategyro.whenPressed(new CalibrateGyro());
		resetgyro.whenPressed(new ResetGyro());
		press.whileHeld(new PressArm());
		
		
	}
	
	public double getLeftY(){
		return leftStick.getY();
	}
	
	public double getRightY(){
		return rightStick.getY();
	}
	
	public double getRightX(){
		return rightStick.getX();
	}
	
	public double getControllerRightY(){
		return controller.getRawAxis(RobotMap.kRightYAxisNum);
	}
	
	public boolean getArmOverideButton(){
		return controller.getRawButton(RobotMap.kLeftTriggerNum);
	}
}

