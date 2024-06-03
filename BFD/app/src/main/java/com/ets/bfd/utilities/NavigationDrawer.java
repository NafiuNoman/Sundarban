package com.ets.bfd.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.ets.bfd.LoginActivity;
import com.ets.bfd.MainActivity;
import com.ets.bfd.activity.DashboardActivity;
import com.ets.bfd.activity.QRCodeScannerActivity;
import com.ets.bfd.activity.RegistrationAndRenewApplication;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.ets.bfd.R;
import com.ets.bfd.preference.MyPreference;

public class NavigationDrawer {
    public static DrawerLayout drawerLayout;

    /**
     * Adding navigation drawer menu item
     *
     * @param context	application context
     * @param activity	layout activity
     * @param drawerLayoutId	Navigation drawer layout id
     * @param selectedMenuItemId	Current activity Menu item id for selecting
     * @return  ActionBarDrawerToggle
     * @author Nazmul Hasan
     */
    public static ActionBarDrawerToggle addNavigationDrawer(final Context context, final Activity activity, int drawerLayoutId, final int selectedMenuItemId){

        final ActionBarDrawerToggle t;
        NavigationView nv;
        TextView txtAppNameAndVersion;
        final MyPreference preferences;
        final String currentLanguage = App_Config.getCurrentLanguage(context);

        //share preferences
        preferences = MyPreference.getPreferences(context);

        activity.setTitle(context.getResources().getText(R.string.app_name));
//        ((AppCompatActivity) context).getSupportActionBar().setSubtitle(context.getResources().getText(R.string.app_name));


        drawerLayout = (DrawerLayout)activity.findViewById(drawerLayoutId);
        t = new ActionBarDrawerToggle(activity, drawerLayout,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(t);
        t.syncState();

        nv = (NavigationView)activity.findViewById(R.id.nv);
        txtAppNameAndVersion = (TextView) activity.findViewById(R.id.txtAppNameAndVersion);

        /**
         * Load nav_header layout
         * Set User Name and Mobile Number
         */
        View headerView = nv.getHeaderView(0);
        TextView navUserName = (TextView) headerView.findViewById(R.id.navUserName);
        TextView navUserMobileNumber = (TextView) headerView.findViewById(R.id.navUserMobileNumber);

        String lang = CommonUtils.getCurrentLanguage(context);

        if(!preferences.getUserId().isEmpty()){
            navUserMobileNumber.setText(CommonUtils.translateNumber(preferences.getMobileNumber(), lang));
            navUserName.setText(preferences.getuserNameEn());
            if(lang.equalsIgnoreCase("bn")){
                navUserName.setText(preferences.getuserNameBn());
            }
        }

        txtAppNameAndVersion.setText( context.getString(R.string.app_name) + " " + CommonUtils.getAppVersionName(context));

        //end nav_header


        NavigationMenuView navMenuView = (NavigationMenuView) nv.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        final Menu nav_Menu = nv.getMenu();
        MenuItem menuLanguage = nav_Menu.findItem(R.id.languageSwitcher);

        // Setting Fontawesome icon in navigation menu
        setFontAwesomeIcon(context, menuLanguage, R.string.fa_globe);

        setFontAwesomeIcon(context, nav_Menu.findItem(R.id.btnLogin), R.string.fa_lock);
        setFontAwesomeIcon(context, nav_Menu.findItem(R.id.btnLogout), R.string.fa_sign_out_alt);

//        setFontAwesomeIcon(context, nav_Menu.findItem(R.id.changePassword), R.string.fa_key);


        if(currentLanguage.equals("en")){
            menuLanguage.setTitle(activity.getString(R.string.switchToBangla));
        } else {
            menuLanguage.setTitle(activity.getString(R.string.switchToEnglish));
        }


        // If rememberMeFlag found 1 in preference the redirect to dashboard

        MenuItem menulogIn = nav_Menu.findItem(R.id.btnLogin);
        MenuItem menuLogout = nav_Menu.findItem(R.id.btnLogout);
        MenuItem checkInCheckOut = nav_Menu.findItem(R.id.checkInCheckOut);

        String rememberMeFlag = preferences.getRememberMeFlag();

        if(!preferences.getUserId().isEmpty()){
            menulogIn.setVisible(false);
            menuLogout.setVisible(true);
            checkInCheckOut.setVisible(true);
        }else{
            menulogIn.setVisible(true);
            menuLogout.setVisible(false);
            checkInCheckOut.setVisible(false);
        }


        nav_Menu.findItem(selectedMenuItemId).setChecked(true);  // Checked current Activity menu
        // handle menu item click event
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.languageSwitcher:
                        // Language Switcher. First Set Default Language as Bangla
                        if (currentLanguage.equals("en")) {
                            App_Config.changeLanguage("bn", context);
                        } else {
                            App_Config.changeLanguage("en", context);
                        }
                        context.startActivity(activity.getIntent());
                        break;

                    case R.id.dashboard:
                        context.startActivity(new Intent(context, DashboardActivity.class));
                        activity.finish();
                        break;

                    case R.id.btnLogin:
                        context.startActivity(new Intent(context, LoginActivity.class));
                        activity.finish();
                        break;

                    case R.id.btnLogout:
                        new LogoutConfirmationWindow(context, activity).showDialog();
                        nav_Menu.findItem(R.id.btnLogout).setChecked(false);  // Checked current Activity menu
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.checkInCheckOut:
                        context.startActivity(new Intent(context, QRCodeScannerActivity.class));
                        activity.finish();
                        break;

                    case R.id.btn_regAndRenew:
                        context.startActivity(new Intent(context, RegistrationAndRenewApplication.class));
                        activity.finish();
                        break;

                  /*  case R.id.changePassword:
                        context.startActivity(new Intent(context, ChangePasswordActivity.class));
                        activity.finish();
                        break;*/
                }
                return true;

            }
        });


        return t;
    }

    public static void setFontAwesomeIcon(Context context, MenuItem menuItem, int icon){
        CommonUtils.setFontAwesomeIcon(context, menuItem, icon);
    }
}
