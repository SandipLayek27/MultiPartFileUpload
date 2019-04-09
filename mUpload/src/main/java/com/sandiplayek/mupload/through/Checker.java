package com.sandiplayek.mupload.through;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Checker {

    private static NetworkInfo networkInfo;

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                return true;
            }else{
                networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
                if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                    return true;
                }else{
                    networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                    if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
