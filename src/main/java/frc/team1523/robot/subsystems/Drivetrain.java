package frc.team1523.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private final WPI_TalonFX leftfront = new WPI_TalonFX(2);
    private final WPI_TalonFX rightrear = new WPI_TalonFX(3);
    private final WPI_TalonSRX leftrear = new WPI_TalonSRX(4);
    private final WPI_TalonSRX rightfront = new WPI_TalonSRX(5);

    SpeedController leftmotors = new SpeedControllerGroup(leftfront, leftrear);
    SpeedController rightmotors = new SpeedControllerGroup(rightfront, rightrear);
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftmotors, rightmotors);

    public Drivetrain() {
        leftfront.configOpenloopRamp(.2);
        leftfront.setNeutralMode(NeutralMode.Brake);
        leftrear.configOpenloopRamp(.2);
        leftrear.setNeutralMode(NeutralMode.Brake);
        rightfront.configOpenloopRamp(.2);
        rightfront.setNeutralMode(NeutralMode.Brake);
        rightrear.configOpenloopRamp(.2);
        rightrear.setNeutralMode(NeutralMode.Brake);
    }

    public void drive(double xSpeed, double rotation){
        m_robotDrive.arcadeDrive(xSpeed, rotation);
    }


}