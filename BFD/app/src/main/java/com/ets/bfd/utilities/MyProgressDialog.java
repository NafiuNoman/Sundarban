package com.ets.bfd.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.ets.bfd.R;

/**
 * Custom progress dialog.
 * Showing Prograssbar in alert dialog.
 * Android built in progressDialog class is deprecated
 * For this reason I built this class
 * After Instantiating this class, call show method to show progress bar
 * Call hide method to hide progress bar.
 *
 * @author Nazmul Hasan
 */
public class MyProgressDialog {
    private AlertDialog.Builder alert;
    private LinearLayout layout;
    private Context context;
    private String message;
    private TextView textView;
    private AlertDialog alertDialog;

    public MyProgressDialog(Context context) {
        this.context = context;

    }

    private void makeLayout(){
        alert = new AlertDialog.Builder(context);

        // Creating Layout
        layout = new LinearLayout(context);
        //LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(10,10,10,10);
        layout.setMinimumHeight(130);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        //Creating progress bar widget
        final ProgressBar progressBar = new ProgressBar(context);
        progressBar.setPadding(30,0,0,0);
        // Setting progressbar circular progress color
        progressBar.getIndeterminateDrawable()
                   .setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN );

        layout.addView(progressBar);

        // Creating TextView widget
        textView = new TextView(context);
        // Setting left padding
        textView.setPadding(30,0,0,0);
        // Setting default loading message
        textView.setText("Please Wait...");
        textView.setTextColor(context.getResources().getColor(R.color.white));
        layout.addView(textView);

        alert.setView(layout);
        alert.setCancelable(false);

    }

    public void show(){

        makeLayout();
        if(getMessage() != null && !getMessage().equalsIgnoreCase("")){
            textView.setText(getMessage());
        }
        alertDialog = alert.create();
        alertDialog.show();  // to show
    }
    public void hide(){
        alertDialog.dismiss();  // to dismiss
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
