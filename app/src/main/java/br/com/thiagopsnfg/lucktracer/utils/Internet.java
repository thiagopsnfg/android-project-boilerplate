package br.com.thiagopsnfg.lucktracer.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by thiagosantos on 08/03/17.
 */

public class Internet {

    public static boolean hasInternet() {
        boolean isInternet = Boolean.FALSE;

        ConnectivityManager connectivity = (ConnectivityManager) ContextUtils.getContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) return Boolean.FALSE;

        NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

        if (netInfo == null) return Boolean.FALSE;

        int netType = netInfo.getType();

        if (netType == ConnectivityManager.TYPE_WIFI || netType == ConnectivityManager.TYPE_MOBILE || netType == ConnectivityManager.TYPE_WIMAX) {
            return netInfo.isConnected();
        } else {
            return Boolean.FALSE;
        }
    }
}
