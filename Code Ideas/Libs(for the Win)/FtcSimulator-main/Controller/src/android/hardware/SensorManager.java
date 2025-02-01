package android.hardware;

import java.util.HashMap;
import java.util.Map;

public class SensorManager {
    public static final int SENSOR_DELAY_GAME = 1; // default sampling rate in microseconds

    HashMap<SensorEventListener, Sensor> listenerMap;

    public SensorManager() {
        listenerMap = new HashMap<SensorEventListener, Sensor>();
    }

    public void unregisterListener(SensorEventListener listener) {
        listenerMap.remove(listener);
    }

    public void registerListener(SensorEventListener listener, Sensor sensor, int samplingInterval) {
        listenerMap.put(listener, sensor);
    }

    // just return the first one found of any given type
    public Sensor getDefaultSensor(int sensorType) {
        for  (Map.Entry<SensorEventListener, Sensor> entry : listenerMap.entrySet()) {
            if (entry.getValue().getType() == sensorType)
                return entry.getValue();
        }
        return null;
    }

    // NOTE: virtual sensors should call this for each value change to send to registered listeners
    void dispatchSensorEvent(Sensor sensor, SensorEvent sensorEvent) {
        for  (Map.Entry<SensorEventListener, Sensor> entry : listenerMap.entrySet()) {
            if (entry.getValue()==sensor) {
                entry.getKey().onSensorChanged(sensorEvent);
            }
        }
    }

    // NOTE: virtual sensors should call this whenever the accuracy changes
    void dispatchSensorAccuracyChange(Sensor sensor, int accuracy) {
        for  (Map.Entry<SensorEventListener, Sensor> entry : listenerMap.entrySet()) {
            if (entry.getValue()==sensor) {
                entry.getKey().onAccuracyChanged(sensor, accuracy);
            }
        }
    }
}
