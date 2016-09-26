package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.MagOff;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BallMagnetUtil extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon magMotor;
	DigitalInput magLimit;
	//to do: decide on what limit switch to use

	public BallMagnetUtil(){
		magMotor = new CANTalon(RobotMap.magMotor);
		magMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		magMotor.enable();
		magLimit = new DigitalInput(RobotMap.magLimit);
	}
	
	public boolean getMagLimit(){
		return magLimit.get();
	}
	
	public void setMotorSpeed(double d){
		magMotor.set(d);
	}
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MagOff());
    }
    
    public void updateStatus(){
    	//SmartDashboard.putNumber("Ball Motor Voltage: ", magMotor.getBusVoltage());
    	SmartDashboard.putBoolean("Ball Captured", magLimit.get());
    }
}

