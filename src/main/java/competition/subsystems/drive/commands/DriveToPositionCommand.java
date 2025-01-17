package competition.subsystems.drive.commands;

import javax.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;

import static java.lang.Math.abs;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    double goal;
    double oldPostion;
    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }

    public void setTargetPosition(double position) {
        this.goal=position;
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        pose.getPosition();
        double postionDiff= pose.getPosition()-oldPostion;
        double range= this.goal-pose.getPosition();
        double power= range*5 -postionDiff*50;
        drive.tankDrive(power,power);
        oldPostion= pose.getPosition();


        // Here you'll need to figure out a technique that:
        // - Gets the robot to move to the target position
        // - Hint: use pose.getPosition() to find out where you are
        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position

        // How you do this is up to you. If you get stuck, ask a mentor or student for
        // some hints!

        // get the postion at 5
    }

    @Override
    public boolean isFinished() {
        double range = this.goal- pose.getPosition();
        double postionDiff= pose.getPosition()- oldPostion;
        if (abs(postionDiff) < .001 && range <.001 && range > 0) {
            return true;
        }
        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)
        return false;
    }

}
