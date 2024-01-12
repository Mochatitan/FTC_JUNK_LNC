package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "BasicDrive")
public class BasicDrive extends LinearOpMode {

  private DcMotor linearleft;
  private DcMotor linearright;
  private Servo servo2;
  private Servo servo3;
  private DcMotor FrontLeft;
  private DcMotor FrontRight;
  private DcMotor BackLeft;
  private DcMotor BackRight;
  private Servo shooterservo;
  private Servo clawmotor;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    linearleft = hardwareMap.get(DcMotor.class, "linearleft");
    linearright = hardwareMap.get(DcMotor.class, "linearright");
    servo2 = hardwareMap.get(Servo.class, "servo2");
    servo3 = hardwareMap.get(Servo.class, "servo3");
    FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
    FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
    BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
    BackRight = hardwareMap.get(DcMotor.class, "BackRight");
    shooterservo = hardwareMap.get(Servo.class, "shooter servo");
    clawmotor = hardwareMap.get(Servo.class, "clawmotor");

    // Put initialization blocks here.
    linearleft.setPower(0);
    linearright.setPower(0);
    sleep(500);
    clawmotor.setPosition(0);
    servo2.setPosition(0);
    servo3.setPosition(0.89);
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Variables
        int FL_Power = 0;
        int FR_Power = 0;
        int BL_Power = 0;
        int BR_Power = 0;
        float powervar = .5f;
        // boolean XB_Activated = false;
        // Movement.
        if(Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x)) // Moves forwards and backwards
        {
          FL_Power += (gamepad1.left_stick_y);
          FR_Power += (gamepad1.left_stick_y);
          BL_Power += ((gamepad1.left_stick_y * -1));
          BR_Power += (gamepad1.left_stick_y);
        } else if(Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) // Strafes left and right
        {
          FL_Power += (gamepad1.left_stick_x);
          FR_Power += (gamepad1.left_stick_x * -1);
          BL_Power += ((gamepad1.left_stick_x* -1) * -1);
          BR_Power += (gamepad1.left_stick_x);
        }
      
        // Turning
        if(Math.abs(gamepad1.right_stick_x) > 0) // Moves forwards and backwards
        {
          FL_Power += gamepad1.right_stick_x;
          FR_Power += -(gamepad1.right_stick_x);
          BL_Power += -gamepad1.right_stick_x;
          BR_Power += -gamepad1.right_stick_x;
        }
        
        // Update Wheel values
        FrontLeft.setPower(FL_Power);
        FrontRight.setPower(FR_Power);
        BackLeft.setPower(BL_Power);
        BackRight.setPower(BR_Power);
        
        if (gamepad1.right_bumper) {
          servo2.setPosition(0.2); // Closes claw
        }
        if (gamepad1.left_bumper) {
          servo2.setPosition(0); // Opens claw
        }
        if (gamepad1.dpad_right) {
          servo3.setPosition(0.3);
        }
        if (gamepad1.dpad_left) {
          servo3.setPosition(0.89);
        }
        if (gamepad1.left_trigger > .001) {
          linearleft.setPower(0.5);
          linearright.setPower(-0.6);
        } else {
          linearleft.setPower(0);
          linearright.setPower(0);
        }
        if (gamepad1.right_trigger > .001) {
          linearleft.setPower(.2);
          linearright.setPower(-0.2);
        } else {
          linearleft.setPower(0);
          linearright.setPower(0);
        }
        if (gamepad1.right_bumper) {
          shooterservo.setPosition(0.75);
        }
        if (gamepad1.x) {
          clawmotor.setPosition(0.68);
          sleep(1000);
          clawmotor.setPosition(-0.3);
        }
        if (gamepad1.b) {
          clawmotor.setPosition(-0.64);
          sleep(1000);
          clawmotor.setPosition(0);
        }
        telemetry.update();
      }
    }
  }
}
