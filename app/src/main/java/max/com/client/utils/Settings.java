package max.com.client.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import max.com.client.model.User;

/**
 * Created by Maxim on 9/28/2017.
 */

public class Settings {
    public static final String APP_PREFERENSES_PHONE = "PHONE";
    public static final String APP_PREFERENSES_PASS = "PASS";
    private static SharedPreferences sharedPreferences;
    public static final String APP_PREFERENCES = "SETTINGS";
    public static String userPhone;

    public static User currentUser;

    public static void init(Context context) {
        currentUser = new User();
        sharedPreferences = getSharedPreferences(context);
    }

    public static void init(Bundle saveInstanceState) {
        if (saveInstanceState != null) {
            currentUser = new User();
            Settings.currentUser.setPhone(saveInstanceState.getString("phone"));
            Settings.currentUser.setPassword(saveInstanceState.getString("password"));
            Settings.currentUser.setRate(saveInstanceState.getString("rate"));
            Settings.currentUser.setBalance(saveInstanceState.getInt("balance"));
        }
    }

    public static void save(Bundle outState){
        outState.putString("phone", Settings.currentUser.getPhone());
        outState.putString("password", Settings.currentUser.getPassword());
        outState.putString("rate", Settings.currentUser.getPassword());
        outState.putString("balance", Settings.currentUser.getPassword());
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        Context appContext = context.getApplicationContext();
        if (sharedPreferences == null) {
            sharedPreferences = appContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static SharedPreferences getSettings() {
        return sharedPreferences;
    }

}
