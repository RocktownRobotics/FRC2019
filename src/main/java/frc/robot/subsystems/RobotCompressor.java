package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Compress;

public class RobotCompressor extends Subsystem {

	private Compressor compressor = new Compressor();

	public RobotCompressor() {

	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Compress());
	}
	
	public void start() {
		compressor.start();
	}

	public void stop() {
		compressor.stop();
	}
}