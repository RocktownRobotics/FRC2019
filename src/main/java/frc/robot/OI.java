/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.TeleOpCommands.DeployRampBridge;
import frc.robot.commands.TeleOpCommands.DriveWithJoystick.DriveType;
import frc.robot.commands.TeleOpCommands.DropRamp;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);


  public static final double JOYSTICK_DEADZONE = 0.1;

  //TODO Make sure that we actually want Tank Drive here and not Cheesy Drive
	public static final DriveType DRIVE_MODE = DriveType.TANK_DRIVE;




	public Joystick xbox1 = new Joystick(0); // set to ID 0


  //Allocating controls for driving around is done in Drivetrain subsystem, not here.
  // private double LeftStickY = xbox1.getRawAxis(RobotMap.CONTROLLER_ONE_LEFT_STICK_Y_AXIS);
  // private double LeftStickX = xbox1.getRawAxis(RobotMap.CONTROLLER_ONE_LEFT_STICK_X_AXIS);
  // private double RightStickY = xbox1.getRawAxis(RobotMap.CONTROLLER_ONE_RIGHT_STICK_Y_AXIS);
  // private double RightStickX = xbox1.getRawAxis(RobotMap.CONTROLLER_ONE_RIGHT_STICK_X_AXIS);

  private double LeftTrigger = xbox1.getRawAxis(RobotMap.CONTROLLER_ONE_LEFT_TRIGGER);
  private double RightTrigger = xbox1.getRawAxis(RobotMap.CONTROLLER_ONE_RIGHT_TRIGGER);


  Button A = new JoystickButton(xbox1, RobotMap.CONTROLLER_ONE_A_BUTTON);
  Button B = new JoystickButton(xbox1, RobotMap.CONTROLLER_ONE_B_BUTTON);
  Button X = new JoystickButton(xbox1, RobotMap.CONTROLLER_ONE_X_BUTTON);
  Button Y = new JoystickButton(xbox1, RobotMap.CONTROLLER_ONE_Y_BUTTON);
  Button Start1 = new JoystickButton(xbox1, RobotMap.CONTROLLER_ONE_START_BUTTON);
  Button Option1 = new JoystickButton(xbox1, RobotMap.CONTROLLER_ONE_OPTIONS_BUTTON);
  Button RightBumper = new JoystickButton(xbox1, RobotMap.CONTROLLER_ONE_RIGHT_BUMPER);
  Button LeftBumper = new JoystickButton(xbox1, RobotMap.CONTROLLER_ONE_LEFT_BUMPER);

 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Key Bindings//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  // button.whenPressed(new ExampleCommand());

public OI() {
  initSingleControllerSetup();
}

//TODO assign keybindings once commands are built

  // button.whileHeld(new ExampleCommand());
public void initSingleControllerSetup() {
  //put all the buttons here


    LeftBumper.whenPressed(new DropRamp());
    LeftBumper.whenPressed(new DeployRampBridge());





    
}
  // button.whenReleased(new ExampleCommand());
}
