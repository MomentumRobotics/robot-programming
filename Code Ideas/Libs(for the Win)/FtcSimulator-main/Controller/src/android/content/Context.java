package android.content;

import android.hardware.SensorManager;

public class Context {
    static SensorManager sensorManager = new SensorManager();
    public static final String SENSOR_SERVICE = "sensor";

    public Object getSystemService(String serviceName) {
        if (SENSOR_SERVICE.equals(serviceName)) {
            return sensorManager;
        }
        return null;
    }
}
