package avraham.rada.screenlocklib;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    public static SharedPreferences sharedPreferences;

    public static SharedPreferences init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static void put(String title, String value) {
        sharedPreferences.edit().putString(title, value).apply();
    }

    public static String getString(String title, String defaultValue) {
        return sharedPreferences.getString(title, defaultValue);
    }

    public static void clearAll() {
        sharedPreferences.edit().clear().commit();
    }

}
