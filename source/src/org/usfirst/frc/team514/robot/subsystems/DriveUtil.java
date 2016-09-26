
package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.DriveTank;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveUtil extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon lDriveMaster, lDriveSlave, rDriveMaster, rDriveSlave;
	Encoder leftDriveEncoder, rightDriveEncoder;
	Gyro gyro;
	
	double input, delta, speed;
	
	public DriveUtil(){
		gyro = new AnalogGyro(1);
		lDriveMaster = new CANTalon(RobotMap.leftMotorMaster);
		lDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
		lDriveMaster.enable();
		
		rDriveMaster = new CANTalon(RobotMap.rightMotorMaster);
		rDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
		rDriveMaster.enable();
		
		lDriveSlave = new CANTalon(RobotMap.leftMotorSlave);
		lDriveSlave.changeControlMode(TalonControlMode.Follower);
		lDriveSlave.set(lDriveMaster.getDeviceID());
		lDriveSlave.enable();
		
		rDriveSlave = new CANTalon(RobotMap.rightMotorSlave);
		rDriveSlave.changeControlMode(TalonControlMode.Follower);
		rDriveSlave.set(rDriveMaster.getDeviceID());
		rDriveSlave.enable();
		
		leftDriveEncoder = new Encoder(RobotMap.leftEncoder1, RobotMap.leftEncoder2, true, CounterBase.EncodingType.k4X);
    	rightDriveEncoder = new Encoder(RobotMap.rightEncoder1, RobotMap.rightEncoder2, true, CounterBase.EncodingType.k4X);
    	
    	input = delta = speed = 0.0;
    	resetGyro();
        calibrateGyro();

	}
	
	public void driveTank(double leftY, double rightY){
		lDriveMaster.set(-leftY);
		rDriveMaster.set(rightY);
	}
	
	public void driveArcade(double throttleY, double turnX){
		double left;
        double right;
        left = throttleY - turnX;
        right = throttleY + turnX;
        driveTank(left, right);
	}
	
	public void driveAuto(double left, double right){
		double adj = coerce2Range(getGyroAngle());
		left = left + adj;
		right = right + -adj;
		
		driveTank(left, right);
	}
	
	public double getLeftEncoder(){
    	return Math.abs(leftDriveEncoder.getRaw());
    }
    
    public double getRightEncoder(){
    	return Math.abs(rightDriveEncoder.getRaw());
    }
    
    public void resetEncoders(){
    	leftDriveEncoder.reset();
    	rightDriveEncoder.reset();
    }
    
    public void resetGyro(){
    	gyro.reset();
    }
    
    public void calibrateGyro(){
    	gyro.calibrate();
    }
    
    public double getGyroAngle(){
    	return gyro.getAngle();
    }
    
    public double coerce2Range(double input){
        // TODO code application logic here
        double inputMin, inputMax, inputCenter;
        double outputMin, outputMax, outputCenter;
        double scale, result;
        //double output;
        
        inputMin = RobotMap.C2R_inputMin; 
        inputMax = RobotMap.C2R_inputMax;     
        
        outputMin = RobotMap.C2R_outputMin;
        outputMax = RobotMap.C2R_outputMax;
        
        //14 Encode ticks per inch...
        this.input = input;
                
            /* Determine the center of the input range and output range */
            inputCenter = Math.abs(inputMax - inputMin) / 2 + inputMin;
            outputCenter = Math.abs(outputMax - outputMin) / 2 + outputMin;

            /* Scale the input range to the output range */
            scale = (outputMax - outputMin) / (inputMax - inputMin);

            /* Apply the transformation */
            result = (input + -inputCenter) * scale + outputCenter;

            /* Constrain to the output range */
            speed = Math.max(Math.min(result, outputMax), outputMin);

       return speed;
       
    }
    
    public double getEncoderDelta(){
    	delta = (Robot.driveUtil.getRightEncoder() - Robot.driveUtil.getLeftEncoder());
    	return delta;
    }
    
    public boolean leftInWindow1(double distance, boolean action){
    	boolean done;
    	if(action){
    		//Straight
        	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_S_Window1)) &&
        	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_S_Window1))){
        	    	  done = true;
        	 }else{
        	    	  done = false;
        	 }    	
    	}else{
    		//Pivot
        	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_P_Window1)) &&
        	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_P_Window1))){
        	    	  done = true;
        	}else{
        	    	  done = false;
        	}    	
    	}
    	return done;
    }
    
    public boolean rightInWindow1(double distance, boolean action){
    	boolean done;
    	if(action){
    		//Straight
        	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_S_Window1)) &&
            	(Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_S_Window1))){
      	    	   		done = true;
       	    }else{
      	    	   		done = false;
       	    }    	    		
    	}else{
    		//Pivot
        	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_P_Window1)) &&
        	    (Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_P_Window1))){
  	    	    		done = true;
   	    	}else{
  	    	    		done = false;
   	    	}    	
    		
    	}
    	return done;
    }
    
    public boolean leftInWindow2(double distance, boolean action){
    	boolean done;
    	if(action){
    		//Straight
        	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_S_Window2)) &&
        	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_S_Window2))){
        	   		done = true;
        	}else{
        	   		done = false;
        	}
    	}else{
    		//Pivot
        	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_P_Window2)) &&
        	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_P_Window2))){
        	   		done = true;
        	}else{
        	   		done = false;
        	}
    		
    	}
    	return done;
    }
    
    public boolean rightInWindow2(double distance, boolean action){
    	boolean done;
    	if(action){
    		//Straight
        	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_S_Window2)) &&
        	   (Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_S_Window2))){
        	   		done = true;
        	}else{
        	    	done = false;
        	}    		
    	}else{
    		//Pivot
        	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_P_Window2)) &&
        	   (Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_P_Window2))){
        	   		done = true;
        	}else{
        	   		done = false;
        	}
    	}
    	return done;
    }

    public boolean leftIsDone(double distance, boolean action){
    	boolean done;
    	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_CONSTANT)) &&
    	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_CONSTANT))){
    		done = true;
    	}else if(action){ 
    		//Straight
    		if(Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance + RobotMap.ENCODER_S_Window2)){
    			done = true;
    		}else{
    			done = false;
    		}
    	}else{
    		//Pivot
    		if(Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance + RobotMap.ENCODER_P_Window2)){
    			done = true;
    		}else{
    			done = false;
    		}
    	}
    	return done;
    }
    
    public boolean rightIsDone(double distance, boolean action){
    	boolean done;
    	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_CONSTANT)) &&
    	   (Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_CONSTANT))){
   	    		done = true;
    	}else if(action){ 
    		//Straight
    		if(Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance + RobotMap.ENCODER_S_Window2)){
   	    			done = true;
    		}else{
   	    			done = false;
    		}
    	}else{
    		//Pivot
    		if(Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance + RobotMap.ENCODER_P_Window2)){
   	    			done = true;
    		}else{
   	    			done = false;
    		}
    	}
    	return done;
    }


	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveTank());
    }
    
    public void updateStatus(){
    	//SmartDashboard.putNumber("Left Motor Voltage: ", lDriveMaster.getBusVoltage());
    	//SmartDashboard.putNumber("Right Number Voltage: ", rDriveMaster.getBusVoltage());
    	SmartDashboard.putNumber("Left Motor Encoder: ", getLeftEncoder());
    	SmartDashboard.putNumber("Right Motor Encoder: ", getRightEncoder());
    	SmartDashboard.putNumber("Gyro Angle: ", gyro.getAngle());
    	SmartDashboard.putNumber("Speed Adjustment: ", this.speed);
    }
}

