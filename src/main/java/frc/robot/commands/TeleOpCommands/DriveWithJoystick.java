package frc.robot.commands.TeleOpCommands;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command allows xbox joystick to drive the robot. It is always running
 * except when interrupted by another command.
 */
public class DriveWithJoystick extends CommandGroup {

	public static enum DriveType {
		CHEESY_DRIVE, TANK_DRIVE
	};

	/**
	 * 
	 * @param blah
	 *            blah is the poorly named variable referring to the method of drive
	 *            control, either tank, or cheesy...
	 */

	public DriveWithJoystick(DriveType blah) {
		
		if (blah == DriveType.TANK_DRIVE) {

			addSequential(new TankDrive());

		} else if (blah == DriveType.CHEESY_DRIVE) {

			addSequential(new CheesyDrive());
			
		} else {
		}
	}
}