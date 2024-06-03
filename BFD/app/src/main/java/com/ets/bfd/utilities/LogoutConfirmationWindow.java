package com.ets.bfd.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.ets.bfd.MainActivity;
import com.ets.bfd.R;
import com.ets.bfd.preference.MyPreference;

public class LogoutConfirmationWindow {

    private Context context;
    private Activity activity;
    private MaterialButton btnCancel, btnLogout;
    private MyPreference preferences;

    public LogoutConfirmationWindow(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        //share preferences
        preferences = MyPreference.getPreferences(context);
    }

    public void showDialog(){
        final AlertDialog alertDialog;
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.logout_confirmation, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();

        btnCancel = dialoglayout.findViewById(R.id.btnCancel);
        btnLogout = dialoglayout.findViewById(R.id.btnLogout);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.clearPreference();
                context.startActivity(new Intent(context, MainActivity.class));
                activity.finishAffinity();
                activity.finish();
            }
        });
    }

}
