package com.ets.bfd.visitor;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ets.bfd.visitor.activity.MainActivity;
import com.ets.bfd.visitor.datasource.AppDatabase;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.utilities.CommonUtils;

public class SplashActivity extends AppCompatActivity {
    private MyPreference preference;
    private Context context;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preference = MyPreference.getPreferences(this);
        CommonUtils.changeLanguage(CommonUtils.getCurrentLanguage(this), this);
        context = getApplicationContext();
        db = AppDatabase.getAppDatabase(this);

        startActivity(new Intent(this, MainActivity.class));
        finish();

    }


}