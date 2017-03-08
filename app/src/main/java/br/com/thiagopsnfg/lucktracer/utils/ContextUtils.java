package br.com.thiagopsnfg.lucktracer.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

public class ContextUtils {

    private static Context context;

    public static void initialize(@NonNull Context context) {
        ContextUtils.context = context;
    }

    public static Context getContext() {
        synchronized (ContextUtils.class) {
            if (ContextUtils.context == null)
                throw new NullPointerException("Call ContextUtil.initialize(context) within your Application onCreate() method.");

            return ContextUtils.context;
        }
    }

    public static Resources getResources() {
        return ContextUtils.getContext().getResources();
    }

    public static Resources.Theme getTheme() {
        return ContextUtils.getContext().getTheme();
    }

    public static AssetManager getAssets() {
        return ContextUtils.getContext().getAssets();
    }

    public static Configuration getConfiguration() {
        return ContextUtils.getResources().getConfiguration();
    }

    @ColorInt
    public static int getColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(ContextUtils.getContext(), colorRes);
    }

    public static String getString(@StringRes int stringRes){
        return getContext().getString(stringRes);
    }
}
