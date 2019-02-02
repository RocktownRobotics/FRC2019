/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TeleOpCommands;

// TODO make sure that the mech team is actually using two pistons, and adjust this as needed if not...

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class RetractRamp extends Command {
  public RetractRamp() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.kRampEnd);
  }

private boolean isRetractFinished = false;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    System.out.println("Robot is now attempting to retract the end of the buddy ramp. Robot would like to not be squashed");

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.kRampEnd.RetractRampEnd();
    
    System.out.println("Robot is retracting the ramp tip. Please refrain from overrunning Robot right now....");

     this.isRetractFinished = true;

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (this.isRetractFinished == false) {
      return false;
    }
    else{
      return true;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("Robot has now raised the end of the ramp, and no longer can be run over. Robot is happy.");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
