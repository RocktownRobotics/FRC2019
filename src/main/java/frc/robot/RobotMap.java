/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static final int NULL_PORT = 0;
// use this as a port number for anything that does not yet have any defined I/O port

  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;


        ////////////////////////////////
        ///////Controller Buttons///////
        ////////////////////////////////
public static int CONTROLLER_ONE_LEFT_STICK_Y_AXIS = 1;
public static int CONTROLLER_ONE_LEFT_STICK_X_AXIS = 0;
public static int CONTROLLER_ONE_RIGHT_STICK_Y_AXIS = 5;
public static int CONTROLLER_ONE_RIGHT_STICK_X_AXIS = 4;
public static int CONTROLLER_ONE_A_BUTTON = 1;
public static int CONTROLLER_ONE_B_BUTTON = 2;
public static int CONTROLLER_ONE_X_BUTTON = 3;
public static int CONTROLLER_ONE_Y_BUTTON = 4;
public static int CONTROLLER_ONE_RIGHT_TRIGGER = 3;
public static int CONTROLLER_ONE_LEFT_TRIGGER= 2;
public static int CONTROLLER_ONE_RIGHT_BUMPER= 6;
public static int CONTROLLER_ONE_LEFT_BUMPER= 5;
public static int CONTROLLER_ONE_RIGHT_STICK_BUTTON= 10;
public static int CONTROLLER_ONE_LEFT_STICK_BUTTON= 9;
public static int CONTROLLER_ONE_DPAD_UP= 13;
public static int CONTROLLER_ONE_DPAD_DOWN= 14;
public static int CONTROLLER_ONE_DPAD_RIGHT= 16;
public static int CONTROLLER_ONE_DPAD_LEFT= 15;
public static int CONTROLLER_ONE_OPTIONS_BUTTON = 7;
public static int CONTROLLER_ONE_START_BUTTON= 8;


        ////////////////////////////////
        //////////Ramp Systems//////////
        ////////////////////////////////
  public static final int RAMP_PISTON = NULL_PORT;
  public static final int RAMP_END_PISTON = NULL_PORT;
  public static final int RAMP_BRIDGE_PISTON = NULL_PORT;

        ////////////////////////////////
        ///////////Drivetrain///////////
        ////////////////////////////////
  public static int LEFT_DRIVE_MOTOR = NULL_PORT;
  public static int LEFT_FORWARD_MOTOR = NULL_PORT;
  public static int LEFT_AFT_MOTOR = NULL_PORT;
  public static int RIGHT_DRIVE_MOTOR = NULL_PORT;
  public static int RIGHT_FORWARD_MOTOR = NULL_PORT;
  public static int RIGHT_AFT_MOTOR = NULL_PORT;
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static int[] LEFT_DRIVE_ENCODER = {NULL_PORT, NULL_PORT};
  public static int[] RIGHT_DRIVE_ENCODER = {NULL_PORT, NULL_PORT};


        ////////////////////////////////
        //////////HatchGrabber//////////
        ////////////////////////////////
  public static int HatchArmPiston = NULL_PORT;
  public static int HatchSpinnyThing = NULL_PORT;
  public static int[] SPINNY_THING_ENCODER = {NULL_PORT, NULL_PORT};

        ////////////////////////////////
        //////////////Gyro//////////////
        ////////////////////////////////

   public static final SPI.Port NAVX_PORT = SPI.Port.kMXP;



  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
