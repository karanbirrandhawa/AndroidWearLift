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
        float x = event.values[0];

        return x;
    }
}
