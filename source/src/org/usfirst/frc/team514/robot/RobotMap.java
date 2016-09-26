package org.usfirst.frc.team514.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//AUTONYMOUS VARIABLES GO HERE
		public static final double leftSpeed = 0.45;
		public static final double rightSpeed = 0.45;
		public static final double cDistance = 9245.0;
		public static final double oDistance = 2712.0;
		public static final double armOut = 0.5;
		public static final double armPresspoint = 13000.0;
		public static final double armForwardMidpoint = 8000.0;
		public static final double armBackwardMidpoint = 7030.0;
		public static final double lowBarDistance = 12205.0;
		public static final double leftPositionDistance = 12205.0;
		public static final double rightPositionDistance = 12205.0;
		public static final double leftRotate = 135;
		public static final double autoWait = 1.0;
		public static final double autoRotate = 32;//147.0;
        //Coerce to Range control variables
        public static final double C2R_inputMin = -10.0;
        public static final double C2R_inputMax = 10.0;
        public static final double C2R_outputMin = -0.25;
        public static final double C2R_outputMax = 0.25;
        
    	//Encoder Distance Parameters
        public static final double ENCODER_DISTANCE = 1592.0;  //Ten Feet
        public static final double ENCODER_90 = 242.0;         //Right Encoder 90 Degree Turn
        public static final double ENCODER_180 = 484.0;	   //Right Encoder 180 Degree Turn
        
        //Encoder Window Ranges to step down robot speed to improve accuracy in Autonomous
             //Pivot Action, Dead Zone windowing constant
        public static final double ENCODER_P_Window1 = 150.0;   
        public static final double ENCODER_P_Window2 = 75.0;
        
            //Straight Action, Dead Zone windowing constant
        public static final double ENCODER_S_Window1 = 500.00;  
        public static final double ENCODER_S_Window2 = 200.00;

            //Speed Rate of Reduction when in the Window
        public static final double Window_S_Speed_Pct = 0.2;
        public static final double Window_P_Speed_Pct = 0.1;
        
        //Encoder window - 5 Ticks (2.5 +/-) where we consider the robot on target
        public static final double ENCODER_CONSTANT = 2.5;
        
        //Autonomous Motor Starting Values
        	//Straight (Reverse) and Pivot (Clockwise) Speed Adjustment Factor
        public static final double Speed_Adjust = 0.05;
        //public static final double Speed_Adjust_f = 0.1;
        	
        	//Straight
        public static final double auto_LeftMotor = -0.55;
        public static final double auto_RightMotor = -0.55;
        
        	//Pivot
        public static final double auto_LeftPivot = -0.30;
        public static final double auto_RightPivot = -0.30;
        

    //ALL CAN MOTOR DEFINITIONS GO HERE
	// DriveUtil Variables.
    	public static final int leftMotorMaster = 1;
    	public static final int leftMotorSlave = 2;
    	public static final int rightMotorMaster = 3;
    	public static final int rightMotorSlave = 4;
    	
    	//BallMagnetUtil Variables
    	public static final int magMotor = 5;
    	
    	//ArmUtil Variables
    	public static final int armMotor = 6;
    //END CAN MOTOR DEFINITIONS
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    
    	//BallMagnetUtil Variables
    	public static final double magSpeedOut = 1.0;
    	public static final double magSpeedIn = 1.0;
    	
    	//ArmUtil Variables
    	public static final double armSpeedAdjust = 0.25;
    	
    //Joystick Variables
    	//Right Joystick
    	public static final int tankMode = 3;
    	public static final int arcadeMode = 2;
    	
    	//Left Joystick
    	public static final int calibrateGyro = 2;
    	public static final int resetGyro = 3;

        //LogiTech F310 Button Mapping X/D Switch = D, DirectInput
        //Axis
        public static final int kLeftXAxisNum = 0;
        public static final int kLeftYAxisNum = 1;
        public static final int kRightXAxisNum = 2;
        public static final int kRightYAxisNum = 3;
        //For DPad, use controller.getPOV();
        //public static final int kDPadXAxisNum = 5;
        //public static final int kDPadYAxisNum = 6;
        //Buttons
        public static final int kXButtonNum = 1;
        public static final int kAButtonNum = 2;
        public static final int kBButtonNum = 3;
        public static final int kYButtonNum = 4;
        public static final int kLeftBumperNum = 5;
        public static final int kRightBumperNum = 6;
        public static final int kLeftTriggerNum = 7;
        public static final int kRightTriggerNum = 8;
        public static final int kBackButtonNum = 9;
        public static final int kStartButtonNum = 10;
        public static final int kLeftStickButtonNum = 11;
        public static final int kRightStickButtonNum = 12;
    	
    	
    	//Controller
    	public static final int magIn = 2;  //Could use the F310 Mapping - kAButtonNum
    	public static final int magOut = 4; //Could use the F310 Mapping - kYButtonNum
    	public static final int resetEncoders = 9;  //Could use the F310 Mapping - kBackButtonNum
    	public static final int autoCMD1 = 10;  //Could use the F310 Mapping - kStartButtonNum
    //End Joystick Variables
    	
    //RIO Variables
    	//Digital Inputs
    	public static final int leftEncoder1 = 2;
        public static final int leftEncoder2 = 3;
        public static final int rightEncoder1 = 0;
        public static final int rightEncoder2 = 1;
        
        public static final int armEncoder1 = 5;
        public static final int armEncoder2 = 6;
        
        public static final int magLimit = 4;
        public static final int outerLimit = 7;
        public static final int returnLimit = 8;
	
}
