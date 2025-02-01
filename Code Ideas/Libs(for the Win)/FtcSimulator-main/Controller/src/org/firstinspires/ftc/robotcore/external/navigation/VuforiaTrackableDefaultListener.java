package org.firstinspires.ftc.robotcore.external.navigation;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;

public class VuforiaTrackableDefaultListener {

    // FIXME: by adding an update() function to this and calling it from the virtual robot's
    // updateAndSensors() method, it should be possible to have these functions actually return
    // valid poses and robot locations.

    // We'd have to be clever & use some heuristics about determining isVisible,
    // maybe assume a 90deg field of view and a 3-4 foot distance?

    public OpenGLMatrix getRobotLocation() {
        return OpenGLMatrix.identityMatrix();
    }
    public OpenGLMatrix getPose() {
        return OpenGLMatrix.identityMatrix();
    }
    public boolean isVisible() {
        return false;
    }

    public void setPhoneInformation(OpenGLMatrix phoneLocationOnRobot, VuforiaLocalizer.CameraDirection cameraDir) {
    }
    public void setCameraLocationOnRobot(CameraName cameraName, OpenGLMatrix cameraLocationOnRobot) {
    }
}
