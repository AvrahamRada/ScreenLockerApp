package avraham.rada.screenlockerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import avraham.rada.screenlocklib.Constants;
import avraham.rada.screenlocklib.MySharedPreferences;
import avraham.rada.screenlocklib.ScreenLock;

public class SuccessActivity extends AppCompatActivity {
    private Button success_BTN_change;
    private Button success_BTN_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        init();

        // CHANGE PASSWORD
        success_BTN_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MySharedPreferences.getString("password", null) != null) // Set new password
                    ScreenLock.changePassword(SuccessActivity.this, Constants.NUMBERS, MainActivity.class);
            }
        });

        // CHECK PASSWORD
        success_BTN_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MySharedPreferences.getString("password", null) != null) // Set new password
                    ScreenLock.checkPassword(SuccessActivity.this, Constants.NUMBERS, SuccessActivity.class);
            }
        });
    }

    private void init() {
        success_BTN_change = findViewById(R.id.success_BTN_change);
        success_BTN_check = findViewById(R.id.success_BTN_check);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}