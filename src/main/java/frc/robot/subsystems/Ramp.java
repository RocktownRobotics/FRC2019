package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Ramp extends Subsystem {
	//TODO make the module number match the PCM ID
	private Solenoid RampPiston = new Solenoid(RobotMap.RAMP_PISTON);

public Ramp() {

}

	public void DeployRampPiston() {
		RampPiston.set(true);
		// This method will extend the ramp piston, deploying the ramp.
	}

	public void RetractRampPiston() {
		RampPiston.set(false);
		// This will fully retract the ramp, or at least not deploy it
	}

	@Override
	protected void initDefaultCommand() {

		// this is what the ramp will do when nobody pays attention to it. It should
		// probably be closed
	}

}
