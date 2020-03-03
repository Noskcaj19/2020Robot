package frc.team1523.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {
    private final CANSparkMax intake = new CANSparkMax(7, CANSparkMax.MotorType.kBrushed);
    private final CANSparkMax wristcontroller = new CANSparkMax(8, CANSparkMax.MotorType.kBrushed);

    public void setWristSpeed(double speed) {
        wristcontroller.set(speed);
    }

    public void setIntakeSpeed(double speed) {
        intake.set(speed);
    }
}