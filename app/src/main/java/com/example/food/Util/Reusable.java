package com.example.food.Util;

import android.app.ProgressDialog;
import android.content.Context;

public class Reusable {
    static ProgressDialog dialog;

    public static void showWaitingDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Please waiting ...!");
        dialog.show();
    }

    public static void dismissDialog() {
        dialog.dismiss();
    }
}
