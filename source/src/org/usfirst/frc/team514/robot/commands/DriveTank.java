
package org.usfirst.frc.team514.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team514.robot.Robot;

/**
 *
 */
public class DriveTank extends Command {

    public DriveTank() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveUtil);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//This sample is the tank drive mode command.
    	Robot.driveUtil.driveTank(Robot.oi.getLeftY(), Robot.oi.getRightY());
    	
    	//These lines of code are the arcade drive mode command logic.
    	//Robot.exampleSubsystem.driveArcade(Robot.oi.getRightY(), Robot.oi.getRightX());
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
