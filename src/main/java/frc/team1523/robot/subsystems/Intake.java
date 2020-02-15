package frc.team1523.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMax;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class Intake extends SubsystemBase {
    CANSparkMax intake = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);

    public void setMotorSpeed(double speed){
        intake.set(speed);



    }



}