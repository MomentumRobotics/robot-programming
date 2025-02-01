package com.vuforia;

public class CameraDevice {
    static final CameraDevice cameraDevice = new CameraDevice();
    public static CameraDevice getInstance() {
        return cameraDevice;
    }

    public void setFlashTorchMode(boolean enabled) {
    }
}
