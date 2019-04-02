package feinno.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclickTest(View view) {
        long currentTime = System.currentTimeMillis();
        Log.e(TAG, "onclickTest currentTime: " + currentTime);
        String dateStr = DateUtils.stampToDate(currentTime);
        Log.e(TAG, "onclickTest dateStr: " + dateStr);
    }
}
