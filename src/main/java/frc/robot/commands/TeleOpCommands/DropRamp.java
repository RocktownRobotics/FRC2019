/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TeleOpCommands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class DropRamp extends Command {
  public DropRamp() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.kRamp);
  }

private boolean isDeployFinished = false;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    this.isDeployFinished = false;

    System.out.println("Robot is about to drop the ramp, and would appreciate a brief delay before getting");
    System.out.println("RUN OVER by 125 POUNDS of 'friendly' robot!");

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.kRamp.DeployRampPiston();
    
    System.out.println("Robot is dropping the ramp... just a little longer and it'll be ready to get squished");

     this.isDeployFinished = true;

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      return this.isDeployFinished;
    }
    
  
  

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("Ramp is out... you did make sure our allies abide by the weight limit, right?");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
