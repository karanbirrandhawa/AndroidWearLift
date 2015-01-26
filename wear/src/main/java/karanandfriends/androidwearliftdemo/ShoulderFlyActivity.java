package karanandfriends.androidwearliftdemo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by anthonyluu on 15-01-19.
 */
public class ShoulderFlyActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicep_curl);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                final TextView repCount = (TextView) stub.findViewById(R.id.repCount);

                SensorManager sensorService =
                        (SensorManager) getApplicationContext()
                                .getSystemService(getApplicationContext().SENSOR_SERVICE);

                Sensor sensor = sensorService.getDefaultSensor(Sensor.TYPE_GRAVITY);

                ShoulderCurvedAlgorithmListener listener = new ShoulderCurvedAlgorithmListener(repCount, 8.0f, 0.0f);

                sensorService.registerListener(listener,
                        sensorService.getDefaultSensor(Sensor.TYPE_GRAVITY),
                        SensorManager.SENSOR_DELAY_FASTEST);

            }
        });
    }
}
