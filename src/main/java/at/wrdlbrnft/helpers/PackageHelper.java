package at.wrdlbrnft.helpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created with IntelliJ Idea 13
 * User: Xaver
 * Date: 20/06/14
 */
public class PackageHelper {

    public static boolean isPackageInstalled(Context context, String packageName) {
        try {
            return getPackageInfo(context, packageName) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static PackageInfo getPackageInfo(Context context, String packageName) throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        return pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
    }

    public static String getVersionName(Context context, String packageName) throws PackageManager.NameNotFoundException {
        PackageInfo info = getPackageInfo(context, packageName);
        return info.versionName;
    }

    public static int getVersionCode(Context context, String packageName) throws PackageManager.NameNotFoundException {
        PackageInfo info = getPackageInfo(context, packageName);
        return info.versionCode;
    }

    public static Bundle getMetaData(Context context, String packageName) throws PackageManager.NameNotFoundException {
        ApplicationInfo ai = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        return ai.metaData;
    }
}

