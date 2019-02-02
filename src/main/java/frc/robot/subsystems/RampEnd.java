package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

//again, used to be a StoppableSubsystem
public class RampEnd extends Subsystem {
	//TODO make the module number match the PCM ID
	private Solenoid RampEndPiston = new Solenoid(RobotMap.RAMP_END_PISTON);

public RampEnd() {

}

	public void DeployRampEnd() {
		RampEndPiston.set(false);
		// This method retracts the ramp tip piston, dropping the end of the ramp onto
		// the mat to allow friendly robots to board
	}

	public void RetractRampEnd() {
		RampEndPiston.set(true);
		// This method extends the ramp tip piston, raising the end of the ramp off of
		// the playfield mat
	}

	@Override
	protected void initDefaultCommand() {

		// this is what the ramp will do when nobody pays attention to it. It should
		// probably be closed
	}

}
