package frc.team1523.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
    // Auto chooser, add additional autos here
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private final SendableChooser<String> chooser = new SendableChooser<>();

    // Create subsystems here

    public RobotContainer() {
        configureButtonBindings();

        // Global initalization here
        chooser.setDefaultOption("Default Auto", kDefaultAuto);
        chooser.addOption("My Auto", kCustomAuto);
        Shuffleboard.getTab("Drive").add("Auto choicess", chooser);
    }

    private void configureButtonBindings() {
        // Define button bindings here
    }

    public Command getAutonomousCommand() {
        String m_autoSelected = chooser.getSelected();
        switch (m_autoSelected) {
            case kCustomAuto:
                return new InstantCommand();
            case kDefaultAuto:
            default:
                return new InstantCommand();
        }
    }
}