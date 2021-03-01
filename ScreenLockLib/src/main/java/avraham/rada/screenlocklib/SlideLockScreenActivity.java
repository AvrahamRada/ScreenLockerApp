package avraham.rada.screenlocklib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SlideLockScreenActivity extends AppCompatActivity implements NextActivity {

    private static Class next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_lock_screen);
    }

    @Override
    public void setNextActivity(Class nextActivity) {
        next = nextActivity;
    }
}