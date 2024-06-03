package com.ets.bfd.visitor.utilities;

import static com.ets.bfd.visitor.activity.OneDayTourTicket.applicantLayout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.datasource.AppDatabase;
import com.ets.bfd.visitor.preference.MyPreference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.widget.AppCompatEditText;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CommonUtils {

    /**
     * Get app default language from SharedPreference.
     * If language not found from preference then get from Android app settings
     *
     * @param context
     * @return language
     * @author Nazmul Hasan
     */
    public static String getCurrentLanguage(Context context){
        MyPreference preference = MyPreference.getPreferences(context);
        String currentLanguage = preference.getLanguagee();
        if(currentLanguage.equalsIgnoreCase("")){
            /*Locale myLocale = context.getResources().getConfiguration().locale;
            currentLanguage = myLocale.getLanguage();*/
            currentLanguage = "bn";
        }

        return currentLanguage;

    }

    /**
     * Translate Number EN TO BN
     * @param number
     * @return
     */
    public static String translateNumberEnToBn(String number){

        String convertedNumber = "";

        if( number != null ){
            convertedNumber = number.replaceAll("0","০")
                    .replaceAll("1","১")
                    .replaceAll("2","২")
                    .replaceAll("3","৩")
                    .replaceAll("4","৪")
                    .replaceAll("5","৫")
                    .replaceAll("6","৬")
                    .replaceAll("7","৭")
                    .replaceAll("8","৮")
                    .replaceAll("9","৯");
        }

        return convertedNumber;
    }

    /**
     * Translate Number EN TO BN
     * @param number
     * @return
     */
    public static String translateNumberBnToEn(String number){

        String convertedNumber = "";

        if( number != null ){
            convertedNumber = number.replaceAll("0","০")
                    .replaceAll("১", "1")
                    .replaceAll("২", "2")
                    .replaceAll("৩", "3")
                    .replaceAll("৪", "4")
                    .replaceAll("৫", "5")
                    .replaceAll("৬", "6")
                    .replaceAll("৭", "7")
                    .replaceAll("৮", "8")
                    .replaceAll("৯", "9");
        }

        return convertedNumber;
    }

    public static String translateNumber(String number, String lang){
        if(lang.equalsIgnoreCase("bn")){
            return translateNumberEnToBn(number);
        }
        return number;
    }


    /**
     * Translate Date from English TO Bangla
     * @param date
     * @return  Translated date
     * @author Nazmul Hasan
     */
    public static String translateDateEnToBn(String date, String lang){


        if( date != null && lang.equalsIgnoreCase("bn") ){

            return date.replaceAll("January","জানুয়ারী")
                    .replaceAll("February","ফেব্রুয়ারী")
                    .replaceAll("March","মার্চ")
                    .replaceAll("April","এপ্রিল")
                    .replaceAll("May","মে")
                    .replaceAll("June","জুন")
                    .replaceAll("July","জুলাই")
                    .replaceAll("August","আগস্ট")
                    .replaceAll("September","সেপ্টেম্বর")
                    .replaceAll("October","অক্টোবর")
                    .replaceAll("November","নভেম্বর")
                    .replaceAll("December","ডিসেম্বর")
                    .replaceAll("0","০")
                    .replaceAll("1","১")
                    .replaceAll("2","২")
                    .replaceAll("3","৩")
                    .replaceAll("4","৪")
                    .replaceAll("5","৫")
                    .replaceAll("6","৬")
                    .replaceAll("7","৭")
                    .replaceAll("8","৮")
                    .replaceAll("9","৯");

        }

        return date;
    }


    /**
     * Translate Date EN TO BN
     * @param date
     * @return string
     * @author Arif Hossain
     */
    public static String translate_date_EnToBn(String date,String lang){

        String formatedDate = "";

        if( date != null ){

            String[] items1 = date.split("-");
            String year=items1[0];
            String month=items1[1];
            String dayAndTime=items1[2];

            String[] items2 = dayAndTime.split(" ");
            String day=items2[0];
            String time = "";
            if(items2.length > 1){
                time = items2[1];
            }


            if( time.contains (".") ) {
                String[] items3 = time.split("\\.");
                time =  items3[0] + ":" + items3[1];
            }

            String AmPm = "";
            if(items2.length > 2){
                AmPm=items2[2];
            }

            String dateForDay =  year + "-" + month + "-" + day;
            String MonthBn;
            if(lang.equalsIgnoreCase("bn")){
                MonthBn =  month.replaceAll("01", lang.equalsIgnoreCase("bn") ? "জানুয়ারী" : "January")
                        .replaceAll("02", lang.equalsIgnoreCase("bn") ? "ফেব্রুয়ারী" : "February")
                        .replaceAll("03", lang.equalsIgnoreCase("bn") ? "মার্চ" : "March")
                        .replaceAll("04", lang.equalsIgnoreCase("bn") ? "এপ্রিল" : "April")
                        .replaceAll("05", lang.equalsIgnoreCase("bn") ? "মে" : "May")
                        .replaceAll("06", lang.equalsIgnoreCase("bn") ? "জুন" : "June")
                        .replaceAll("07", lang.equalsIgnoreCase("bn") ? "জুলাই" : "July")
                        .replaceAll("08", lang.equalsIgnoreCase("bn") ? "আগস্ট" : "August")
                        .replaceAll("09", lang.equalsIgnoreCase("bn") ? "সেপ্টেম্বর" : "September")
                        .replaceAll("10", lang.equalsIgnoreCase("bn") ? "অক্টোবর" : "October")
                        .replaceAll("11", lang.equalsIgnoreCase("bn") ? "নভেম্বর" : "November")
                        .replaceAll("12", lang.equalsIgnoreCase("bn") ? "ডিসেম্বর" : "December");
            }else{
                MonthBn =  month.replaceAll("০১", lang.equalsIgnoreCase("bn") ? "জানুয়ারী" : "January")
                        .replaceAll("০২", lang.equalsIgnoreCase("bn") ? "ফেব্রুয়ারী" : "February")
                        .replaceAll("০৩", lang.equalsIgnoreCase("bn") ? "মার্চ" : "March")
                        .replaceAll("০৪", lang.equalsIgnoreCase("bn") ? "এপ্রিল" : "April")
                        .replaceAll("০৫", lang.equalsIgnoreCase("bn") ? "মে" : "May")
                        .replaceAll("০৬", lang.equalsIgnoreCase("bn") ? "জুন" : "June")
                        .replaceAll("০৭", lang.equalsIgnoreCase("bn") ? "জুলাই" : "July")
                        .replaceAll("০৮", lang.equalsIgnoreCase("bn") ? "আগস্ট" : "August")
                        .replaceAll("০৯", lang.equalsIgnoreCase("bn") ? "সেপ্টেম্বর" : "September")
                        .replaceAll("১০", lang.equalsIgnoreCase("bn") ? "অক্টোবর" : "October")
                        .replaceAll("১১", lang.equalsIgnoreCase("bn") ? "নভেম্বর" : "November")
                        .replaceAll("১২", lang.equalsIgnoreCase("bn") ? "ডিসেম্বর" : "December");
            }




            formatedDate = translateNumber(day,lang) + " " + MonthBn + " " + translateNumber(year,lang) + "; " + translateNumber(time,lang) + " " + AmPm ;

        }


        return formatedDate;
    }


    public static String returnDayNameByDate(String dateTime) {

        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;
        String dayName = null;

        try {
            date = inputFormat.parse(dateTime);
            if(!date.equals(null)){

                str = outputFormat.format(date);
                Calendar mydate = new GregorianCalendar();
                mydate.setTime(date);

                dayName =  new SimpleDateFormat("EEEE").format(date);

//                String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
//                dayName = days[mydate.get(Calendar.DAY_OF_WEEK)];

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayName;
    }

    /**
     * Translate Date EN TO BN
     * @param /date
     * @return string
     * @author Arif Hossain
     */
    public static String translateYesNoByOneORZero(String yesNo,String lang){

        String resultYesNo = "";

        if( yesNo != null ){

            resultYesNo =  yesNo.replaceAll("1", lang.equalsIgnoreCase("bn") ? "হ্যাঁ" : "Yes")
                    .replaceAll("0", lang.equalsIgnoreCase("bn") ? "না" : "No");
        }
        return resultYesNo;
    }


    /**
     * Language Switcher method.
     *
     * @param lang	"bn" for bangla and "en" for English switcher
     * @param context	application context
     * @author Nazmul Hasan
     */
    public static void changeLanguage(String lang, Context context){
        // Create a new Locale object
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        // Create a new configuration object
        Configuration config = new Configuration();
        // Set the locale of the new configuration
        config.locale = locale;
        // Update the configuration of the Accplication context
        context.getResources().updateConfiguration(
                config,
                context.getResources().getDisplayMetrics()
        );
        // Store language in app preference also
        MyPreference preference = MyPreference.getPreferences(context);
        preference.setLanguage(lang);
    }

    /**
     *get current date time with for Asia/Dhaka zone
     *
     * @param datePattern   yyyy-MM-dd hh:mm:ss a
     * @return  Current date
     *
     * @author Nazmul
     */
    public static String getCurrentDateTime(String datePattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Dhaka"));
        Date date = new Date();

        return dateFormat.format(date);
    }

    public static void showToastError(Context context, String message){
        showToast(context, message, "error");
    }

    public static void showToastSuccess(Context context, String message){

        showToast(context, message, "success");
    }

    public static void showToastWarning(Context context, String message){

        showToast(context, message, "warning");
    }

    public static void showToastInfo(Context context, String message){
        showToast(context, message, "info");
    }

    /**
     * Show different type(error/success/warning/info) of toast message.
     * Use custom toast library
     *
     * @param context
     * @param message
     * @param toastType
     * @url https://github.com/GrenderG/Toasty
     * @author Nazmul Hasan
     */
    public static void showToast(Context context, String message, String toastType){
        setupToastConfig();
        Toast toast = null;
        switch (toastType){
            case "error" :
                toast = Toasty.error(context, message, Toast.LENGTH_LONG);
                break;
            case "success" :
                toast = Toasty.success(context, message, Toast.LENGTH_LONG);
                break;
            case "warning" :
                toast = Toasty.warning(context, message, Toast.LENGTH_LONG);
                break;
            case "info" :
                toast = Toasty.info(context, message, Toast.LENGTH_LONG);
                break;
        }
        if(toast != null){
            toast.setGravity(Gravity.CENTER| Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    // Toasty toast library configuration
    public static void setupToastConfig(){
        Toasty.Config.getInstance()
                .allowQueue(true) // optional (prevents several Toastys from queuing)
                .apply(); // required
    }


    /**
     * Processing font awesome icon to show in menu item in action bar and navigation drawer
     *
     * @param context   Activity context
     * @param menuItem  Menu id for setting icon
     * @param icon      Font Awesome Icon from fontawesome string file
     * @param color     color
     * @param fontFile  Font Awesome font file
     *
     * @author Nazmul Hasan
     */
    public static void setFontAwesomeIcon(Context context, MenuItem menuItem, int icon, int color, String fontFile){
        TextDrawable faIcon = new TextDrawable(context);
        faIcon.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        faIcon.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        faIcon.setTypeface(FontCache.get(context, "fonts/"+fontFile));
        if(color != 0){
            faIcon.setTextColor(context.getResources().getColor(color));
        }
        faIcon.setText(context.getResources().getText(icon));
        menuItem.setIcon(faIcon);
    }

    /**
     * Processing font awesome icon to show in menu item in action bar and navigation drawer
     *
     * @param context   Activity context
     * @param menuItem  Menu id for setting icon
     * @param icon      Font Awesome Icon from fontawesome string file
     *
     * @author Nazmul Hasan
     */
    public static void setFontAwesomeIcon(Context context, MenuItem menuItem, int icon){
        setFontAwesomeIcon(context, menuItem, icon, 0, "fa-solid-900.ttf");
    }

    /**
     * Processing font awesome icon to show in menu item in action bar and navigation drawer
     *
     * @param context   Activity context
     * @param menuItem  Menu id for setting icon
     * @param icon      Font Awesome Icon from fontawesome string file
     * @param color     color
     *
     * @author Nazmul Hasan
     */
    public static void setFontAwesomeIcon(Context context, MenuItem menuItem, int icon, int color){
        setFontAwesomeIcon(context, menuItem, icon, color, "fa-solid-900.ttf");
    }

    /**
     * Return drawable icon from fontawesome icon string resource id
     * @param context
     * @param icon
     * @param color
     * @param fontFile
     * @return  drawable icon from fontawesome icon string resource id
     *
     * @author Nazmul Hasan
     */
    public static TextDrawable generateDrawableIconFromFontAwesomeIcon(Context context, int icon, int color, String fontFile ){
        TextDrawable faIcon = new TextDrawable(context);
        faIcon.setTextSize(TypedValue.COMPLEX_UNIT_PT, 5);
        faIcon.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        faIcon.setTypeface(FontCache.get(context, "fonts/fa-solid-900.ttf"));
        if(color != 0){
            faIcon.setTextColor(context.getResources().getColor(color));
        }
        faIcon.setText(context.getResources().getText(icon));
        return faIcon;
    }

    public static TextDrawable generateDrawableIconFromFontAwesomeIcon(Context context, int icon, int color){

        return generateDrawableIconFromFontAwesomeIcon(context, icon, color, "fa-solid-900.ttf" );
    }

    /**
     * Expand a view with slow motion animation
     * @param v View layout that need to expanded
     * @param duration  expand duration
     * @param targetHeight
     *
     * @author Nazmul Hasan
     */
    public static void expand(final View v, int duration, int targetHeight) {

        int prevHeight  = v.getHeight();

        v.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    /**
     * Collapse a view with slow motion animation
     * @param v View layout that need to collapse
     * @param duration  collapse duration
     * @param targetHeight
     *
     * @author Nazmul Hasan
     */
    public static void collapse(final View v, int duration, int targetHeight) {
        int prevHeight  = v.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    /**
     * Rotate an icon during collapse or expand a view
     * @param target
     * @param from  degree(180)
     * @param to
     * @return
     *
     * @author Nazmul Hasan
     */
    public static ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }

    // Check if device has active network connection
    public static boolean isNetworkOnline(Context context) {
        boolean status = false;
        ///  ("Connection Type", `0`-`3`): `0` - cellular/mobile network, `1` - wifi / ethernet, `2` - inne;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null
                    && netInfo.getState() == NetworkInfo.State.CONNECTED ) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }

    /**
     * Get image mime type
     * @param uri   Image uri
     * @param context   Application context
     * @return  mime type as string
     * @author Nazmul Hasan
     */
    public static String getMimeType(Uri uri, Context context) {
        String mimeType = null;
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }

    /**
     *  make image as multipart data to uploading image in remote server
     * @param partName
     * @param fileUri
     * @param context
     * @return
     *
     * @author Nazmul Hasan
     */
    @NonNull
    public static MultipartBody.Part prepareFilePart(String partName, String fileUri, Context context) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = new File(fileUri);
        //compress the image using Compressor lib
        //Timber.d("size of image before compression --> " + file.getTotalSpace());
        //compressedImageFile = new Compressor(this).compressToFile(file);
        //Timber.d("size of image after compression --> " + compressedImageFile.getTotalSpace());
        // create RequestBody instance from file
        String mimeType = getMimeType(Uri.parse(fileUri), context);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(mimeType), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    public static String convertImageToBase64(String imagePath){
        if(imagePath == null || imagePath.equalsIgnoreCase("")){
            return "";
        }
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm is the bitmap object
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    // This method  converts String to RequestBody
    public static RequestBody toRequestBody (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }

    public static double toDouble(String value){
        if(value.equalsIgnoreCase("")){
            return 0;
        }
        return Double.valueOf(value);
    }

    /**
     * Show date picker dialogue.
     * @param activity
     * @param editText  date placed in this edit text
     *
     * @author Nazmul Hasan
     */
    public static void showPicker(Activity activity, final AppCompatEditText editText, final TextInputLayout layout){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String pickerDay = String.valueOf(dayOfMonth);
                String pickerMonth = String.valueOf(monthOfYear);
                if(dayOfMonth<10){
                    pickerDay = "0" + String.valueOf(dayOfMonth);
                }
                if(monthOfYear<10){
                    pickerMonth = "0" + String.valueOf(monthOfYear + 1);
                }else{
                    pickerMonth = String.valueOf(monthOfYear + 1);
                }
                String pickDate = pickerDay + "-" + pickerMonth + "-" + year;
                editText.setText(pickDate);
                if(layout != null){
                    setError(layout, "");
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public static void showPicker(Activity activity, final AppCompatEditText editText){
        showPicker(activity, editText, null);
    }

    /**
     * Adding date picker end icon click listener and touch listener
     * @param layout    Material text input layout
     * @param activity  Activity context
     * @param editText  Material edit text
     * @author Nazmul Hasan
     */
    public static void addDatePickerListener(final TextInputLayout layout, final Activity activity, final AppCompatEditText editText){
        layout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showPicker();
                showPicker(activity, editText, layout);
            }
        });

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(MotionEvent.ACTION_UP == event.getAction()) {
                    showPicker(activity, editText, layout);
                }
                return true;
            }
        });
    }


    public static String getCompleteAddressString(Context context,double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {

                if(addresses.get(0).getSubThoroughfare() != null){
                    strAdd += addresses.get(0).getSubThoroughfare() + ", ";
                }

                if(addresses.get(0).getThoroughfare() != null){
                    strAdd += addresses.get(0).getThoroughfare() + ", ";
                }

                if(addresses.get(0).getSubLocality() != null){
                    strAdd += addresses.get(0).getSubLocality() + ", ";
                }

                if(addresses.get(0).getLocality() != null && addresses.get(0).getPostalCode() != null){
                    strAdd += addresses.get(0).getLocality() + "-" + addresses.get(0).getPostalCode() + ", ";
                }

                if(addresses.get(0).getLocality() != null && addresses.get(0).getPostalCode() == null){
                    strAdd += addresses.get(0).getLocality() + ", ";
                }

                if(addresses.get(0).getAdminArea() != null){
                    strAdd += addresses.get(0).getAdminArea() + ", ";
                }

                if(addresses.get(0).getCountryName() != null){
                    strAdd += addresses.get(0).getCountryName() + ".";
                }

//                Toast.makeText(context, "My Current loction address: " + strAdd.toString(), Toast.LENGTH_SHORT).show();

            } else {
                strAdd += "No Address Found!";
                Toast.makeText(context, "My Current loction address: " + "No Address returned!", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "My Current loction address: " + "Canont get Address!", Toast.LENGTH_SHORT).show();
        }

        return strAdd;
    }

    /**
     * Show or hide error message or error box in text input layout
     * @param layout
     * @param message  If message found then error will be enabled otherwise error will be hide.
     *
     * @author Nazmul Hasan
     */
    public static void setError(TextInputLayout layout, String message){

        layout.setErrorIconDrawable(null);

        layout.setError(message);
    }







    /**
     * Add text changed listener
     * @param editText  Adding listener in this editText
     * @param layout    Hide error message and error controll box  in this layout
     * @author Nazmul Hasan
     */
    public static void setTextChangedListener(TextInputEditText editText, final TextInputLayout layout){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0){
                    layout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     *
     * @param inputDate The date that need to format
     * @param inputFormat   input date format
     * @param outputFormat  Output format
     * @return  Formatted date time
     *
     * @author Nazmul Hasan
     */
    public static String formatDateTime(String inputDate, String inputFormat, String outputFormat){
        if(inputDate == null || inputDate.equalsIgnoreCase("")){
            return "";
        }
        DateFormat outputFormatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        DateFormat inputFormatDate;
        if(inputFormat == null){
            inputFormatDate = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);

        } else {
            inputFormatDate = new SimpleDateFormat(inputFormat, Locale.US);
        }
        if(outputFormat == null){
            outputFormatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        } else {
            outputFormatDate = new SimpleDateFormat(outputFormat, Locale.US);
        }
        Date date = null;
        try {
            date = inputFormatDate.parse(inputDate);
            return outputFormatDate.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * Get date time from Image
     * @param imageUri
     * @return dateTime
     *
     * @author Nazmul Hasan
     */
    public static String getDateTimeFromImage(String imageUri){
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imageUri);
            String dateTime = exif.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL);
            if(dateTime != null && !dateTime.equalsIgnoreCase("")){
                return formatDateTime(dateTime, null, null);
            }

            dateTime = exif.getAttribute(ExifInterface.TAG_DATETIME);
            if(dateTime != null && !dateTime.equalsIgnoreCase("")){
                return formatDateTime(dateTime, null, null);
            }

            dateTime = exif.getAttribute(ExifInterface.TAG_DATETIME_DIGITIZED);
            if(dateTime != null && !dateTime.equalsIgnoreCase("")){
                return formatDateTime(dateTime, null, null);
            }
            return "";
        } catch (Exception e){
            return "";
        }
    }
    /**
     * Get GPS Latitude and Longitude from Images
     * @param imageUri
     * @return  latLong array list of double type
     *
     * @link https://stackoverflow.com/questions/15403797/how-to-get-the-latitude-and-longitude-of-an-image-in-sdcard-to-my-application
     *
     * @author Nazmul Hasan
     */
    public static ArrayList<Double> getLongLatFromImage(String imageUri){
        ExifInterface exif = null;
        ArrayList<Double> latLong = new ArrayList<Double>();
        try {
            exif = new ExifInterface(imageUri);
            String LATITUDE = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String LATITUDE_REF = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String LONGITUDE = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            String LONGITUDE_REF = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

            Double Latitude = null, Longitude = null;

            if((LATITUDE !=null)
                    && (LATITUDE_REF !=null)
                    && (LONGITUDE != null)
                    && (LONGITUDE_REF !=null))
            {

                if(LATITUDE_REF.equals("N")){
                    Latitude = convertToDegree(LATITUDE);
                }
                else{
                    Latitude = 0 - convertToDegree(LATITUDE);
                }

                if(LONGITUDE_REF.equals("E")){
                    Longitude = convertToDegree(LONGITUDE);
                }
                else{
                    Longitude = 0 - convertToDegree(LONGITUDE);
                }

            }
           if(LATITUDE != null && !LATITUDE.equalsIgnoreCase("")){
                latLong.add(Latitude);
                latLong.add(Longitude);
            }
            return latLong;
        } catch (IOException e) {
            return latLong;
        }

    }

    private static Double convertToDegree(String stringDMS){
        Double result = null;
        String[] DMS = stringDMS.split(",", 3);

        String[] stringD = DMS[0].split("/", 2);
        Double D0 = new Double(stringD[0]);
        Double D1 = new Double(stringD[1]);
        Double FloatD = D0/D1;

        String[] stringM = DMS[1].split("/", 2);
        Double M0 = new Double(stringM[0]);
        Double M1 = new Double(stringM[1]);
        Double FloatM = M0/M1;

        String[] stringS = DMS[2].split("/", 2);
        Double S0 = new Double(stringS[0]);
        Double S1 = new Double(stringS[1]);
        Double FloatS = S0/S1;

        result = new Double(FloatD + (FloatM/60) + (FloatS/3600));

        return result;


    }


    /**
     * Get App Version Name
     *
     * @param context	application context
     * @return boolean
     */

    public static String getAppVersionName(Context context){
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        String myVersionName = "";
        try {
            myVersionName = packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(!myVersionName.equalsIgnoreCase("")){
            myVersionName = "(v-" + myVersionName + ")" ;
        }
        return myVersionName;
    }


    //////////new Code






}
