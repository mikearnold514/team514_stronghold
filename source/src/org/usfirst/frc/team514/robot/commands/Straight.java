package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Straight extends Command {
	double leftValue = 0.0, rightValue = 0.0, distance = 0.0;
	boolean done, direction;
	
    public Straight(boolean direction, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.direction = direction;
    	this.distance = distance;
    	requires(Robot.driveUtil);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		getMotorSpeed();
    	Robot.driveUtil.driveTank(leftValue, rightValue);
    	//setOvershot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.driveUtil.leftIsDone(this.distance, true) ||
           Robot.driveUtil.rightIsDone(this.distance, true)){
    		done = true;
    		Robot.driveUtil.driveTank(0.0, 0.0);
    	}else{
    		done = false;
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
    
    public void getMotorSpeed(){
        double adjust;

        leftValue = RobotMap.auto_LeftMotor;
        rightValue = RobotMap.auto_RightMotor;

        /*
        if(this.direction){
            adjust = Robot.driveUtil.coerce2Range(Robot.driveUtil.getEncoderDelta());        	
        }else{
        	adjust = 0.0;
        }
        */
        
        adjust = Robot.driveUtil.coerce2Range(Robot.driveUtil.getEncoderDelta());
        
        if(!this.direction){
    		rightValue = rightValue + -adjust;
    		leftValue = leftValue + adjust;        	        	        	
        }else{
    		rightValue = rightValue + adjust;
    		leftValue = leftValue + -adjust;        	        	        	
        }
        
        //Decrease Motor Speed if within Window 2 by 2x
        	if((Robot.driveUtil.leftInWindow2(0.0, false)) ||
        	  (Robot.driveUtil.rightInWindow2(0.0, false))){
            	rightValue = (rightValue - (rightValue * (RobotMap.Window_S_Speed_Pct)*1.5));
            	leftValue = (leftValue - (leftValue * (RobotMap.Window_S_Speed_Pct)*1.5));
        	}
        //Decrease Motor Speed if within Window 1 by 1x
        	if((Robot.driveUtil.leftInWindow1(0.0, false)) ||
               (Robot.driveUtil.rightInWindow1(0.0, false))){
            	rightValue = (rightValue - (rightValue * RobotMap.Window_S_Speed_Pct));
            	leftValue = (leftValue - (leftValue * RobotMap.Window_S_Speed_Pct));             		
            }

        //Reduce Motor Speed if within Window 1
            if(Robot.driveUtil.leftInWindow1(this.distance, true) ||
               Robot.driveUtil.rightInWindow1(this.distance, true)){
            	rightValue = (rightValue - (rightValue * RobotMap.Window_S_Speed_Pct));
            	leftValue = (leftValue - (leftValue * RobotMap.Window_S_Speed_Pct));
        //Reduce Motor Speed MORE if within Window 2
            	if(Robot.driveUtil.leftInWindow2(this.distance, true) ||
            	   Robot.driveUtil.rightInWindow2(this.distance, true)){
                	rightValue = (rightValue - (rightValue * RobotMap.Window_S_Speed_Pct));
                	leftValue = (leftValue - (leftValue * RobotMap.Window_S_Speed_Pct));        		
            	}
            }
    		
    	if(!this.direction){
        	//reverse
            //rightValue = -(rightValue + (rightValue * RobotMap.Speed_Adjust));
            //leftValue = -(leftValue - (leftValue * RobotMap.Speed_Adjust));
    		rightValue = -rightValue;
    		leftValue = -(leftValue - (leftValue * RobotMap.Speed_Adjust));
        }else{
        	 rightValue = (rightValue - (rightValue * RobotMap.Speed_Adjust));
             leftValue = (leftValue + (leftValue * RobotMap.Speed_Adjust));
        }
        
    }
    
}
