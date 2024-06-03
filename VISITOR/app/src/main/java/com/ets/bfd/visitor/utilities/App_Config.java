package com.ets.bfd.visitor.utilities;

import android.content.Context;

public class App_Config {

    public static final String BASE_URL = "http://192.168.50.85:9217/api/android/";
    public static final String BASE_URL_FOR_ONLY_IMAGE = "http://192.168.50.85:9217/";

//    public static final String BASE_URL = "http://pc81:9214/api/android/";
//    public static final String BASE_URL_FOR_ONLY_IMAGE = "http://pc81:9214/";

    //Main Server
//    public static final String BASE_URL = "http://114.130.119.157/api/android/";
//    public static final String BASE_URL_FOR_ONLY_IMAGE = "http://114.130.119.157/";


    public static final String SHARED_PREFERENCES_NAME = "com.ets.bfd.visitor.my_shared_preferences";
    public static final String DB_NAME = "ets_bfd.db";
    public static final int DB_VERSION = 2;
    public static final int SCHEME_ALLOCATION_BBG_PBG_ID = 116;
    public static final String SCHEME_STATUS_ID_IN_PROCESS = "1";
    public static final String SCHEME_STATUS_ID_RUNNING = "2";
    public static final String SCHEME_STATUS_ID_DONE = "3";
    public static final String AFS_HEAD_TYPE_INCOME_ID = "1";
    public static final String AFS_HEAD_TYPE_EXPENSE_ID = "2";
    public static final String AFS_HEAD_TYPE_MISC_ID = "3";
    public static final String AFS_HEAD_TYPECLOSING_ID = "4";

    /**
     * Language Switcher method.
     *
     * @param lang    "bn" for bangla and "en" for English switcher
     * @param context application context
     * @author Nazmul Hasan
     */

    public static void changeLanguage(String lang, Context context) {
        CommonUtils.changeLanguage(lang, context);
    }

    /**
     * Get app default language from SharedPreference.
     * If language not found from preference then get from Android app settings
     *
     * @param context
     * @return language
     * @author Nazmul Hasan
     */
    public static String getCurrentLanguage(Context context) {
        return CommonUtils.getCurrentLanguage(context);

    }

}
