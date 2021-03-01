package avraham.rada.screenlocklib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LockScreenActivity extends AppCompatActivity implements NextActivity {

    private TextView lockScreenActivity_LBL_inputpassword;
    private TextView lockScreenActivity_LBL_password;
    private Button lockScreenActivity_BTN_check;
    private Button lockScreenActivity_BTN_delete;

    private static Class next;

    private int[] digits = {
            R.id.lockScreenActivity_BTN_number1,
            R.id.lockScreenActivity_BTN_number2,
            R.id.lockScreenActivity_BTN_number3,
            R.id.lockScreenActivity_BTN_number4,
            R.id.lockScreenActivity_BTN_number5,
            R.id.lockScreenActivity_BTN_number6,
            R.id.lockScreenActivity_BTN_number7,
            R.id.lockScreenActivity_BTN_number8,
            R.id.lockScreenActivity_BTN_number9,
            R.id.lockScreenActivity_BTN_number0
    };


    private String tempPassword = "", realPass = "";
    private String status = "";
    String tempPass = "";

    private final String checkStatus = "check";
    private final String setStatus = "newPassword"; // new password
    private final String setStatus1 = "newPassword1";
    private final String changeStatus = "change";
    private final String changeStatus1 = "change1";
    private final String changeStatus2 = "change2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        MySharedPreferences.init(this);

        // Get password if already set once, else get null
        realPass = getPassword();

        // Initialize relevant variables
        initVariables();

        initElements();

        // At first: status -> "set"
        status = getIntent().getExtras().getString("passwordStatus", "noStatus");
        // status = "set"
        if (status.equals(setStatus))
            lockScreenActivity_LBL_password.setText("Set a new password:");
    }

    /*
        return saved value of key: password. If not exist return: null
     */
    private String getPassword() {
        return MySharedPreferences.getString("password", null);
    }

    private void initVariables() {
        lockScreenActivity_LBL_password = findViewById(R.id.lockScreenActivity_LBL_password);
        lockScreenActivity_LBL_inputpassword = findViewById(R.id.lockScreenActivity_LBL_inputpassword);
        lockScreenActivity_BTN_check = findViewById(R.id.lockScreenActivity_BTN_check);
        lockScreenActivity_BTN_delete = findViewById(R.id.lockScreenActivity_BTN_delete);
    }

    private void initElements() {
        // DELETE characters
        lockScreenActivity_BTN_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete last digit that added to the password
                if (tempPassword.length() > 0)
                    tempPassword = tempPassword.substring(0, tempPassword.length() - 1);
                lockScreenActivity_LBL_inputpassword.setText(tempPassword);
            }
        });

        // CHECK PASSWORD
        lockScreenActivity_BTN_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // status = "check"
                if (status.equals(checkStatus)) {
                    if (tempPassword.equals(realPass))
                        nextActivity();
                    else {
                        // delete temp password user entered
                        tempPassword = "";
                        lockScreenActivity_LBL_inputpassword.setText(tempPassword);
                        Toast.makeText(LockScreenActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                }

                // status = setNewPassword
                else if (status.equals(setStatus)) {
                    tempPass = tempPassword; // first password
                    tempPassword = "";
                    status = setStatus1; // status = "set1" - confirm password

                    lockScreenActivity_LBL_password.setText("Confirm your Password:");
                    lockScreenActivity_LBL_inputpassword.setText(tempPassword);
                }

                // status = confirm NewPassword
                else if (status.equals(setStatus1)) {
                    if (tempPassword.equals(tempPass)) { // password2 == password1
                        MySharedPreferences.put("password", tempPassword); // save new password to SP
                        Toast.makeText(LockScreenActivity.this, "Password saved!", Toast.LENGTH_SHORT).show();
                        nextActivity();

                    } else {
                        tempPass = tempPassword;
                        tempPassword = "";
                        tempPass = "";
                        status = setStatus;

                        lockScreenActivity_LBL_inputpassword.setText(tempPassword);
                        lockScreenActivity_LBL_password.setText("Enter a New Password");
                        Toast.makeText(LockScreenActivity.this, "Enter a New Password Again", Toast.LENGTH_SHORT).show();
                    }
                }

                // status setNewPassword (changePassword)
                else if (status.equals(changeStatus)) {
                    if (tempPassword.equals(realPass)) {
                        tempPass = tempPassword;
                        tempPassword = "";
                        tempPass = "";
                        status = changeStatus1;

                        lockScreenActivity_LBL_password.setText("Enter a New Password");
                        lockScreenActivity_LBL_inputpassword.setText(tempPassword);
                    } else {
                        tempPassword = "";
                        lockScreenActivity_LBL_inputpassword.setText(tempPassword);
                        Toast.makeText(LockScreenActivity.this, "Please Enter Current Password", Toast.LENGTH_SHORT).show();
                    }
                }

                // status = "change1"
                else if (status.equals(changeStatus1)) {
                    tempPass = tempPassword;
                    tempPassword = "";
                    status = changeStatus2;

                    lockScreenActivity_LBL_password.setText("Confirm Password");
                    lockScreenActivity_LBL_inputpassword.setText(tempPassword);
                }

                // status = "change2"
                else if (status.equals(changeStatus2)) {
                    if (tempPassword.equals(tempPass)) {
                        MySharedPreferences.put("password", tempPassword);
                        Toast.makeText(LockScreenActivity.this, "Password Changed", Toast.LENGTH_SHORT).show();
                        nextActivity();
                    } else {

                        tempPass = tempPassword;
                        tempPassword = "";
                        tempPass = "";
                        status = changeStatus1;

                        lockScreenActivity_LBL_inputpassword.setText(tempPassword);
                        lockScreenActivity_LBL_password.setText("Enter a New Password");
                        Toast.makeText(LockScreenActivity.this, "Please Enter a New Password Again", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        for (int i = 0; i < digits.length; i++) {
            Button digitBtn = findViewById(digits[i]);
            digitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tempPassword.length() >= 6)
                        Toast.makeText(LockScreenActivity.this, "Max 6 digits", Toast.LENGTH_SHORT).show();
                    else
                        tempPassword += digitBtn.getText().toString();
                    lockScreenActivity_LBL_inputpassword.setText(tempPassword);
                }
            });
        }
    }

    //
    private void nextActivity() {
        startActivity(new Intent(LockScreenActivity.this, next));
        finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (status.equals("check")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            } else {
                ActivityCompat.finishAffinity(this);
            }
        }
    }

    // activityClass
    @Override
    public void setNextActivity(Class nextActivity) {
        next = nextActivity;
    }
}