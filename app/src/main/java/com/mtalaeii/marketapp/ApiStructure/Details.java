package com.mtalaeii.marketapp.ApiStructure;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Details {
    public static class Main {
        public static final String TAG = "Details";
        public static final String BASE_URL = "https://my.api.mockaroo.com/";
    }

    public static void L(String message) {
        Log.e(Main.TAG, message);
    }

    public static void T(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
