/*
 * Lockdown Framework Library
 * Copyright (c) 2015 Lockdown Team 8564 (lockdown8564.weebly.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ftc8564opMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import ftclib.*;
import ftc8564lib.*;

@Autonomous(name="TestAutonomous", group="Autonomous")
public class TestAutonomous extends LinearOpMode implements DriveBase.AbortTrigger {

    Robot robot;

    public enum Alliance {
        RED_ALLIANCE,
        BLUE_ALLIANCE
    }

    private Alliance alliance = Alliance.RED_ALLIANCE;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this,true);
        waitForStart();

        robot.driveBase.drivePID(-25, null);
        robot.driveBase.spinPID(35);
        robot.driveBase.drivePID(-50, null);
        robot.driveBase.spinPID(55);
        robot.driveBase.drivePID(-16, null);
        robot.driveBase.drivePID(4, null);
        robot.driveBase.spinPID(-90);

        robot.shooter.resetMotors();
        robot.pulleySystem.resetMotors();
        robot.driveBase.resetMotors();
        robot.driveBase.resetPIDDrive();

    }

    @Override
    public boolean shouldAbort() { return robot.odsLeft.getLightDetected() >= 0.2 || robot.odsRight.getLightDetected() >= 0.2; }

}