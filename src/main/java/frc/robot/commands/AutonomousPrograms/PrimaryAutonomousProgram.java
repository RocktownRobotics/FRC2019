package frc.robot.commands.AutonomousPrograms;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.AutonomousCommands.DriveBackwardForTime;
import frc.robot.commands.AutonomousCommands.DriveForwardForTime;
import frc.robot.commands.AutonomousCommands.TurnLeftForTime;
import frc.robot.commands.TeleOpCommands.DeployHatchArm;

public class PrimaryAutonomousProgram extends CommandGroup {

	
	public PrimaryAutonomousProgram() {
	//TODO update this once we have DeployHatchArm, plus more info on auto period
		addSequential(new DeployHatchArm());
		addSequential(new DriveForwardForTime(4));
		addSequential(new DriveBackwardForTime(1));
		addSequential(new TurnLeftForTime(2));
		addSequential(new DriveForwardForTime(8));
	}
}
