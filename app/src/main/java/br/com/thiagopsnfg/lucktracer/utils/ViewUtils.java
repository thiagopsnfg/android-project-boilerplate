package br.com.thiagopsnfg.lucktracer.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnimRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;
import timber.log.Timber;

public class ViewUtils {

    //region KeyBoard
    public static void hideKeyboard(View v){
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void showKeyboard(View v){
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);

    }
    //endregion

    //region Drawables
    public static Drawable getDrawable(@DrawableRes int drawableRes) {
        return ContextCompat.getDrawable(ContextUtils.getContext(), drawableRes);
    }

    public static Drawable getDrawable(@DrawableRes int drawableRes, Resources.Theme theme) {
        if (ApiLevel.require(ApiLevel.LOLLIPOP))
            return ContextUtils.getResources().getDrawable(drawableRes, theme);
        else
            return ContextUtils.getResources().getDrawable(drawableRes);
    }

    public static void setBackground(View view, Drawable drawable) {
        if (view == null)  return;

        if (ApiLevel.require(ApiLevel.JELLY_BEAN)) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setBackground(View view, @DrawableRes int drawableRes) {
        if (view == null) return;

        if (ApiLevel.require(ApiLevel.JELLY_BEAN)) {
            view.setBackground(ViewUtils.getDrawable(drawableRes));
        } else {
            view.setBackgroundDrawable(ViewUtils.getDrawable(drawableRes));
        }
    }
    //endregion

    //region Display Metrics
    private static Display getDisplay(){
        return ((WindowManager) ContextUtils.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return ContextUtils.getResources().getDisplayMetrics();
    }

    public static float convertPixelsToDp(float px) {
        DisplayMetrics metrics = ContextUtils.getResources().getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }
    //endregion

    //region Dimensions
    public static int getWidth() {
        Display display = getDisplay();
        if (ApiLevel.require(13)) {
            Point size = new Point();
            display.getSize(size);
            return size.x;
        } else {
            return display.getWidth();
        }
    }

    public static int getHeight() {
        Display display = getDisplay();
        if (ApiLevel.require(13)) {
            Point size = new Point();
            display.getSize(size);
            return size.y;
        } else {
            return display.getHeight();
        }
    }

    public static int getStatusBarHeight() {
        int resourceId = ContextUtils.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? ContextUtils.getResources().getDimensionPixelSize(resourceId) : 0;
    }

    public static int getToolbarHeight() {
        return getActionBarHeight();
    }

    public static int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        return ContextUtils.getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true) ?   TypedValue.complexToDimensionPixelSize(tv.data, getDisplayMetrics()) :  0;
    }

    public static int getNavigationBarHeight() {
        int resourceId = ContextUtils.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        return resourceId > 0 ? ContextUtils.getResources().getDimensionPixelSize(resourceId) :   0;
    }
    //endregion

    //region Orientation
    public static boolean isPortrait() {
        return getHeight() >= getWidth();
    }

    public static boolean isLandscape() {
        return getHeight() < getWidth();
    }
    //endregion

    public static XmlResourceParser getAnimation(@AnimRes int animRes) {
        return ContextUtils.getResources().getAnimation(animRes);
    }

    //region Fonts
    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s.ttf", fontAssetName));
        replaceFont(staticTypefaceFieldName, regular);
    }

    private static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);

            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            Timber.w(e);
        } catch (IllegalAccessException e) {
            Timber.w(e);
        }
    }
    //endregion

}
