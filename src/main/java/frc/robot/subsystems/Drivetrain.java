package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.CounterBase.EncodingType;
// import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.OI;
import frc.robot.RobotMap;
// import frc.robot.commands.TeleOpCommands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

/**
 * The DriveTrain subsystem controls the robot's chassis and reads in
 * information about it's speed and position.
 */
//TODO Clean all this stuff up once we figure out what is unneccessary
 // this was originally StoppableSubsystem. Don't know what that is but it threw an error
public class Drivetrain extends Subsystem {

	// public static final double ENCODER_PULSES_PER_REVOLUTION = 875;//was 1895 then 3790 with rightEncoder *** 500

	/**
	 * Normal power is multiplied by this value when in enter sniper mode. Greater
	 * than 0 and less than 1.
	 */
	private static final double SNIPER_MODE_MULTIPLIER = 0.5;

	/** In inches **/
	public static final double WHEEL_DIAMETER = 6.0;

	/** PID Controller coefficients. **/
	public static final double Gyro_KP = .01;

	private static final float PITCH_TIPPING_CONSTANT = 15;

	private static final float ROLL_TIPPING_CONSTANT = 15;

	/** Difference between joysticks in tankdrive to just count them as the same **/
	public static final double TANK_DRIVE_STRAIGHT_OFFSET = .05;

	private boolean isSniperMode;

	// right motors (note some are wired backwards, so need different value)
	private WPI_TalonSRX _rFrontMot = new WPI_TalonSRX(RobotMap.RIGHT_FORWARD_MOTOR);
	private WPI_TalonSRX _rMidMot = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_MOTOR);
	private WPI_TalonSRX _rRearMot = new WPI_TalonSRX(RobotMap.RIGHT_AFT_MOTOR);

	// left motors (note some are wired backwards, so need different value)
	private WPI_TalonSRX _lFrontMot = new WPI_TalonSRX(RobotMap.LEFT_FORWARD_MOTOR);
	private WPI_TalonSRX _lMidMot = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_MOTOR);
	private WPI_TalonSRX _lRearMot = new WPI_TalonSRX(RobotMap.LEFT_AFT_MOTOR);

	//TODO figure out if we actually have encoders and gyro, will need to wait for Mechanical people to figure this out
	// private Encoder _rightEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER[0], RobotMap.RIGHT_DRIVE_ENCODER[1], true,
	// 		EncodingType.k4X);
	// private Encoder _leftEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER[0], RobotMap.LEFT_DRIVE_ENCODER[1], false,
	// 		EncodingType.k4X);

	private AHRS navX;

	public Drivetrain() {

		// Configure encoders
		// _rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		// _leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);

		isSniperMode = false;

		// double distancePerPulse; // in feet
		// distancePerPulse = (WHEEL_DIAMETER/* in */ * Math.PI) / (ENCODER_PULSES_PER_REVOLUTION * 12.0/* in/ft */);

		// _rightEncoder.setDistancePerPulse(distancePerPulse);
		// _leftEncoder.setDistancePerPulse(distancePerPulse);

		// configure navX
			navX = new AHRS(RobotMap.NAVX_PORT);
	
	}

	/**
	 * When other commands aren't using the drivetrain, allow tank drive with the
	 * joystick.
	 */
	@Override
	public void initDefaultCommand() {
		// Use either CHEESY_DRIVE or TANK_DRIVE for DriveType
		//TODO
		// setDefaultCommand(new DriveWithJoystick(OI.DRIVE_MODE));
	}

	/**
	 * Limit motor values to the -1.0 to +1.0 range.
	 */
	protected double limit(double value) {
		if (value > 1.0) {
			return 0.999;
		}
		if (value < -1.0) {
			return -0.999;
		}
		return value;
	}

	/**
	 * Returns 0.0 if the given value is within the specified range around zero. The
	 * remaining range between the deadzone and 1.0 is scaled from 0.0 to 1.0.
	 * 
	 * This method is used to make sure we ignore false input from the joysticks
	 * (basically any value that is too small).
	 *
	 * @param value
	 *            value to clip
	 * @param deadband
	 *            range around zero
	 */
	protected double applyDeadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) * (value - deadband);
			} else {
				return -1 * (value + deadband) * (value + deadband);
			}
		} else {
			return 0.0;
		}
	}

	/**
	 * Drive the wheels on one side forward with one stick and the wheels on another
	 * side forward with another stick.
	 * 
	 * @param joy
	 *            Xbox controller to use as the input for tank drive.
	 */
	public void tankDrive(Joystick joy) {
		this.tankDrive(joy.getRawAxis(RobotMap.CONTROLLER_ONE_LEFT_STICK_Y_AXIS), -joy.getRawAxis(RobotMap.CONTROLLER_ONE_RIGHT_STICK_Y_AXIS), true);
	}

	/**
	 * Drive the wheels on one side forward with one stick and the wheels on another
	 * side forward with another stick.
	 * 
	 * @param leftPower
	 *            power towards left motor, between -1 and 1, where 0 is not moving
	 * @param rightPower
	 *            power towards right motor, between -1 and 1, where 0 is not moving
	 * @param applyDeadband
	 *            whether or not tankdrive should ignore smaller inputs, in order to
	 *            account for false input from joysticks. Will also assume two
	 *            inputs that are really close to each other should actually be the
	 *            same input.
	 */
	public void tankDrive(double leftPower, double rightPower, boolean applyDeadband) {
		double lJoyStickVal = -leftPower;
		double rJoyStickVal = rightPower;

		if (applyDeadband) {

			// make "almost" equal values equal
			if (Math.abs(lJoyStickVal - rJoyStickVal) < TANK_DRIVE_STRAIGHT_OFFSET) {
				lJoyStickVal = rJoyStickVal;
			}
			// apply deadband
			lJoyStickVal = applyDeadband(leftPower, OI.JOYSTICK_DEADZONE);
			rJoyStickVal = applyDeadband(rightPower, OI.JOYSTICK_DEADZONE);
		}

		// used for fine movements
		if (isSniperMode) {
			lJoyStickVal *= SNIPER_MODE_MULTIPLIER;
			rJoyStickVal *= SNIPER_MODE_MULTIPLIER;
		}

		// left motors (note some are wired backwards, so need different value)
		_lFrontMot.set(ControlMode.PercentOutput, lJoyStickVal);
		_lMidMot.set(ControlMode.PercentOutput, lJoyStickVal);
		_lRearMot.set(ControlMode.PercentOutput, lJoyStickVal);

		// right motors (note some are wired backwards, so need different value)
		_rFrontMot.set(ControlMode.PercentOutput, rJoyStickVal);
		_rMidMot.set(ControlMode.PercentOutput, rJoyStickVal);
		_rRearMot.set(ControlMode.PercentOutput, rJoyStickVal);

		Timer.delay(0.005); // wait for a motor update time
	}

	/**
	 * Have left joystick verticle axis for power, and right joystick horizontal
	 * axis for turning.
	 * 
	 * @param joy
	 *            xbox controller to use for input
	 */
	public void cheesyDrive(Joystick joy) {
		cheesyDrive(-joy.getRawAxis(RobotMap.CONTROLLER_ONE_RIGHT_STICK_X_AXIS), joy.getRawAxis(RobotMap.CONTROLLER_ONE_LEFT_STICK_Y_AXIS), true);
	}

	/**
	 * Drive with a given forward and turning power.
	 * 
	 * @param power
	 *            forward power to use between 1 and -1
	 * @param turnPower
	 *            rotational power to use between 1 and -1 where 1 is to the right,
	 *            and -1 is to the left
	 * @param applyDeadband
	 *            whether or not tankdrive should ignore smaller inputs, in order to
	 *            account for false input from joysticks
	 */
	public void cheesyDrive(double power, double turnPower, boolean applyDeadband) {
		SmartDashboard.putNumber("joystickcheesypowervalue2", power);
		SmartDashboard.putNumber("joystickcheesyturnpowervalue2", turnPower);

		double tempPower = power;
		double tempTurnPower = turnPower;
		if (applyDeadband) {
			tempPower = applyDeadband(tempPower, OI.JOYSTICK_DEADZONE);
			tempTurnPower = applyDeadband(tempTurnPower, OI.JOYSTICK_DEADZONE);
		}
		// this bit would not necessarily function correctly when turning while moving at high speeds, therefore was 
		// was replaced with basically the same thing that adjusted  Left and Right power instead of base throttle Power
		// if (power > .999)// keep power below 1
		// {
		// 	power = .999;
		// } else if (power < -.999) {// keeps power above -1
		// 	power = -.999;
		// }

		double leftPower = power + turnPower;
		double rightPower = power - turnPower;

		//TODO make sure this doesn't cause problems turning at high speeds
		if (leftPower > .999)// keep power below 1
		{
			leftPower = .999;
		} else if (leftPower < -.999) {// keeps power above -1
			leftPower = -.999;
		}

		if (rightPower > .999)// keep power below 1
		{
			rightPower = .999;
		} else if (rightPower < -.999) {// keeps power above -1
			rightPower = -.999;
		}

		tankDrive(leftPower, rightPower, false);
	// }

	// public int getLeftRotations() {
		// return this._leftEncoder.getRaw();
	// }

	// public int getRightRotations() {
		// return this._rightEncoder.getRaw();
	}

	/**
	 * Used to enable or disable sniper mode. Sniper mode makes the robot more
	 * sensitive to actions from the driver.
	 * 
	 * @param isEnabled
	 *            whether or not sniper mode is enabled
	 */
	public void setSniperMode(boolean isEnabled) {
		this.isSniperMode = isEnabled;
	}

	/**
	 * Stop the drivetrain from moving.
	 */
	//@Override
/** TODO Delete me (probably) public void stop() {
		this.tankDrive(0, 0, false);
}*/

	/**
	 * Resets encoders to start tracking distance driven from a certain point.
	 */
	// public void resetEncoders() {
	// 	//_rightEncoder.reset();
	// 	_leftEncoder.reset();

//		double time = 0;

		// wait for encoders to finish resetting
//		while (Robot.itself.isEnabled() && Math.abs(_rightEncoder.getDistance()) > 0.15
//				/*&& Math.abs(_leftEncoder.getDistance()) > 0.15*/) {
//
//			Timer.delay(0.01);
//
////			SmartDashboard.putNumber("leftEncoder", this.getLeftDistance());
////			SmartDashboard.putNumber("rightEncoder", this.getRightDistance());
////			;
////			SmartDashboard.putNumber("encoder_reset_seconds", time += 0.01);
//		}
	// }

	/**
	 * Gets the average distance driven each encoder has registered in feet since
	 * the last time the encoders were reset.
	 * 
	 * @return average distance driven in feet
	 */
	// public double getDistanceDriven() {
	// 	double dist;

		// dist = (_rightEncoder.getDistance() + _leftEncoder.getDistance()) / 2;
		// dist = _leftEncoder.getDistance();

		// return dist;
	// }

	/**
	 * Distance in feet.
	 * 
	 * @return
	 */
	// public double getLeftDistance() {
		// return _leftEncoder.getDistance();
	// }

	/**
	 * Distance in feet.
	 * 
	 * @return
	 */
	// public double getRightDistance() {
	// 	return _rightEncoder.getDistance();
	// }

	/**
	 * @return The encoder getting the distance and speed of left side of the
	 *         drivetrain.
	 */
	// public Encoder getLeftEncoder() {
	// 	return _leftEncoder;
	// }

	/**
	 * @return The encoder getting the distance and speed of right side of the
	 *         drivetrain.
	 */
	// public Encoder getRightEncoder() {
	// 	return _rightEncoder;

	// }

	/**
	 * @return The current angle of the drivetrain.
	 */
	public double getYaw() {
		return navX.getYaw();
	}

	public boolean isRobotTipping() {
		if (Math.abs(navX.getPitch()) > PITCH_TIPPING_CONSTANT || Math.abs(navX.getRoll()) > ROLL_TIPPING_CONSTANT) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Resets Gyro Yaw to 0.
	 */

	public void resetYaw() {
//		double time = 0;

		navX.zeroYaw();
		
//		int counter = 0;

// 		// wait for yaw to reset fully 
//TODO fix this... or delete it
// 		while (Robot.itself.isEnabled() && Math.abs(this.getYaw()) > 0.8 && (counter < 10)) {
// 			Timer.delay(0.01);
// 			SmartDashboard.putNumber("gyro_yaw", this.getYaw());
// 			SmartDashboard.putNumber("gyro_reset_seconds", time += 0.01);
// 			System.out.println("Yaw is " + this.getYaw());
// //			navX.zeroYaw();
// 			counter++;
// 		}
// 		System.out.println("Resetted Yaw");
 	}
}





//Just to make this 400 lines long... you should probably delete this comment...