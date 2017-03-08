package br.com.thiagopsnfg.lucktracer.utils;

import android.os.Build;

public class ApiLevel {

    public static int NOUGAT_MR1 = Build.VERSION_CODES.N_MR1;
    public static int NOUGAT = Build.VERSION_CODES.N;
    public static int MARSHMALLOW = Build.VERSION_CODES.M;
    public static int LOLLIPOP_MR1 = Build.VERSION_CODES.LOLLIPOP_MR1;
    public static int LOLLIPOP = Build.VERSION_CODES.LOLLIPOP;
    public static int KITKAT_WATCH = Build.VERSION_CODES.KITKAT_WATCH;
    public static int KITKAT = Build.VERSION_CODES.KITKAT;
    public static int JELLY_BEAN_MR2 = Build.VERSION_CODES.JELLY_BEAN_MR2;
    public static int JELLY_BEAN_MR1 = Build.VERSION_CODES.JELLY_BEAN_MR1;
    public static int JELLY_BEAN = Build.VERSION_CODES.JELLY_BEAN;
    public static int ICE_CREAM_SANDWICH_MR1 = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
    public static int ICE_CREAM_SANDWICH = Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    public static int HONEYCOMB_MR2 = Build.VERSION_CODES.HONEYCOMB_MR2;
    public static int HONEYCOMB_MR1 = Build.VERSION_CODES.HONEYCOMB_MR1;
    public static int HONEYCOMB = Build.VERSION_CODES.HONEYCOMB;
    public static int GINGERBREAD = Build.VERSION_CODES.GINGERBREAD;
    public static int FROYO = Build.VERSION_CODES.FROYO;
    public static int ECLAIR = Build.VERSION_CODES.ECLAIR;
    public static int DONUT = Build.VERSION_CODES.DONUT;
    public static int CUPCAKE = Build.VERSION_CODES.CUPCAKE;

    public static boolean require(int level) {
        return Build.VERSION.SDK_INT >= level;
    }

    public static boolean isLowerThan(int apiLevel) {
        return Build.VERSION.SDK_INT < apiLevel;
    }
}
