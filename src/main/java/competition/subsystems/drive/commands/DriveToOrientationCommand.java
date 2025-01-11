package competition.subsystems.drive.commands;
import edu.wpi.first.math.geometry.Rotation2d;
import javax.inject.Inject;

import competition.subsystems.pose.PoseSubsystem;
import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import xbot.common.math.PIDManager;
import xbot.common.subsystems.drive.control_logic.HeadingModule;

import static java.lang.Math.abs;


public class DriveToOrientationCommand extends BaseCommand {
    DriveSubsystem drive;
    PIDManager pid;
    HeadingModule headingModule;
    double currentPostion;
    double oldPostion;
    double goal;
    PoseSubsystem pose;
    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem , PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;

    }

    public void setTargetHeading(double heading) {
        // This method will be called by the test, and will give you a goal heading.
        // You'll need to remember this target position and use it in your calculations.
        this.goal=heading;

    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        double power = headingModule.calculateHeadingPower(goal);
        drive.tankDrive(-power, power);
       /* Rotation2d goal2d;
        goal2d= Rotation2d.fromDegrees(goal);
        double heading = pose.getCurrentHeading().getRadians();
        Rotation2d currentHeading= new Rotation2d(heading);
        Rotation2d difference2d= currentHeading.minus(goal2d); //difference (goal);
        double difference = difference2d.getDegrees();
        double power = pid.calculate(0, difference);
        drive.tankDrive(-power, power);*/

        /*currentPostion= pose.getCurrentHeading().getDegrees();
        double postionDif= currentPostion - oldPostion;
        double range = this.goal-currentPostion;
        double power= range *.8 - postionDif*1.1;
        drive.tankDrive(-power,power);
        oldPostion = pose.getCurrentHeading().getDegrees();
        System.out.println("POWER: " + power);
        System.out.println("Range: " + range);
        System.out.println("Postion diff"+ postionDif);
        // Here you'll need to figure out a technique that:
        // - Gets the robot to turn to the target orientation
        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position

        // How you do this is up to you. If you get stuck, ask a mentor or student for
        // some hints!*/

    }

    @Override
    public boolean isFinished() {
        double range = this.goal - pose.getCurrentHeading().getDegrees();
        double postionDif= currentPostion - oldPostion;
        if (range==0 && abs(postionDif) ==0 && currentPostion == goal   ) {
            return true;
        }

        return false;
    }
        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)

    }

