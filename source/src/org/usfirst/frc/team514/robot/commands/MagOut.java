package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MagOut extends Command {
	boolean autonomous;
	
    public MagOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballMagnetUtil);
    	autonomous = false;
    }
    
    public MagOut(boolean autonomous){
    	requires(Robot.ballMagnetUtil);
    	this.autonomous=autonomous;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ballMagnetUtil.setMotorSpeed(-RobotMap.magSpeedOut);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(autonomous){
    		return true;
    	}
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
