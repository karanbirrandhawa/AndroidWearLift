package karanandfriends.androidwearlift.exercises;

import android.hardware.SensorEvent;
import android.util.Log;

public abstract class ShoulderFlyManager {

    // User must implement this function to have something done on a cycle completion
    protected abstract void onRepIncrease();


    boolean state = false; // Two states, 0 and 1. 0 = Raising towards self, 1 = lowering

    public void updateEvent(SensorEvent event){
        float value = currentPos(event);
        Log.d("Update", "" + value);
        if (!state) {
            if (value > 1.2d) {
                onRepIncrease();
                state = !state;
            }
        } else {
            if (value < -8.0d) {
                state = !state;
            }
        }
    }



    private float currentPos(SensorEvent event) {
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
        }
        return x;
    }
}
