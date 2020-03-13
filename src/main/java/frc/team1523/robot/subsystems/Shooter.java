package frc.team1523.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

import java.util.function.BooleanSupplier;

import static frc.team1523.robot.Constants.ShooterConstants;

public class Shooter extends PIDSubsystem {
    private final CANSparkMax intakeAgitator = new CANSparkMax(11, CANSparkMax.MotorType.kBrushed);
    //    private final CANSparkMax shooterFeeder = new CANSparkMax(10, CANSparkMax.MotorType.kBrushless);
    private final CANSparkMax shooterMotor = new CANSparkMax(8, CANSparkMax.MotorType.kBrushless);

    private final SimpleMotorFeedforward shooterFeedforward = new SimpleMotorFeedforward(
            ShooterConstants.kSVolts,
            ShooterConstants.kVVoltSecondsPerRotation);
    private final NetworkTableEntry speedEntry = Shuffleboard.getTab("Debug")
            .add("Shooter Speed", 0.0)
            .getEntry();
    private boolean shooting = false;

    public Shooter() {
        super(new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD));
        Shuffleboard.getTab("Debug").add("Shooter PID", getController());
        intakeAgitator.restoreFactoryDefaults();
//        shooterFeeder.restoreFactoryDefaults();
        shooterMotor.restoreFactoryDefaults();

        shooterMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
//        shooterFeeder.setIdleMode(CANSparkMax.IdleMode.kCoast);

        shooterMotor.setSmartCurrentLimit(50);
        intakeAgitator.setInverted(false);
//        shooterFeeder.setInverted(false);
        shooterMotor.setInverted(true);

        getController().setTolerance(70);

        disable();
    }

    @Override
    public void periodic() {
        super.periodic();
        speedEntry.setNumber(shooterMotor.getEncoder().getVelocity());
        if (shooting) {
            // Only load balls if we are at speed
            if (atSetpoint()) {
                intakeAgitator.set(1);
            } else {
                intakeAgitator.set(0);
            }
        }
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        shooterMotor.setVoltage(output + shooterFeedforward.calculate(setpoint));
    }

    @Override
    protected double getMeasurement() {
        return shooterMotor.getEncoder().getVelocity();
    }

    // set speed for testing
    public void testingSetMotorSpeed(double speed) {
        shooterMotor.set(speed);
//        shooterFeeder.set(speed * .75);
        intakeAgitator.set(speed * .75);
    }

    public void enableShooter() {
        shooting = true;
        setSetpoint(5000);
        enable();
//        shooterMotor.set(ShooterConstants.kFlywheelSpeed);
//        shooterFeeder.set(Constants.ShooterConstants.kFlywheelSpeed);
    }


    public boolean atSetpoint() {
        return m_controller.atSetpoint();
    }

    public void disableShooter() {
        shooting = false;
        setSetpoint(0);
        disable();
//        shooterFeeder.set(0);
        intakeAgitator.set(0);
    }
}