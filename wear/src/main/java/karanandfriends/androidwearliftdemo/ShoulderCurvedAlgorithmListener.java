package karanandfriends.androidwearliftdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.TextView;

import karanandfriends.androidwearlift.exercises.ShoulderFlyManager;

/**
 * Created by anthonyluu on 15-01-19.
 */
public class ShoulderCurvedAlgorithmListener implements SensorEventListener {
    TextView textView;

    float upper_threshold;
    float lower_threshold;

    ShoulderFlyManager manager;
    int reps;

    public ShoulderCurvedAlgorithmListener(final TextView textView, float upper_threshold, float lower_threshold) {
        this.textView = textView;
        this.upper_threshold = upper_threshold;
        this.lower_threshold = lower_threshold;

        reps = 0;
        manager = new ShoulderFlyManager() {
            @Override
            protected void onRepIncrease() {
                reps++;
                Log.d("Counter", "" + reps);
                Log.d("Counter", "This executed");
                textView.setText(Integer.toString(reps));
            }
        };

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            manager.updateEvent(event);
        } else if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
