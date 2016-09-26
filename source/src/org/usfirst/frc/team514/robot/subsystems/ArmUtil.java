package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.OperateArm;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmUtil extends Subsystem {
	
	CANTalon armMotor;
	Encoder armEncoder;
	DigitalInput outerLimit, returnLimit;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ArmUtil(){
		armMotor = new CANTalon(RobotMap.armMotor);
		armEncoder = new Encoder(RobotMap.armEncoder1, RobotMap.armEncoder2, false, CounterBase.EncodingType.k4X);
		outerLimit = new DigitalInput(RobotMap.outerLimit);
		returnLimit = new DigitalInput(RobotMap.returnLimit);
		
	}

	public boolean getOuterLimit(){
		return outerLimit.get();
	}
	
	public boolean getReturnLimit(){
		return returnLimit.get();
	}
	
	public void resetEncoder(){
		armEncoder.reset();
	}
	
	public double getEncoderValue(){
		return armEncoder.getRaw();
	}
	
	public void operateArm(double d){
		armMotor.set(checkLimits(d));
		
	}
	
	public void pressArm(){
		double d = 0.0;
		if(getEncoderValue() >= RobotMap.armPresspoint){
			if(getOuterLimit()){
				d=0.0;
			}else{
				d=0.20;
			}
		}
		armMotor.set(d);
	}
	
	private double checkLimits(double d){
		if(!Robot.oi.getArmOverideButton()){
			d=d*RobotMap.armSpeedAdjust;
			if(d<=0.0){
				if(getOuterLimit()){
					d=0.0;
				}
			}else{
				if(getReturnLimit()){
					d=0.0;
				}
			}
			if(d!=0.0){
				if(getEncoderValue()>=RobotMap.armForwardMidpoint && d<0.0){
					d=0.0;
				}
				if(getEncoderValue()<=RobotMap.armBackwardMidpoint && d>0.0){
					d=0.0;
				}
			}
		}
		
		return -d;
	}

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new OperateArm());
    }
    
    public void updateStatus(){
    	SmartDashboard.putBoolean("Arms Out: ", getOuterLimit());
    	SmartDashboard.putBoolean("Arms In: ", getReturnLimit());
    	SmartDashboard.putNumber("Arm Encoder: ", getEncoderValue());
    }
}

