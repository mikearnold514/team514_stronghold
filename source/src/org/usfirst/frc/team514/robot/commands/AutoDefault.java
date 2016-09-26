package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDefault extends CommandGroup {
    
    public  AutoDefault() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	/*
    	 * This command group must do the following:
    	 * 1.  Reset Encoders
    	 * 2.  Drive Forward into Courtyard
    	 * 3.  Stop.
    	 * Note:  All commands must have an isFinished() that returns true or the 
    	 *        Command Group will not work!
    	 */
    	addSequential(new ResetEncoders());
    	addSequential(new ResetGyro());
    	addSequential(new DriveEncoder(RobotMap.cDistance));
    }
    
    
}
