package com.ets.bfd.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.ets.bfd.utilities.App_Config;

public class MyPreference {
    private static MyPreference myPreferences;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private MyPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(App_Config.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void clearPreference(){
        sharedPreferences.edit().clear().commit();
    }

    public static MyPreference getPreferences(Context context) {
        if (myPreferences == null) myPreferences = new MyPreference(context);
        return myPreferences;
    }

    // Set app locale
    public void setLanguage(String language){
        editor.putString("language", language);
        editor.apply();
    }

    // User Name
    public String getLanguagee(){
        //if no data is available for language then this getString() method returns
        //a default value that is mentioned in second parameter
        return sharedPreferences.getString("language", "");
    }

    public void setLongitude(String longitude){
        editor.putString("longitude", longitude);
        editor.apply();
    }

    public String getLongitude(){
        return sharedPreferences.getString("longitude", "");
    }

    public void setLatitude(String latitude){
        editor.putString("latitude", latitude);
        editor.apply();
    }

    public String getLatitude(){
        return sharedPreferences.getString("latitude", "");
    }


    public void setAddressByLatLong(String address){
        editor.putString("address", address);
        editor.apply();
    }

    public String getAddressByLatLong(){
        return sharedPreferences.getString("address", "");
    }


    public void setToken(String token){
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken(){
        return sharedPreferences.getString("token", "");
    }

    // Set Remember Me flag for login check
    public void setRememberMeFlag(String flag){
        editor.putString("isRemember", flag);
        editor.apply();
    }

    // Get Remember Me flag of login check
    public String getRememberMeFlag(){
        return sharedPreferences.getString("isRemember", "");
    }

    // Primary key id of users table in remote server

    public void setUserId(String id){
        editor.putString("id", id);
        editor.apply();
    }

    public String getUserId(){
        return sharedPreferences.getString("id", "");
    }

    public void setLoginId(String loginId){
        editor.putString("loginId", loginId);
        editor.apply();
    }

    public String getLoginId(){
        return sharedPreferences.getString("loginId", "");
    }



    public void setuserNameEn(String userNameEn){
        editor.putString("user_name_en", userNameEn);
        editor.apply();
    }

    public String getuserNameEn(){
        return sharedPreferences.getString("user_name_en", "");
    }

    public void setuserNameBn(String userNameBn){
        editor.putString("user_name_bn", userNameBn);
        editor.apply();
    }
    public String getuserNameBn(){
        return sharedPreferences.getString("user_name_bn", "");
    }

    public void setuserEmail(String user_email){
        editor.putString("user_email", user_email);
        editor.apply();
    }
    public String getuserEmail(){
        return sharedPreferences.getString("user_email", "");
    }

    public void setuserBloodGroup(String user_blood_group){
        editor.putString("user_blood_group", user_blood_group);
        editor.apply();
    }
    public String getuserBloodGroup(){
        return sharedPreferences.getString("user_blood_group", "");
    }

    public void setDesignationId(String designation_id){
        editor.putString("designation_id", designation_id);
        editor.apply();
    }
    public String getDesignationId(){
        return sharedPreferences.getString("designation_id", "");
    }

    public void setDivisionId(String division_id){
        editor.putString("division_id", division_id);
        editor.apply();
    }
    public String getDivisionId(){
        return sharedPreferences.getString("division_id", "");
    }

    public void setDistrictId(String district_id){
        editor.putString("district_id", district_id);
        editor.apply();
    }
    public String getDistrictId(){
        return sharedPreferences.getString("district_id", "");
    }

    public void setUpazilaId(String upazila_id){
        editor.putString("upazila_id", upazila_id);
        editor.apply();
    }
    public String getUpazilaId(){
        return sharedPreferences.getString("upazila_id", "");
    }

    public void setUnionId(String union_id){
        editor.putString("union_id", union_id);
        editor.apply();
    }
    public String getUnionId(){
        return sharedPreferences.getString("union_id", "");
    }

    public void setUserLevel(String user_level){
        editor.putString("user_level", user_level);
        editor.apply();
    }
    public String getUserLevel(){
        return sharedPreferences.getString("user_level", "");
    }

    public void setUserImage(String user_image){
        editor.putString("user_image", user_image);
        editor.apply();
    }
    public String getUserImage(){
        return sharedPreferences.getString("user_image", "");
    }

    public void setMobileNumber(String MobileNumber){
        editor.putString("MobileNumber", MobileNumber);
        editor.apply();
    }
    public String getMobileNumber(){
        return sharedPreferences.getString("MobileNumber", "");
    }

    public void setOfficeId(String officeId){
        editor.putString("officeId", officeId);
        editor.apply();
    }
    public String getOfficeId(){
        return sharedPreferences.getString("officeId", "");
    }

    public void setOfficeNameEn(String officeNameEn){
        editor.putString("officeNameEn", officeNameEn);
        editor.apply();
    }
    public String getOfficeNameEn(){
        return sharedPreferences.getString("officeNameEn", "");
    }

    public void setOfficeNameBn(String officeNameBn){
        editor.putString("officeNameBn", officeNameBn);
        editor.apply();
    }
    public String getOfficeNameBn(){
        return sharedPreferences.getString("officeNameBn", "");
    }

    public void setSpotId(String spotId){
        editor.putString("spotId", spotId);
        editor.apply();
    }
    public String getSpotId(){
        return sharedPreferences.getString("spotId", "");
    }

    public void setSpotNameEn(String spotNameEn){
        editor.putString("spotNameEn", spotNameEn);
        editor.apply();
    }
    public String getSpotNameEn(){
        return sharedPreferences.getString("spotNameEn", "");
    }

    public void setSpotNameBn(String spotNameBn){
        editor.putString("spotNameBn", spotNameBn);
        editor.apply();
    }
    public String getSpotNameBn(){
        return sharedPreferences.getString("spotNameBn", "");
    }

}
