package com.ets.bfd.visitor.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.ets.bfd.visitor.utilities.App_Config;

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


    public void setOperatorId(String operator_id){
        editor.putString("operator_id", operator_id);
        editor.apply();
    }

    public String getOperatorId(){
        return sharedPreferences.getString("operator_id", "");
    }


    public void setExternalUserType(String external_user_type){
        editor.putString("external_user_type", external_user_type);
        editor.apply();
    }

    public String getExternalUserType(){
        return sharedPreferences.getString("external_user_type", "");
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


    public void setTotalReview(String total_review){
        editor.putString("total_review", total_review);
        editor.apply();
    }

    public String getTotalReview(){
        return sharedPreferences.getString("total_review", "");
    }

    public void setTotalTrips(String total_trips){
        editor.putString("total_trips", total_trips);
        editor.apply();
    }

    public String getTotalTrips(){
        return sharedPreferences.getString("total_trips", "");
    }


    public void setTotalPackage(String total_package){
        editor.putString("total_package", total_package);
        editor.apply();
    }

    public String getTotalPackage(){
        return sharedPreferences.getString("total_package", "");
    }

    public void setTotalRating(String total_rating){
        editor.putString("total_rating", total_rating);
        editor.apply();
    }

    public String getTotalRating(){
        return sharedPreferences.getString("total_rating", "");
    }

    public void setTotalTourist(String total_tourist){
        editor.putString("total_tourist", total_tourist);
        editor.apply();
    }

    public String getTotalTourist(){
        return sharedPreferences.getString("total_tourist", "");
    }


    public void setUserDesignationEn(String user_designation_en){
        editor.putString("user_designation_en", user_designation_en);
        editor.apply();
    }

    public String getUserDesignationEn(){
        return sharedPreferences.getString("user_designation_en", "");
    }

    public void setUserDesignationBn(String user_designation_bn){
        editor.putString("user_designation_bn", user_designation_bn);
        editor.apply();
    }

    public String getUserDesignationBn(){
        return sharedPreferences.getString("user_designation_bn", "");
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


}
