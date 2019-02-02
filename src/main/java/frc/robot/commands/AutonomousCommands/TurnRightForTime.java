/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//TODO maybe do some math so that this can accept a target turn angle rather than a time in seconds?
public class TurnRightForTime extends Command {

	public double WaitTimeInSec = 0;
	public double StartTime = 0;

double WaitTime;

  public TurnRightForTime(double WaitTime) {
    // Use requires() here to declare subsystem dependencies
	requires(Robot.kDrivetrain);
	this.WaitTimeInSec = this.WaitTime;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

	this.StartTime = Robot.getTime();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
	Robot.kDrivetrain.tankDrive(1, -1, false);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(this.StartTime + this.WaitTimeInSec > Robot.getTime()) {
		return true;
	}
	else {
		return false;
	}
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
