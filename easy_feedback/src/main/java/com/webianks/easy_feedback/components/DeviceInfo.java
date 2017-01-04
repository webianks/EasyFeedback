package com.webianks.easy_feedback.components;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;
import java.util.TimeZone;

import static com.webianks.easy_feedback.components.DeviceInfo.Device.DEVICE_LANGUAGE;

/**
 * Created by R Ankit on 08-12-2016.
 */

public class DeviceInfo {

    public enum Device {

        DEVICE_TYPE, DEVICE_VERSION, DEVICE_SYSTEM_VERSION,
        /**
         *
         */
        DEVICE_LANGUAGE, DEVICE_TIME_ZONE,
        /**
         *
         */
        DEVICE_TOTAL_MEMORY, DEVICE_FREE_MEMORY,


    }


    public static String getAllDeviceInfo(Context context, boolean fromDialog) {

        StringBuilder stringBuilder = new StringBuilder();

        if (!fromDialog)
            stringBuilder = new StringBuilder("\n\n ==== SYSTEM-INFO ===\n\n");

        stringBuilder.append("\n Device: " + getDeviceInfo(context, DeviceInfo.Device.DEVICE_SYSTEM_VERSION));
        stringBuilder.append("\n SDK Version: " + getDeviceInfo(context, DeviceInfo.Device.DEVICE_VERSION));
        stringBuilder.append("\n App Version: " + getAppVersion(context));
        stringBuilder.append("\n Language: " + getDeviceInfo(context, DEVICE_LANGUAGE));
        stringBuilder.append("\n TimeZone: " + getDeviceInfo(context, DeviceInfo.Device.DEVICE_TIME_ZONE));
        stringBuilder.append("\n Total Memory: " + getDeviceInfo(context, DeviceInfo.Device.DEVICE_TOTAL_MEMORY));
        stringBuilder.append("\n Free Memory: " + getDeviceInfo(context, DeviceInfo.Device.DEVICE_FREE_MEMORY));
        stringBuilder.append("\n Device Type: " + getDeviceInfo(context, DeviceInfo.Device.DEVICE_TYPE));
        stringBuilder.append("\n Data Type: " + DeviceInfo.getDataType(context));

        return stringBuilder.toString();
    }


    public static String getDeviceInfo(Context activity, Device device) {
        try {
            switch (device) {

                case DEVICE_LANGUAGE:
                    return Locale.getDefault().getDisplayLanguage();

                case DEVICE_TIME_ZONE:
                    return TimeZone.getDefault().getID();//(false, TimeZone.SHORT);

                case DEVICE_TOTAL_MEMORY:
                    if (Build.VERSION.SDK_INT >= 16)
                        return String.valueOf(getTotalMemory(activity));

                case DEVICE_FREE_MEMORY:
                    return String.valueOf(getFreeMemory(activity));

                case DEVICE_SYSTEM_VERSION:
                    return String.valueOf(getDeviceName());

                case DEVICE_VERSION:
                    return String.valueOf("SDK " + android.os.Build.VERSION.SDK_INT);

                case DEVICE_TYPE:
                    if (isTablet(activity)) {
                        if (getDeviceMoreThan5Inch(activity)) {
                            return "Tablet";
                        } else
                            return "Mobile";
                    } else {
                        return "Mobile";
                    }
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    @SuppressLint("NewApi")
    private static long getTotalMemory(Context activity) {
        try {
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.getMemoryInfo(mi);
            long availableMegs = mi.totalMem / 1048576L; // in megabyte (mb)

            return availableMegs;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static long getFreeMemory(Context activity) {
        try {
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.getMemoryInfo(mi);
            long availableMegs = mi.availMem / 1048576L; // in megabyte (mb)

            return availableMegs;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean getDeviceMoreThan5Inch(Context activity) {
        try {
            DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
            // int width = displayMetrics.widthPixels;
            // int height = displayMetrics.heightPixels;

            float yInches = displayMetrics.heightPixels / displayMetrics.ydpi;
            float xInches = displayMetrics.widthPixels / displayMetrics.xdpi;
            double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
            if (diagonalInches >= 7) {
                // 5inch device or bigger
                return true;
            } else {
                // smaller device
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static String getDataType(Context activity) {
        String type = "Mobile Data";
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        switch (tm.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                type = "Mobile Data 3G";
                Log.d("Type", "3g");
                // for 3g HSDPA networktype will be return as
                // per testing(real) in device with 3g enable
                // data
                // and speed will also matters to decide 3g network type
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                type = "Mobile Data 4G";
                Log.d("Type", "4g");
                // No specification for the 4g but from wiki
                // i found(HSPAP used in 4g)
                break;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                type = "Mobile Data GPRS";
                Log.d("Type", "GPRS");
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                type = "Mobile Data EDGE 2G";
                Log.d("Type", "EDGE 2g");
                break;

        }

        return type;
    }

    public static String getAppVersion(Context context){

        PackageInfo pInfo = null;
        String version = " ";
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

}
