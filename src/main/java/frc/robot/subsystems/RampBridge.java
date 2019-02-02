package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

//again, used to be a StoppableSubsystem
public class RampBridge extends Subsystem {
	//TODO make the module number match the PCM ID
	private Solenoid RampBridgePiston = new Solenoid(RobotMap.RAMP_BRIDGE_PISTON);

public RampBridge() {

}

	

	public void DeployRampBridge() {
		RampBridgePiston.set(true);
		// this method extends the ramp bridge piston
	}


	public void RetractRampBridge() {
		RampBridgePiston.set(false);
		// this method retracts the ramp bridge piston
	}


	@Override
	protected void initDefaultCommand() {

		// this is what the ramp will do when nobody pays attention to it. It should
		// probably be closed
	}

}
