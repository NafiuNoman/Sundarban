package com.ets.bfd.visitor.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.room.Ignore;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.datasource.DatabaseHelper;
import com.ets.bfd.visitor.models.one_day_tour_all_data.ChildModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.EntryTimeModel;
import com.ets.bfd.visitor.models.spots_and_routes.RouteModel;
import com.ets.bfd.visitor.models.spots_and_routes.OldSpotModel;
import com.ets.bfd.visitor.models.touristfee.TouristDetails;
import com.ets.bfd.visitor.models.vessels_data.RegisteredVessel;
import com.ets.bfd.visitor.models.vessels_data.VesseleType;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommonConfigSpinner {
    protected DatabaseHelper dbHelper;
    protected SQLiteDatabase db;
    private transient Context context;
    private MyPreference preferences;
    @Ignore
    private String lang = Locale.getDefault().getDisplayLanguage();

    public final static String COLUMN_ID = "id";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_NAME_BN = "name_bn";
    public transient final static String COLUMN_IS_ACTIVE = "is_active";

    private String tableName;
    private String id;
    private String name;
    private String name_bn;
    private int is_active;

    public CommonConfigSpinner() {

    }

    public CommonConfigSpinner(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
        this.context = context;
        //share preferences
        preferences = MyPreference.getPreferences(context);
        lang = CommonUtils.getCurrentLanguage(context);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_bn() {
        return name_bn;
    }

    public void setName_bn(String name_bn) {
        this.name_bn = name_bn;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }




    public List<CommonConfigSpinner> getCommonLabelsPickPointData(List<ChildModel> childModels){

        List<CommonConfigSpinner> list = new ArrayList<CommonConfigSpinner>();
        try{
            CommonConfigSpinner commonConfig = new CommonConfigSpinner();
            commonConfig.setId("0");
            commonConfig.setName(context.getString(R.string.label_select));
            list.add(commonConfig);

            for (ChildModel childModel : childModels){
                commonConfig = new CommonConfigSpinner();
                commonConfig.setId(String.valueOf(childModel.getId()));

                if(lang.equalsIgnoreCase("en")){
                    commonConfig.setName( childModel.getPointName() );
                }else{
                    commonConfig.setName( childModel.getPointNameBn() );
                }
                list.add(commonConfig);
            }

        } catch(Exception e){
            Toast.makeText(context, "Operation Failed", Toast.LENGTH_SHORT).show();
        }
        return list;
    }

    /**************/

    public List<CommonConfigSpinner> getCommonLabelsEntryTimeSlotData(List<EntryTimeModel> entryTimeModelList,String visitDate){

        List<CommonConfigSpinner> list = new ArrayList<CommonConfigSpinner>();
        try{
            CommonConfigSpinner commonConfig = new CommonConfigSpinner();
            commonConfig.setId("0");
            commonConfig.setName(context.getString(R.string.label_select));
            list.add(commonConfig);

            for (EntryTimeModel timeSlot : entryTimeModelList){

                if(timeSlot.getStart_date().equalsIgnoreCase(visitDate))
                {
                    commonConfig = new CommonConfigSpinner();
                    commonConfig.setId(String.valueOf(timeSlot.getId()));

                    if(lang.equalsIgnoreCase("en")){
                        commonConfig.setName( timeSlot.getTime_slot() );
                    }else{
                        commonConfig.setName( timeSlot.getTime_slot() );
                    }
                    list.add(commonConfig);

                }


            }

        } catch(Exception e){
            Toast.makeText(context, "Operation Failed", Toast.LENGTH_SHORT).show();
        }
        return list;
    }


    public List<CommonConfigSpinner> getCommonLabelsUnRegisteredVesselData(List<VesseleType> vesseleTypeList){

        List<CommonConfigSpinner> list = new ArrayList<CommonConfigSpinner>();
        try{
            CommonConfigSpinner commonConfig = new CommonConfigSpinner();
            commonConfig.setId("0");
            commonConfig.setName(context.getString(R.string.label_select));
            list.add(commonConfig);

            for (VesseleType model : vesseleTypeList){
                commonConfig = new CommonConfigSpinner();
                commonConfig.setId(String.valueOf(model.getVessel_category_id()));

                if(lang.equalsIgnoreCase("en")){
                    commonConfig.setName( model.getVessel_type_with_entry_fee_en() );
                }else{
                    commonConfig.setName( model.getVessel_type_with_entry_fee_bn() );
                }
                list.add(commonConfig);
            }

        } catch(Exception e){
            Toast.makeText(context, "Operation Failed", Toast.LENGTH_SHORT).show();
        }
        return list;
    }
    public List<CommonConfigSpinner> getCommonLabelsRegisteredVesselData(List<RegisteredVessel> registeredVesselList){

        List<CommonConfigSpinner> list = new ArrayList<CommonConfigSpinner>();
        try{
            CommonConfigSpinner commonConfig = new CommonConfigSpinner();
            commonConfig.setId("0");
            commonConfig.setName(context.getString(R.string.label_select));
            list.add(commonConfig);

            for (RegisteredVessel model : registeredVesselList){
                commonConfig = new CommonConfigSpinner();
                commonConfig.setId(String.valueOf(model.getVessele_id()));

                if(lang.equalsIgnoreCase("en")){
                    commonConfig.setName( model.getVessel_name_en() );
                }else{
                    commonConfig.setName( model.getVessel_name_bn() );
                }
                list.add(commonConfig);
            }

        } catch(Exception e){
            Toast.makeText(context, "Operation Failed", Toast.LENGTH_SHORT).show();
        }
        return list;
    }






    @Override
    public String toString() {
        return  name;
    }


}
