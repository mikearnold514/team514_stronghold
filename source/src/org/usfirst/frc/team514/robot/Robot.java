package org.usfirst.frc.team514.robot;

import org.usfirst.frc.team514.robot.commands.AutoDefault;
import org.usfirst.frc.team514.robot.commands.AutoLeft;
import org.usfirst.frc.team514.robot.commands.AutoRight;
import org.usfirst.frc.team514.robot.subsystems.ArmUtil;
import org.usfirst.frc.team514.robot.subsystems.BallMagnetUtil;
import org.usfirst.frc.team514.robot.subsystems.DriveUtil;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveUtil driveUtil = new DriveUtil();
	public static final BallMagnetUtil ballMagnetUtil = new BallMagnetUtil();
	public static final ArmUtil armUtil = new ArmUtil();
	public static OI oi;
	public static CameraServer server;
	
    Command autonomousCommand;
    SendableChooser chooser;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	server = CameraServer.getInstance();
		oi = new OI();
		
		SmartDashboard.putData(driveUtil);
		SmartDashboard.putData(ballMagnetUtil);
		SmartDashboard.putData(armUtil);

		//server.startAutomaticCapture("cam2");
		server.startAutomaticCapture("cam0");
		
        chooser = new SendableChooser();
        chooser.addDefault("Default Courtyard", new AutoDefault());
        //chooser.addObject("Low Bar: ", new AutoLowBar());
        chooser.addObject("Auto Left: ", new AutoLeft());
        chooser.addObject("Auto Right: ", new AutoRight());
        
        SmartDashboard.putData("Auto mode", chooser);
        
    }
    
    public void updateStatus(){
    	Robot.driveUtil.updateStatus();
    	Robot.ballMagnetUtil.updateStatus();
    	Robot.armUtil.updateStatus();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        updateStatus();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        updateStatus();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}