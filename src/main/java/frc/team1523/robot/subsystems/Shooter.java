package frc.team1523.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1523.robot.Constants;

public class Shooter extends SubsystemBase {
    private final CANSparkMax intakeAgitator = new CANSparkMax(11, CANSparkMax.MotorType.kBrushed);
    private final CANSparkMax shooterFeeder = new CANSparkMax(10, CANSparkMax.MotorType.kBrushless);
    private final CANSparkMax shooterMotor = new CANSparkMax(8, CANSparkMax.MotorType.kBrushless);
    private boolean shooting = false;

    public Shooter() {
        intakeAgitator.setInverted(true);
        shooterFeeder.setInverted(true);
    }

    @Override
    public void periodic() {
        if (shooting) {
            // Only load balls if we are at speed
            if (shooterMotor.getEncoder().getVelocity() > Constants.ShooterConstants.kShooterSpeedThreshold) {
                intakeAgitator.set(1);
            } else {
                intakeAgitator.set(0);
            }
        }
    }

    public void enableShooter() {
        shooting = true;
        shooterMotor.set(Constants.ShooterConstants.kFlywheelSpeed);
        shooterFeeder.set(Constants.ShooterConstants.kFlywheelSpeed);
    }

    public void disableShooter() {
        shooting = false;
        shooterMotor.set(0);
        shooterFeeder.set(0);
        intakeAgitator.set(0);
    }
}