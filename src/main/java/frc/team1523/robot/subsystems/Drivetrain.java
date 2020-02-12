package frc.team1523.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private final WPI_TalonFX m_leftMotor = new WPI_TalonFX(2);
    private final WPI_TalonFX m_rightMotor = new WPI_TalonFX(3);
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
}