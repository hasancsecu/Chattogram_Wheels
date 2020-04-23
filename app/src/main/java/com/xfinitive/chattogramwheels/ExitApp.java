package com.xfinitive.chattogramwheels;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

public class ExitApp {

    public static void showExitDialog(final Context context, int themeId) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, themeId);
        showExitDialog(context, alertDialog);
    }

    private static void showExitDialog(final Context context, AlertDialog.Builder alertDialog) {
        alertDialog.setTitle(R.string.exitTitle);
        alertDialog.setMessage(R.string.exitMsg);
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.ic_sign_out_24dp);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                ActivityCompat.finishAffinity((Activity) context);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}
