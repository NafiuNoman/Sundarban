package com.ets.bfd.datasource;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ets.bfd.dao.TestDao;
import com.ets.bfd.entity.TestTable;
import com.ets.bfd.entity.User;
import com.ets.bfd.utilities.App_Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Database connection and creating database using room presistence library
 * Note : Need to remind during SQLite database table creating, every Integer or Real datatype must be
 *         Not Null otherwise Room Library will give Exception
 *
 * @link https://developer.android.com/topic/libraries/architecture/room
 * @link https://github.com/ajaysaini-sgvu/room-persistence-sample
 *
 * @author Nazmul Hasan
 */

@Database(entities = {
                        TestTable.class
}, version = App_Config.DB_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    // Create DAO in dao folder and add here
    public abstract TestDao testDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {

            copyDataBase(context);

            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, App_Config.DB_NAME)
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            // copy pre-packaged database from assets folder to android project data folder
                            //.createFromAsset(App_Config.DB_NAME)
                            .build();
        }
        return INSTANCE;
    }

    /**
     * Copy pre packaged SQLite database from app/assets folder to android app database folder.
     * @param context   Activity context
     *
     * @author Nazmul Hasan
     */
    private static void copyDataBase(Context context) {
        final File dbPath = context.getDatabasePath(App_Config.DB_NAME);
        // If the database already exists, return
        if (dbPath.exists()) {
            return;
        }
        // Make sure we have a path to the file
        dbPath.getParentFile().mkdirs();

        try {
            InputStream myInput = context.getAssets().open(App_Config.DB_NAME);
            // String outFileName = "/data/data/" + context.getPackageName() + "/databases/" + App_Config.DB_NAME;
            OutputStream myOutput = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch (IOException e) {
            throw new Error("Error copying database : " + e.getMessage());
        }


    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
