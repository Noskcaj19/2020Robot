package frc.team1523.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public class Drivetrain extends SubsystemBase{

    private final PWMVictorSPX m_leftMotor = new PWMVictorSPX(0);
    private final PWMVictorSPX m_rightMotor = new PWMVictorSPX(1);
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private final XboxController m_driverController = new XboxController(0);
    
}