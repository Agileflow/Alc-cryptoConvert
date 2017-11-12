package com.alc.agileflow.cryptoexchange.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by dell pc on 11/3/2017.
 */

public class Helper {
    public static void showToast(Context context, String message){
        Toast.makeText(context,
                message,
                Toast.LENGTH_SHORT).show();
    }

    public static double convertPrice(String price){
        Log.i("HELPER", price.indexOf(' ', 0) + " price: " + price);
        String rate = price.substring(price.indexOf(' ', 0)).replaceAll(",","");

        return Double.parseDouble(rate);
    }
}
