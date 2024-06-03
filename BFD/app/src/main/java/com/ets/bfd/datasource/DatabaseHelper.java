package com.ets.bfd.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ets.bfd.utilities.App_Config;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = App_Config.DB_NAME;
    private static int DB_VERSION = App_Config.DB_VERSION;
    private SQLiteDatabase db;
    private final Context context;
    private String DB_PATH;
    private static DatabaseHelper databaseHelper;

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }

    public static DatabaseHelper getInstance(Context context) {

        if (databaseHelper == null) {
            synchronized (DatabaseHelper.class){ //thread safe singleton
                if (databaseHelper == null)
                    databaseHelper = new DatabaseHelper(context);
            }
        }

        return databaseHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
