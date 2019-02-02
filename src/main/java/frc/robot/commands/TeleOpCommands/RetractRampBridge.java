/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TeleOpCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class RetractRampBridge extends Command {
  public RetractRampBridge() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.kRampBridge);
  }

public boolean isBridgeRetracted = false;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    System.out.println("Robot is retracting the Ramp Bridge");

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.kRampBridge.RetractRampBridge();
    System.out.println("Robot has retracted the ramp bridge");
    this.isBridgeRetracted = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return this.isBridgeRetracted;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("Robot has now finished retracting the ramp bridge");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
