/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TeleOpCommands.DeployHatchArm;

public class HatchArm extends Subsystem {

  public Solenoid HatchPiston = new Solenoid(RobotMap.HatchArmPiston);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

public void DeployHatchArm() {
  this.HatchPiston.set(true);
}

public void RetractHatchArm() {
  this.HatchPiston.set(false);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DeployHatchArm());
  }
}
