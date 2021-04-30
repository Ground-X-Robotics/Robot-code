package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.net.*; 
import java.io.*;

@TeleOp(name="Basic: Java Controller", group="ControllerJava")

public class robotNetworkController extends LinearOpMode {
    private Socket socket            = null; 
    private ServerSocket server      = null; 
    private DataInputStream in       = null;
    
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    
    @Override
    public void runOpMode() {
        try {
            server = new ServerSocket(5000); 
            
            telemetry.addData("Status", "I AM A SERVER AND WAITING FOR A CLIENT: ", runtime.toString());
            telemetry.update();
            
            socket = server.accept();
            telemetry.addData("Status", "CLIENT FOUND: ", runtime.toString());
            
            in = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
            telemetry.addData("Status", "I AM A SERVER: ", runtime.toString());
            //telemetry.update();
                
                
            String line = "";
            telemetry.addData("Status", "Initialized");
            //telemetry.update();
            
            // Initialize the hardware variables. Note that the strings used here as parameters
            // to 'get' must correspond to the names assigned during the robot configuration
            // step (using the FTC Robot Controller app on the phone).
            leftDrive  = hardwareMap.get(DcMotor.class, "left");
            rightDrive = hardwareMap.get(DcMotor.class, "right");
            
            // Most robots need the motor on one side to be reversed to drive forward
            // Reverse the motor that runs backwards when connected directly to the battery
            leftDrive.setDirection(DcMotor.Direction.FORWARD);
            rightDrive.setDirection(DcMotor.Direction.REVERSE);
            
            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();
            
            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) 
            {
                try 
                {
                    line = in.readUTF();
                    telemetry.addData("Status", "I AM READING A LINE: ", line);
                    
                } catch(final IOException i)
                {
                    telemetry.addData("Status", "I AM NOT READING A LINE: ", i);
                }
                
                //Control motors
                
                // Debug message
                if (line.equals("test") || line.equals("test2") || line.equals("b'test'") || line.equals("b'test2'")
                || line.equals("b'Forward'")
                || line.equals("b'Reverse'")
                || line.equals("b'Left'")
                || line.equals("b'Right'")
                ) 
                { telemetry.addData("IT WORKS! Received test message", line); }
                
                if (line.equals("b'Forward'")) { leftDrive.setPower(-1.0); rightDrive.setPower(-1.0); }
                if (line.equals("b'Reverse'")) { leftDrive.setPower(1.0); rightDrive.setPower(1.0); }
                if (line.equals("b'Left'")) { leftDrive.setPower(-0.2); rightDrive.setPower(0.2); }
                if (line.equals("b'Right'")) { leftDrive.setPower(0.2); rightDrive.setPower(-0.2); }
                if (
                    !line.equals("b'Forward'") 
                    & !line.equals("b'Reverse'") 
                    & !line.equals("b'Left'") 
                    & !line.equals("b'Right'")
                    )
                { leftDrive.setPower(0.0); rightDrive.setPower(0.0); }
                
                //telemetry.addData("Did it work?", worked.toString());
                telemetry.addData("Timestamp", runtime.toString());
                telemetry.update();
            }
            telemetry.addData("Closing connection at ", runtime.toString());
            telemetry.update();
            socket.close();
            in.close();
        } catch (final IOException i) {
            //telemetry.addData("Status", "I AM NOT A SERVER: ", runtime.toString());
            //telemetry.update();
        }
    }
}
