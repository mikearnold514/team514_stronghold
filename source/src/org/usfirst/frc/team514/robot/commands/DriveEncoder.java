package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveEncoder extends Command {
	double distance;

    public DriveEncoder(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveUtil);
    	distance = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveUtil.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveUtil.driveAuto(RobotMap.leftSpeed, RobotMap.rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean done = false;
    	if(Robot.driveUtil.getLeftEncoder()>=distance){
    		done = true;
    		Robot.driveUtil.driveTank(0.0, 0.0);
    	}	
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
