package avraham.rada.screenlockerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;
import avraham.rada.screenlocklib.Constants;
import avraham.rada.screenlocklib.MySharedPreferences;
import avraham.rada.screenlocklib.ScreenLock;

public class MainActivity extends AppCompatActivity {

//    private MaterialButton main_BTN_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // - 1 -
        // Initialize sharePreferences
        MySharedPreferences.init(this);

        // - 2 - On firstActivity
        // Check if we already set default password
        if (MySharedPreferences.getString("password", null) == null) // Set new password
            ScreenLock.setNewPassword(this, Constants.NUMBERS, SuccessActivity.class);
        else {
            ScreenLock.checkPassword(this, Constants.NUMBERS, SuccessActivity.class);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check if we already set default password
        if (MySharedPreferences.getString("password", null) == null) // Set new password
            ScreenLock.setNewPassword(this, Constants.NUMBERS, SuccessActivity.class);
        else {
            ScreenLock.checkPassword(this, Constants.NUMBERS, SuccessActivity.class);
        }
    }
}