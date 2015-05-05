package com.sanshisoft.fragmentbase.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by chenleicpp on 2015/5/5.
 */
public class CommenUtils {

    public static String TAG = "FragmentBase";

    public static String ACTION_REPORT = "action_report";
    public static String ACTION_DISPLAY = "action_display";

    public static void exit(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("warning!")
                .setMessage("make sure to exit")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppManager.getAppManager().AppExit(context);
                    }
                });
        builder.create().show();
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0 || cs.equals("null")) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !CommenUtils.isBlank(cs);
    }
}
