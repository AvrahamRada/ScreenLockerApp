package avraham.rada.screenlocklib;

import android.content.Context;
import android.content.Intent;

public class ScreenLock {

    // Saves the activity that should be open after entering correct password
    private static NextActivity nextActivityToOpen;

    // New Password
    public static void setNewPassword(Context context, int type, Class nextActivity) {
        // Initialize sharePreferences
        MySharedPreferences.init(context);

        // Which activity to open ?
        if (nextActivityToOpen == null) {
            if (type == Constants.NUMBERS)  // ScreenLock - numbers
                nextActivityToOpen = new LockScreenActivity();      // Activity to activate
            else                            // ScreenLock - slide
                nextActivityToOpen = new SlideLockScreenActivity(); // Activity to activate
        }

        // Set the next activity
        nextActivityToOpen.setNextActivity(nextActivity);
        // Open next activity
        Intent intent = new Intent(context, nextActivityToOpen.getClass());
        intent.putExtra("passwordStatus", "newPassword");
        context.startActivity(intent);
    }

    // Check Password
    public static void checkPassword(Context context, int type, Class nextActivity) {
        // Initialize sharePreferences
        MySharedPreferences.init(context);

        // Which activity to open ?
        if (nextActivityToOpen == null) {
            if (type == Constants.NUMBERS)  // ScreenLock - numbers
                nextActivityToOpen = new LockScreenActivity();      // Activity to activate
            else                            // ScreenLock - slide
                nextActivityToOpen = new SlideLockScreenActivity(); // Activity to activate
        }

        // Set the next activity
        nextActivityToOpen.setNextActivity(nextActivity);
        // There is a password in sharedPreferences
        if (MySharedPreferences.getString("password", null) != null) {
            Intent intent = new Intent(context, nextActivityToOpen.getClass());
            intent.putExtra("passwordStatus", "check");
            context.startActivity(intent);
        }
    }

    // Check Password
    public static void changePassword(Context context, int type, Class activityClassToGo) {
        // Initialize sharePreferences
        MySharedPreferences.init(context);

        // Which activity to open ?
        if (nextActivityToOpen == null) {
            if (type == Constants.NUMBERS)  // ScreenLock - numbers
                nextActivityToOpen = new LockScreenActivity();      // Activity to activate
            else                            // ScreenLock - slide
                nextActivityToOpen = new SlideLockScreenActivity(); // Activity to activate
        }

        Intent intent = new Intent(context, nextActivityToOpen.getClass());
        intent.putExtra("passwordStatus", "change");
        context.startActivity(intent);
    }
}
