package ftc8564TestOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

/**
 * Created by margaretli on 1/5/18.
 *
 * DEMO FOR CHILDREN
 *
 * LITERALLY ONLY PUTS PRESSURE ON JEWEL ARM AND DRIVES
 */

@TeleOp(name = "Demo", group = "TeleOp")
public class DemoTeleOp extends OpMode {

    //for time and stuff
    private ElapsedTime runtime = new ElapsedTime();

    //object creation !!
    private DcMotor left;
    private DcMotor right;
    Servo claw;
    DcMotor binch;
    DcMotor lift;
    DcMotor hugleft;
    DcMotor hugright;
    DcMotor pull;
    DcMotor longer;

    //variablesssss
    private double slow = 1;
    private double threshold = 0.3;
    private boolean relicMode = false;
    private double rightPower;
    private double leftPower;

    private double scalePower(double dVal)
    {
        return (Math.signum(dVal) * ((Math.pow(dVal, 2) * (.9)) + .1));
    }

    @Override
    public void init(){
        //mapping
        left = hardwareMap.dcMotor.get("left");
        right  = hardwareMap.dcMotor.get("right");
        lift = hardwareMap.dcMotor.get("liftleft");
        binch = hardwareMap.dcMotor.get("binch");
        claw = hardwareMap.servo.get("claw");
        hugleft = hardwareMap.dcMotor.get("hugleft");
        hugright = hardwareMap.dcMotor.get("hugright");
        pull = hardwareMap.dcMotor.get("pull");
        longer = hardwareMap.dcMotor.get("longer");
    }
    @Override
    public void start() {
    }

    @Override
    public void loop() {
        runtime.reset();

        //drivetrain
        if (abs(gamepad1.left_stick_y) > .2) {
            left.setPower(-gamepad1.left_stick_y * slow);
        }
        else {
            left.setPower(0);
        }
        if (abs(gamepad1.right_stick_y) > .2) {
            right.setPower(gamepad1.right_stick_y * slow);
        }
        else {
            right.setPower(0);
        }
        //spit
        if (gamepad1.right_bumper) {
            hugleft.setPower(-.8);
            hugright.setPower(.8);
        }
        //swallow
        else if (gamepad1.right_trigger > .6) {
            if (leftPower < .8){
                leftPower = (scalePower(gamepad1.right_trigger));
                rightPower = (scalePower(gamepad1.right_trigger));
            }
            else{
                leftPower = 0.8;
                rightPower = 0.8;
            }
            hugleft.setPower(leftPower);
            hugright.setPower(-rightPower);
        }
        else{
            hugleft.setPower(0);
            hugright.setPower(0);
        }

        if (gamepad1.left_trigger > .6){
            pull.setPower(1);
        }
        if (gamepad1.left_bumper){
            pull.setPower(-1);
        }
        else {
            pull.setPower(0);
        }



        //P2Controls
        //RelicSwitch
        if (!relicMode) {
            //Normal Modes
            if ((abs(gamepad2.right_stick_y)) > threshold) {
                lift.setPower(-gamepad2.right_stick_y);
            } else {
                lift.setPower(0);
            }

        }


        else{
            //Relic Mode
            if ((abs(gamepad2.left_stick_y)) > threshold) {

            } else {

            }
            if ((abs(gamepad2.right_stick_y)) > threshold) {
                binch.setPower(gamepad2.right_stick_y);
            } else {
                binch.setPower(0);
            }

            if(abs(gamepad2.left_trigger) > 0.1){
                longer.setPower(0.5);
            }
            else if (abs(gamepad2.right_trigger) > 0.1) {
                longer.setPower(-0.5);
            }
            else {
                longer.setPower(-.1);
            }

            if (gamepad2.left_bumper){
                claw.setPosition(.1);
            }
            if(gamepad2.right_bumper){
                claw.setPosition(.75);
            }
        }

        //slow mode toggle
        if (gamepad1.b) {
            slow = .5;
        } else if (gamepad1.a) {
            slow = 1;
        }

        //relic mode toggle
        if (gamepad2.x) {
            relicMode = true;
        }
        if (gamepad2.y){
            relicMode = false;
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Debug","relicMode: " +relicMode);
        telemetry.update();
    }
}
