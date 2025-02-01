package android.hardware;

/*
 * TODO: if we want these virtual sensors to actually *do* anything and send notifications, etc.
 *  we'll have to implement a com.qualcomm.robotcore.hardware.HardwareDevice (probably in VirtualRobotController itself)
 *  which, in its updateState() method, calls Context.sensorManager.dispatchSensorEvent().
 */

public class Sensor {

    public static final int TYPE_GYROSCOPE = 4;
    public static final int TYPE_LINEAR_ACCELERATION = 10;
    private String name;
    private int type;

    Sensor(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}

