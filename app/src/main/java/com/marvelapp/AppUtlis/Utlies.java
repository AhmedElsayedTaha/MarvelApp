package com.marvelapp.AppUtlis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utlies {
    /**
     * checking if there is internet or not
     * @param context app context
     * @return boolean
     */
    public static Boolean isOnline(Context context){
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert conMgr != null;
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        if (networkInfo == null)
            return false;
        if (!networkInfo.isConnected())
            return false;
        return networkInfo.isAvailable();
    }
}
