package com.xfinitive.chattogramwheels;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

public class RateThisApp {

    public static void showRateDialog(final Context context, int themeId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, themeId);
        showRateDialog(context, builder);
    }

    private static void showRateDialog(final Context context, AlertDialog.Builder builder) {
        int titleId = R.string.rta_dialog_title;
        int messageId = R.string.rta_dialog_message;
        int laterButtonID = R.string.rta_dialog_cancel;
        int rateButtonID = R.string.rta_dialog_ok;
        builder.setTitle(titleId);
        builder.setIcon(R.drawable.ic_star_black_24dp);
        builder.setMessage(messageId);
        builder.setCancelable(false);
        builder.setPositiveButton(rateButtonID, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String appPackage = context.getPackageName();
                String url = "market://details?id=" + appPackage;

                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackage)));
                }
            }
        });

        builder.setNegativeButton(laterButtonID, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
