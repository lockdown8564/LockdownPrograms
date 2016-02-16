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

package ftc8564;

/**
 * Created by Owner on 2/4/2016.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Robot {

    public DriveBase driveBase = null;
    public Hanger hanger = null;
    public ClimberDeployer climberDeployer = null;

    public Robot(LinearOpMode opMode, Boolean auto) throws InterruptedException {
        driveBase = new DriveBase(opMode);
        if(!auto) {
            driveBase.constantSpeed();
            hanger = new Hanger(opMode,driveBase);
        }
        climberDeployer = new ClimberDeployer(opMode);
    }

    public void stop() throws InterruptedException {
        driveBase.stop();
        hanger.stop();
    }

}
