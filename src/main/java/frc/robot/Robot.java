package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  private DifferentialDrive m_robotDrive;
  private VictorSP motorRightOne, motorRightTwo,
        motorLeftOne, motorLeftTwo;
  private SpeedControllerGroup motorsRight, motorsLeft;
  private XboxController exampleXbox;
  private final Timer m_timer = new Timer();

  @Override
  public void robotInit() {
      motorRightOne = new VictorSP(0);
      motorRightTwo = new VictorSP(1);
      motorLeftOne = new VictorSP(2);
      motorLeftTwo = new VictorSP(3);

      motorsRight = new SpeedControllerGroup(motorRightOne, motorRightTwo);
      motorsLeft = new SpeedControllerGroup(motorLeftOne, motorLeftTwo);

      m_robotDrive = new DifferentialDrive(motorsRight, motorsLeft);
  }

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      m_robotDrive.arcadeDrive(0.5, 0.0);
    } else {
      m_robotDrive.stopMotor();
    }
  }

  @Override
  public void teleopInit() {
    exampleXbox = new XboxController(4);

  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(exampleXbox.getY(), exampleXbox.getX());
    
    // Tank drive with a given left and right rates
    m_robotDrive.tankDrive(-exampleXbox.getY(), -exampleXbox.getY());

    // Arcade drive with a given forward and turn rate
    m_robotDrive.arcadeDrive(-exampleXbox.getY(), exampleXbox.getX());
  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
