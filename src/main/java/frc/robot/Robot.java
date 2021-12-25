package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Robot extends TimedRobot {
  private DifferentialDrive drive;
  private VictorSP motorRightOne, motorRightTwo, motorLeftOne, motorLeftTwo;
  private SpeedControllerGroup motorsRight, motorsLeft;
  private XboxController driver;
  private PWMVictorSPX motorController;
  private DigitalInput toplimitSwitch;
  private DigitalInput bottomlimitSwitch;

  @Override
  public void robotInit() {
      motorController =  new PWMVictorSPX(0);
      toplimitSwitch = new DigitalInput(1);
      bottomlimitSwitch = new DigitalInput(2);

      motorRightOne = new VictorSP(0);
      motorRightTwo = new VictorSP(1);
      motorLeftOne = new VictorSP(2);
      motorLeftTwo = new VictorSP(3);

      motorsRight = new SpeedControllerGroup(motorRightOne, motorRightTwo);
      motorsLeft = new SpeedControllerGroup(motorLeftOne, motorLeftTwo);

      drive = new DifferentialDrive(motorsRight, motorsLeft);
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
      driver = new XboxController(4);
  }

  @Override
  public void teleopPeriodic() {
      drive.arcadeDrive(driver.getY(Hand.kLeft), driver.getX(Hand.kRight));
      setMotorSpeed(driver.getRawAxis(2));
  }
  public void setMotorSpeed(double speed) {
    if (speed > 0) {
        if (toplimitSwitch.get()) {
            motorController.set(0);
        } else {
            motorController.set(speed);
        }
    } else {
        if (bottomlimitSwitch.get()) {
            motorController.set(0);
        } else {
            motorController.set(speed);
        }
    }
}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
