package com.ets.bfd;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ets.bfd.datasource.AppDatabase;
import com.ets.bfd.preference.MyPreference;
import com.ets.bfd.utilities.CommonUtils;

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

        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }


}