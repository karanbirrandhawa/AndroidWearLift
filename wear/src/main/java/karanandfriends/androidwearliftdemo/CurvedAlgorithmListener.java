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
public class CurvedAlgorithmListener implements SensorEventListener {
    TextView textView;

    float upper_threshold;
    float lower_threshold;

    BicepCurlManager manager;
    boolean state; // true or false
    int reps;

    public CurvedAlgorithmListener(final TextView textView, float upper_threshold, float lower_threshold) {
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

        state = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            manager.updateEvent(event);
//            float value = Float.parseFloat(String.valueOf(this.repCount(event)));
//            Log.d("distance", "" + value);
//            if (!state) {
//                if (value > 6.5d) {
//                    reps++;
//                    textView.setText(Integer.toString(reps));
//                    Log.d("Rep", "Rep increased" + reps);
//                    state = !state;
//                }
//            } else {
//                if (value < 0.0d) {
//                    state = !state;
//                }
//            }
//            this.textView.setText(String.valueOf(this.repCount(event)));
        } else if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float repCount(SensorEvent event) {
        float[] values = event.values;
        float[] gravityVal = new float[3];

        float x = values[0];
        float y = values[1];
        float z = values[2];

        final float alpha = 0.8f;

        // Isolate the force of gravity with the low-pass filter.
        gravityVal[0] = alpha * gravityVal[0] + (1 - alpha) * event.values[0];
        gravityVal[1] = alpha * gravityVal[1] + (1 - alpha) * event.values[1];
        gravityVal[2] = alpha * gravityVal[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        float linearX = event.values[0] - gravityVal[0];
        float linearY = event.values[1] - gravityVal[1];
        float linearZ = event.values[2] - gravityVal[2];

        float rel_z = (float) (z/Math.sqrt(Math.pow(linearX, 2) + Math.pow(linearY, 2) + Math.pow(linearZ, 2) + Math.pow(1.0, -6)));

        if (rel_z > 0.9 && Math.abs(z) > 0.1) {
            for(int i = 0;i<1000;i++) {
                if(z < 0){
                    z = z - z*2;
                }
            }

            double freq = 1;

            double pi = Math.PI;
            double gravity = 9806.65;

            double result = 0;
            result = (gravity*z)/(2*pi*freq);

//            Log.d("event x", String.valueOf(x));
//            Log.d("event y", String.valueOf(y));
//            Log.d("event z", String.valueOf(z));

//            Log.d("distance", "" + result);
//            textView.setTextSize(12);

        }
        return x;
    }
}
