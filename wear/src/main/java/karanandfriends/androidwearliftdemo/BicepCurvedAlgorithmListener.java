package karanandfriends.androidwearliftdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.TextView;

import karanandfriends.androidwearlift.exercises.BicepCurlManager;

/**
 * Created by karan on 18/01/15.
 */
public class BicepCurvedAlgorithmListener implements SensorEventListener {
    TextView textView;

    float upper_threshold;
    float lower_threshold;

    BicepCurlManager manager;
    int reps;

    public BicepCurvedAlgorithmListener(final TextView textView, float upper_threshold, float lower_threshold) {
        this.textView = textView;
        this.upper_threshold = upper_threshold;
        this.lower_threshold = lower_threshold;

        reps = 0;
        manager = new BicepCurlManager() {
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
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            manager.updateEvent(event);
        } else if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
