package com.ets.bfd.visitor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.adapters.OneDayTourTouristTypeAdapter;
import com.ets.bfd.visitor.adapters.PopupPassportAdapter;
import com.ets.bfd.visitor.adapters.SpotRecyclerAdapter;
import com.ets.bfd.visitor.adapters.VesselRecyclerAdapter;
import com.ets.bfd.visitor.dao.ExampleListModel;
import com.ets.bfd.visitor.models.CommonConfigSpinner;
import com.ets.bfd.visitor.models.one_day_tour_all_data.ChildModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.EntryTimeModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.GuardModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.OneDayTourParentModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.SpotsModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.TouristTypeModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.MainSendModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.ParentSendOneDayTourModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.SendOneDayGuardModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.SendOneDayTouristModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.SendOneDayVesselModel;
import com.ets.bfd.visitor.models.vessels_data.RegisteredVessel;
import com.ets.bfd.visitor.models.vessels_data.RootVesselsData;
import com.ets.bfd.visitor.models.vessels_data.VesseleType;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.retrofit.RetrofitApiClient;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.MyProgressDialog;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.ets.bfd.visitor.utilities.TextAwesome;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Interface.ApiInterface;
import Interface.ResponseCallback;

public class OneDayTourTicket extends AppCompatActivity implements SpotRecyclerAdapter.OnSpotItemListener {

    AutoCompleteTextView autoUnregisterVesselType, autoRegisterVesselNumberWithNumber;

    private AlertDialog modalDialog;
    String mainVisitDateForCheck = "";
    String newVisitDateForCheck = "";
    String timeSlot, guardNetFee, guardTotalFee, guardVat;
    String startingPointId = "0";
    String startingPointName = "";
    String mainStartingPointIdForCheck = "0";
    String newStartingPointIdForCheck = "0";
    String spoetId = "0";
    String spoetName = ".";
    String timeSlotName = ".";
    String pickPointName = "";
    String selectValueForEntryTime = "";
    String selectValueRegisterVesselType = "";
    private String lang = "bn";
    private String isPassportAllFieldComplete = "false";
    private String modeFor = "";
    private String modeForVessel = "";

    double allTicketGrandTotal = 0;
    public static float GrandTotal = 0;
    int guardCounter = 0;
    int itemCount = 0;
    int pickPointId = 0;
    int editSpotId = 0;
    int editPickPointId = 0;
    int editTimeSlotId = 0;
    int touristTypeCount = 0;
    int position;
    int clickedRegisteredVesselModelId = 0;

    Button vesselButton, unregisterVesselButton, registerVesselButton;

    TextInputLayout timeSlotLayout, pickPointLayout;
    TextInputLayout unRegisterVesselTypeLayout;
    TextInputLayout unRegisterVesselNameLayout;
    TextInputLayout registerVesselLayout;
    public static TextInputLayout applicantLayout, calendarLayout;
    public static TextInputEditText dateTextInput;
    TextInputEditText unRegisterVesselName;
    public static AutoCompleteTextView autoStartingPoint, autoTimeSlot;


    List<SpotsModel> spotModels = new ArrayList<>();
    List<SendOneDayTouristModel> sendTouristList;
    List<SendOneDayVesselModel> sendVesselModelList;
    List<ParentSendOneDayTourModel> parentSendOneDayTourModelList;
    List<TouristTypeModel> touristTypeList;
    List<SendOneDayTouristModel> forEditTouristModelList;
    public static List<ExampleListModel> list;


    ParentSendOneDayTourModel parentSendOneDayTourModel;
    SendOneDayGuardModel sendOneDayGuardModel;
    SendOneDayVesselModel sendOneDayVesselModel;
    OneDayTourTouristTypeAdapter oneDayTourTouristTypeAdapter;
    OneDayTourParentModel startingPointData;
    RootVesselsData rootVesselsData;
    MainSendModel mainSendModel;
    RegisteredVessel selectedRegisteredVesselModel;
    VesseleType selectedUnRegisteredVesselModel;

    HashMap<Integer, String> typeIdAndCounter;

    String[] passportNumberArray;
    String[] spotName;


    RecyclerView spotRecyclerView;
    public static RecyclerView touristTypeRecyclerView, vesselRecyclerView;


    TextView optionWaring, guardNetPrice;
    TextView numberOfGuard, itemAddedCount;
    public static TextView grandPriceTextView;

    private Context context;
    private ActionBarDrawerToggle mDrawerToggol;
    private ProgressDialog prgDialog;
    private MyPreference preferences;
    private AppService networkCall;
    private MyProgressDialog progressDialog;
    private ApiInterface apiInterface;


    CardView myCartCard;
    ImageView guardMinus, guardPlus;

    private PopupPassportAdapter popupPassportAdapter;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggol.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_day_tour_ticket);

        context = getApplicationContext();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getResources().getText(R.string.label_one_day_tour));
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, OneDayTourTicket.this, R.id.drawer_layout, R.id.idOneDayTourTicket);

        // Set app default language
        App_Config.changeLanguage(App_Config.getCurrentLanguage(this), this);
        lang = CommonUtils.getCurrentLanguage(context);

        spotName = getResources().getStringArray(R.array.Spot_Name_list);
        myCartCard = findViewById(R.id.idOneDayTourMyCart);

        guardPlus = findViewById(R.id.idOneDayGuardPlusBtn);
        guardMinus = findViewById(R.id.idOneDayGuardMinusBtn);
        guardNetPrice = findViewById(R.id.idOneDayGuardNetPrice);

        unregisterVesselButton = findViewById(R.id.idVesselRegisterBtn);
        registerVesselButton = findViewById(R.id.idVesselUnRegisterBtn);


        spotRecyclerView = findViewById(R.id.idAllSpotRecyclerView);
        touristTypeRecyclerView = findViewById(R.id.idOneDayTouristTypeRecyclerView);
        vesselRecyclerView = findViewById(R.id.idOneDayVesselRecyclerView);

        parentSendOneDayTourModelList = new ArrayList<>();


//        optionWaring = findViewById(R.id.idOptionWarning);
//        unRegisteredVesselOptionWaring = findViewById(R.id.idUnRegisterOptionWarning);


        numberOfGuard = findViewById(R.id.idNoOfGuard);
        itemAddedCount = findViewById(R.id.idOneDayItemAddedCount);
        calendarLayout = findViewById(R.id.idOneDayCalenderlayout);
        vesselButton = findViewById(R.id.idVesselButton);

        grandPriceTextView = findViewById(R.id.idGrandTotalPrice);


        preferences = MyPreference.getPreferences(this);
        networkCall = new AppService(this);

        progressDialog = new MyProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        // Set app default language
        App_Config.changeLanguage(App_Config.getCurrentLanguage(this), this);

        //Create an instance of Interface
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);


        initializationALlField();
        loadAllOneDayTourData();


        myCartCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // all passport array is here
                Log.d("they", "" + passportNumberArray);
                getCartReady();
            }
        });

    }

    private void prepareRegisterVessel() {
    }

    private void prepareUnRegisterVessel() {

        Log.d("aa ", "" + unRegisterVesselName.getText().toString());
    }

    private void getCartReady() {

        mainSendModel = new MainSendModel();

        mainSendModel.setParentSendOneDayTourModelList(parentSendOneDayTourModelList);

        mainSendModel.setVisitDate(dateTextInput.getText().toString());
        mainSendModel.setStartingPointId(startingPointId);
        mainSendModel.setPickPointName(startingPointName);

        Intent newIntent = new Intent(context, ConfirmTicket.class);

        newIntent.putExtra("sendObj", mainSendModel);

        startActivity(newIntent);

    }


    private void initializationALlField() {

        list = new ArrayList<>();
//        sendTouristList = new ArrayList<>();

        timeSlotLayout = findViewById(R.id.idOneDayTimeSlotLayout);
        pickPointLayout = findViewById(R.id.idOneDayPickPointLayout);
        dateTextInput = findViewById(R.id.idDataPicker);
        autoStartingPoint = findViewById(R.id.idOneDayTourStartingPoint);
        autoTimeSlot = findViewById(R.id.idOneDayTourTimeSlot);


        CommonUtils.addDatePickerListener(calendarLayout, OneDayTourTicket.this, dateTextInput);

        if (preferences.getExternalUserType().equalsIgnoreCase("operator")) {
            vesselButton.setVisibility(View.VISIBLE);

            sendVesselModelList = new ArrayList<>();

        }
//        setTextChangedListener(applicantName, applicantLayout);

        modeFor = getIntent().getStringExtra("modeFor");

        if (modeFor != null && modeFor.equalsIgnoreCase("editTicket")) {
            mainSendModel = (MainSendModel) getIntent().getSerializableExtra("forEdit");

            if (mainSendModel != null) {
                position = (int) getIntent().getSerializableExtra("itemPositionFromList");
                parentSendOneDayTourModelList = mainSendModel.getParentSendOneDayTourModelList();
                forEditTouristModelList = parentSendOneDayTourModelList.get(position).getSendTouristList();

                guardCounter = Integer.parseInt(parentSendOneDayTourModelList.get(position).getSendOneDayGuardModel().getGuardPerson());
                numberOfGuard.setText(String.valueOf(guardCounter));


                dateTextInput.setText(mainSendModel.getVisitDate());
                mainVisitDateForCheck = mainSendModel.getVisitDate();
//                dateTextInput.setEnabled(false);

                mainStartingPointIdForCheck = mainSendModel.getPickPointName();

                editPickPointId = Integer.parseInt(parentSendOneDayTourModelList.get(position).getPickPointId());


//                newStartingPointId= String.valueOf(editPickPointId);

                editSpotId = Integer.parseInt(parentSendOneDayTourModelList.get(position).getSpotId());
                editTimeSlotId = Integer.parseInt(parentSendOneDayTourModelList.get(position).getTimeSlotId());

            }
        } else if (modeFor != null && modeFor.equalsIgnoreCase("anotherTicket")) {
            mainSendModel = (MainSendModel) getIntent().getSerializableExtra("addMore");
            if (mainSendModel != null) {
                parentSendOneDayTourModelList = mainSendModel.getParentSendOneDayTourModelList();
                Log.d("ede", "" + parentSendOneDayTourModelList);
                dateTextInput.setText(mainSendModel.getVisitDate());

                mainVisitDateForCheck = mainSendModel.getVisitDate();
                mainStartingPointIdForCheck = mainSendModel.getPickPointName();
//                newStartingPointIdForCheck = autoStartingPoint.getText().toString();

//                dateTextInput.setEnabled(false);

                itemCount = parentSendOneDayTourModelList.size();
                itemAddedCount.setText(String.valueOf(itemCount));
            }
        }


    }


    private void loadVesselDataFromServe() {

        progressDialog.show();
        rootVesselsData = new RootVesselsData();
        networkCall.getAllVesselData(preferences.getExternalUserType(), preferences.getUserId(), new ResponseCallback<RootVesselsData>() {
            @Override
            public void onSuccess(RootVesselsData data) {
                progressDialog.hide();
                rootVesselsData = data;
                loadRegisteredVesselInAdaptor(data.getRegisteredVessels());
                loadUnRegisteredVesselInAdaptor(data.getVesseleType());
            }

            @Override
            public void onFail(Throwable th) {
                CommonUtils.showToastError(getApplicationContext(), th.getMessage());
            }
        });

    }

    private void loadUnRegisteredVesselInAdaptor(List<VesseleType> vesseleTypeList) {

        CommonConfigSpinner commonConfig = new CommonConfigSpinner(this);
        List<CommonConfigSpinner> list;
        list = commonConfig.getCommonLabelsUnRegisteredVesselData(vesseleTypeList);

        ArrayAdapter<CommonConfigSpinner> adapter = new ArrayAdapter<CommonConfigSpinner>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        autoUnregisterVesselType.setAdapter(adapter);

        autoUnregisterVesselType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemId = String.valueOf(id);

                if (itemId.equalsIgnoreCase("0")) {


//                    selectValueRegisterVesselType = "select";


                } else {

//                    timeSlot = String.valueOf(entryTimeModelList.get(position - 1).getId());
//                    timeSlotName = entryTimeModelList.get(position - 1).getTime_slot();
//                    selectValueForEntryTime = "others";

                    selectedUnRegisteredVesselModel = new VesseleType();

                    selectedUnRegisteredVesselModel = vesseleTypeList.get(position - 1);


                }


            }
        });


    }

    private void loadRegisteredVesselInAdaptor(List<RegisteredVessel> registeredVesselList) {

        CommonConfigSpinner commonConfig = new CommonConfigSpinner(this);
        List<CommonConfigSpinner> list;
        list = commonConfig.getCommonLabelsRegisteredVesselData(registeredVesselList);

        ArrayAdapter<CommonConfigSpinner> adapter = new ArrayAdapter<CommonConfigSpinner>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        autoRegisterVesselNumberWithNumber.setAdapter(adapter);

        autoRegisterVesselNumberWithNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemId = String.valueOf(id);

                if (itemId.equalsIgnoreCase("0")) {

                    selectValueRegisterVesselType = "select";


                } else {

                    // selected Model of registered vessel
                    selectedRegisteredVesselModel = new RegisteredVessel();

                    selectedRegisteredVesselModel = registeredVesselList.get(position - 1);

                    clickedRegisteredVesselModelId = registeredVesselList.get(position - 1).getVessele_id();


                }


            }
        });


    }


    private void loadEntryTimeInAdaptor(List<EntryTimeModel> entryTimeModelList,String visitDate) {

        CommonConfigSpinner commonConfig = new CommonConfigSpinner(this);
        List<CommonConfigSpinner> list;
        list = commonConfig.getCommonLabelsEntryTimeSlotData(entryTimeModelList,visitDate);

        ArrayAdapter<CommonConfigSpinner> adapter = new ArrayAdapter<CommonConfigSpinner>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        autoTimeSlot.setAdapter(adapter);
        autoTimeSlot.getText().toString().trim();

        if (editTimeSlotId != 0) {
            autoTimeSlot.setText(parentSendOneDayTourModelList.get(position).getTimeSlot(), false);
            timeSlot = String.valueOf(entryTimeModelList.get(position).getId());
            timeSlotName = entryTimeModelList.get(position).getTime_slot();
        }

        autoTimeSlot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemId = String.valueOf(id);

                if (itemId.equalsIgnoreCase("0")) {

                    selectValueForEntryTime = "select";


                } else {
                    timeSlot = String.valueOf(entryTimeModelList.get(position - 1).getId());
                    timeSlotName = entryTimeModelList.get(position - 1).getTime_slot();
                    selectValueForEntryTime = "others";

                }


            }
        });


    }


    private void loadAllOneDayTourData() {

        progressDialog.show();

        startingPointData = new OneDayTourParentModel();


        networkCall.getOneDayTourData(new ResponseCallback<OneDayTourParentModel>() {
            @Override
            public void onSuccess(OneDayTourParentModel data) {
                Log.d("super", "......inside" + data.getStartingPoints().size());
                startingPointData = data;

                GuardModel guardModel = data.getGuardFee();
                List<ChildModel> picPoint = data.getStartingPoints();

                loadStartingPointInAdaptor(picPoint);
                loadGuardData(guardModel);

                progressDialog.hide();


            }

            @Override
            public void onFail(Throwable th) {

                CommonUtils.showToastError(getApplicationContext(), th.getMessage());

                Log.d("super", "......inside" + th.getMessage());


            }
        });

    }


    private void loadStartingPointInAdaptor(List<ChildModel> childModelList) {

        CommonConfigSpinner commonConfig = new CommonConfigSpinner(this);
        List<CommonConfigSpinner> list;
        list = commonConfig.getCommonLabelsPickPointData(childModelList);

        ArrayAdapter<CommonConfigSpinner> adapter = new ArrayAdapter<CommonConfigSpinner>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        autoStartingPoint.setAdapter(adapter);
        autoStartingPoint.getText().toString().trim();

        if (modeFor != null && modeFor.equalsIgnoreCase("editTicket")) {
            if (mainSendModel != null) {
                autoStartingPoint.setText(parentSendOneDayTourModelList.get(position).getPickPointName(), false);
                int spotPosition = 0;
                int spotFinalPosition = 0;
                for (int i = 0; i < childModelList.size(); i++) {
                    ChildModel childModel = childModelList.get(i);
                    if (editPickPointId == childModel.getId()) {
                        spotFinalPosition = i;
                    }
                }

                spotPosition = spotFinalPosition;

                pickPointName = childModelList.get(spotPosition).getPointName();
                startingPointId = String.valueOf(childModelList.get(spotPosition).getId());
                startingPointName = String.valueOf(childModelList.get(spotPosition).getPointName());

                spotModels = childModelList.get(spotPosition).getSpots();
                loadAllSpotInRecyclerView(spotModels);
            }
        } else if (modeFor != null && modeFor.equalsIgnoreCase("anotherTicket")) {
            if (mainSendModel != null) {
                autoStartingPoint.setText(parentSendOneDayTourModelList.get(position).getPickPointName(), false);
                int spotPosition = 0;
                int spotFinalPosition = 0;
                for (int i = 0; i < childModelList.size(); i++) {
                    ChildModel childModel = childModelList.get(i);
                    if (editPickPointId == childModel.getId()) {
                        spotFinalPosition = i;
                    }
                }

                spotPosition = spotFinalPosition;

                pickPointName = childModelList.get(spotPosition).getPointName();
                startingPointId = String.valueOf(childModelList.get(spotPosition).getId());
                newStartingPointIdForCheck = autoStartingPoint.getText().toString();
                startingPointName = String.valueOf(childModelList.get(spotPosition).getPointName());
                mainStartingPointIdForCheck = startingPointName;


                spotModels = childModelList.get(spotPosition).getSpots();
                loadAllSpotInRecyclerView(spotModels);
            }

        }

        autoStartingPoint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemId = String.valueOf(id);

//                Log.d("pick ", "" + childModelList.get(position - 1).getId());
//                Log.d("pick name ", "" + childModelList.get(position - 1).getPointName());

//                this is to skip the crash for selecting the select item

                if (itemId.equalsIgnoreCase("0")) {
                    List<SpotsModel> spotModels = null;
                    loadAllSpotInRecyclerView(spotModels);
                } else {
                    pickPointName = childModelList.get(position - 1).getPointName();
                    startingPointId = String.valueOf(childModelList.get(position - 1).getId());
                    startingPointName = String.valueOf(childModelList.get(position - 1).getPointName());
                    mainStartingPointIdForCheck = startingPointName;
                    spotModels = childModelList.get(position - 1).getSpots();
                    loadAllSpotInRecyclerView(spotModels);
                }


            }
        });


    }


    private void loadAllSpotInRecyclerView(List<SpotsModel> spotModels) {
        if (spotModels == null) {
            spotRecyclerView.setVisibility(View.GONE);
        } else {
            spotRecyclerView.setVisibility(View.VISIBLE);
            spotRecyclerView.setAdapter(new SpotRecyclerAdapter(context, spotModels, this, editSpotId));
        }


    }


    public void guardBtnClicked(View view) {


        if (view.getId() == R.id.idOneDayGuardPlusBtn) {

            guardCounter++;
            numberOfGuard.setText(String.valueOf(guardCounter));


        } else if (view.getId() == R.id.idOneDayGuardMinusBtn) {
            if (guardCounter > 0) {

                guardCounter--;
                numberOfGuard.setText(String.valueOf(guardCounter));

            } else {
            }


        }


    }


    private void loadGuardData(GuardModel guardModel) {

        guardNetPrice.setText(guardModel.getFee());
        guardNetFee = guardModel.getFee();
        guardVat = guardModel.getVat();
        guardTotalFee = guardModel.getTotal_fee();
    }


    private boolean validationForAddToCartButton() {

        boolean isValidate = true;
        final String visitDate, timeSlot;

//        visitDate = dateTextInput.getText().toString();
        newVisitDateForCheck = dateTextInput.getText().toString();
        timeSlot = autoTimeSlot.getText().toString().trim();


        if (pickPointName.isEmpty()) {

            CommonUtils.setError(pickPointLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        } else {
            CommonUtils.setError(pickPointLayout, "");

        }
        if (timeSlot.isEmpty()) {

            CommonUtils.setError(timeSlotLayout, getString(R.string.message_field_mandatory));
            isValidate = false;


        } else if (selectValueForEntryTime.equalsIgnoreCase("select")) {
            CommonUtils.setError(timeSlotLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        } else {
            CommonUtils.setError(timeSlotLayout, "");
        }

        if (newVisitDateForCheck.isEmpty()) {

            CommonUtils.setError(calendarLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        } else {
            CommonUtils.setError(calendarLayout, "");
        }
        return isValidate;
    }

    private boolean validationForRegisteredVesselInput() {

        boolean isValidate = true;
        final String registeredVesselNumber;

        registeredVesselNumber = autoRegisterVesselNumberWithNumber.getText().toString();


        if (registeredVesselNumber.isEmpty()) {

            CommonUtils.setError(registerVesselLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        } else {
            CommonUtils.setError(registerVesselLayout, "");

        }

        return isValidate;
    }

    private boolean validationForUnRegisteredVesselInput() {

        boolean isValidate = true;
        final String unRegisteredVesselType, unRegisteredVesselName;

        unRegisteredVesselName = unRegisterVesselName.getText().toString();
        unRegisteredVesselType = autoUnregisterVesselType.getText().toString();


        if (unRegisteredVesselName.isEmpty()) {

            CommonUtils.setError(unRegisterVesselNameLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        } else {
            CommonUtils.setError(unRegisterVesselNameLayout, "");

        }
        if (unRegisteredVesselType.isEmpty()) {

            CommonUtils.setError(unRegisterVesselTypeLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        } else {
            CommonUtils.setError(unRegisterVesselTypeLayout, "");

        }

        return isValidate;
    }

    @Override
    public void onSpotClick(List<EntryTimeModel> entryTimeModelList, int spotId, String spotName, List<TouristTypeModel> touristTypeModelList) {

        touristTypeList = new ArrayList<>();
        touristTypeList = touristTypeModelList;

        loadEntryTimeInAdaptor(entryTimeModelList,dateTextInput.getText().toString());
        loadTouristTypeInRecyclerview(touristTypeModelList);

        spoetId = String.valueOf(spotId);
        spoetName = spotName;

    }

    private void loadTouristTypeInRecyclerview(List<TouristTypeModel> touristTypeModelList) {

        if (editTimeSlotId != 0) {

            typeIdAndCounter = new HashMap<>();
            touristTypeCount = touristTypeModelList.size();

            for (SendOneDayTouristModel touristModel : forEditTouristModelList) {
                typeIdAndCounter.put(Integer.parseInt(touristModel.getTouristId()), touristModel.getTotalPerson());
            }

            oneDayTourTouristTypeAdapter = new OneDayTourTouristTypeAdapter(new int[touristTypeCount], OneDayTourTicket.this, touristTypeModelList, typeIdAndCounter, "editMode");
            touristTypeRecyclerView.setAdapter(oneDayTourTouristTypeAdapter);


        } else {
            typeIdAndCounter = new HashMap<>();

            touristTypeCount = touristTypeModelList.size();
            oneDayTourTouristTypeAdapter = new OneDayTourTouristTypeAdapter(new int[touristTypeCount], OneDayTourTicket.this, touristTypeModelList, typeIdAndCounter, "normalMode");
            touristTypeRecyclerView.setAdapter(oneDayTourTouristTypeAdapter);
        }


    }


    public void addToCartButtonClicked(View view) {

        if (validationForAddToCartButton()) {

            if (modeFor != null && modeFor.equalsIgnoreCase("editTicket")) {
                parentSendOneDayTourModelList.remove(position);

            }

            if (OneDayTourTouristTypeAdapter.allPassportCount > 0 && isPassportAllFieldComplete.equalsIgnoreCase("false")) {
//                CommonUtils.showToastWarning(context,""+OneDayTourTouristTypeAdapter.allPassportCount+" "+getResources().getString(R.string.passport_warning));
//                passportNumberArray = new String[OneDayTourTouristTypeAdapter.allPassportCount];
                showPassportAlertBox(OneDayTourTouristTypeAdapter.allPassportCount);


                checkForSameDateOrSpot();

                sendVesselModelList = new ArrayList<>();
                VesselRecyclerAdapter vesselRecyclerAdapter = new VesselRecyclerAdapter(context, sendVesselModelList);
                vesselRecyclerView.setAdapter(vesselRecyclerAdapter);
//                passportNumberArray = new String[0];


            } else {

                if (parentSendOneDayTourModelList.size() > 0) {
                    for (ParentSendOneDayTourModel model : parentSendOneDayTourModelList) {
                        //if time slot id and spot id same then  its going to remove the previous ticket and add the latest one.


                        if (Integer.parseInt(model.getTimeSlotId()) == Integer.parseInt(timeSlot) && Integer.parseInt(model.getSpotId()) == Integer.parseInt(spoetId)) {
                            double ticketFee = Double.parseDouble(model.getTotalSingleTicketFee());
//                            sendTouristList.remove(model.getSendTouristList());
                            parentSendOneDayTourModelList.remove(model);
                            Toast.makeText(context, "remove done", Toast.LENGTH_SHORT).show();
                            itemCount--;
//                          itemAddedCount.setText(String.valueOf(itemCount));


                        }
                    }
                }

                checkForSameDateOrSpot();

                sendVesselModelList = new ArrayList<>();
                VesselRecyclerAdapter vesselRecyclerAdapter = new VesselRecyclerAdapter(context, sendVesselModelList);
                vesselRecyclerView.setAdapter(vesselRecyclerAdapter);


            }


        } else {
            CommonUtils.showToastWarning(context, getResources().getString(R.string.final_warning));
        }


    }


    private void prepareOneDayTicketList() {


        sendTouristList = new ArrayList<>();
        parentSendOneDayTourModel = new ParentSendOneDayTourModel();
        itemCount++;
        itemAddedCount.setText(String.valueOf(itemCount));
        double allTouristTotal = 0;
        double totalSingleTicketFee = 0;
        Set<Integer> set = typeIdAndCounter.keySet();
        Iterator<Integer> i = set.iterator();

        parentSendOneDayTourModel.setPickPointId(startingPointId);
        parentSendOneDayTourModel.setPickPointName(pickPointName);
        parentSendOneDayTourModel.setSpotId(spoetId);
        parentSendOneDayTourModel.setSpotName(spoetName);
        parentSendOneDayTourModel.setVisitDate(dateTextInput.getText().toString());
        parentSendOneDayTourModel.setTimeSlotId(timeSlot);
        parentSendOneDayTourModel.setTimeSlot(timeSlotName);

        parentSendOneDayTourModel.setIs_tourist("0");
        parentSendOneDayTourModel.setIs_guard("0");
        parentSendOneDayTourModel.setIs_vessel("0");

        while (i.hasNext()) {
            //res== id//typeIdAndCounter.get(res)==counter
            Integer res = i.next();

            for (TouristTypeModel list : touristTypeList) {
                if (list.getId() == res) {
                    SendOneDayTouristModel sendTouristModel = new SendOneDayTouristModel();

                    double netPrice = Double.parseDouble(list.getNetPrice());
                    int person = Integer.valueOf(typeIdAndCounter.get(res));
                    double vat = (netPrice * Double.parseDouble(list.getVat())) / 100;
                    double totalVat = vat * person;
                    double totalRevenue = netPrice * person;
                    double totalAmount = totalRevenue + totalVat;

                    sendTouristModel.setTouristId(String.valueOf(res));
                    sendTouristModel.setTouristName(list.getType());
                    sendTouristModel.setNetPrice(String.valueOf(netPrice));
                    sendTouristModel.setVatPrice(String.valueOf(totalVat));
                    sendTouristModel.setTotalPerson(String.valueOf(person));
                    sendTouristModel.setRevenue(String.valueOf(totalRevenue));
                    sendTouristModel.setTotal(String.valueOf(totalAmount));

                    allTouristTotal = allTouristTotal + totalAmount;

                    sendTouristList.add(sendTouristModel);

                }


            }


        }


        int guardCount = Integer.valueOf(numberOfGuard.getText().toString());
        double guardTotal = Double.parseDouble(guardNetFee) * guardCount;

        sendOneDayGuardModel = new SendOneDayGuardModel();
        sendOneDayGuardModel.setGuardPerson(numberOfGuard.getText().toString());
        sendOneDayGuardModel.setNetFee(guardNetFee);
        sendOneDayGuardModel.setVat(guardVat);
        sendOneDayGuardModel.setTotalFee(String.valueOf(guardTotal));

        parentSendOneDayTourModel.setSendTouristList(sendTouristList);
        parentSendOneDayTourModel.setSendOneDayGuardModel(sendOneDayGuardModel);
        parentSendOneDayTourModel.setSendVesselList(sendVesselModelList);



        if (sendTouristList.size() > 0) {
            parentSendOneDayTourModel.setIs_tourist("1");
        }

        if (guardCount > 0) {
            parentSendOneDayTourModel.setIs_guard("1");
        }
        if(OneDayTourTouristTypeAdapter.allPassportCount != 0)
        {
            parentSendOneDayTourModel.setPassportArray(passportNumberArray);
        }

        if (sendVesselModelList != null && sendVesselModelList.size() > 0) {
            parentSendOneDayTourModel.setIs_vessel("1");
        }

        totalSingleTicketFee = allTouristTotal + guardTotal;
        parentSendOneDayTourModel.setTotalSingleTicketFee(String.valueOf(totalSingleTicketFee));

        parentSendOneDayTourModelList.add(parentSendOneDayTourModel);
        allTicketGrandTotal = allTicketGrandTotal + totalSingleTicketFee;
        grandPriceTextView.setText(String.valueOf(allTicketGrandTotal));

        allTicketGrandTotal = 0;
//        dateTextInput.setEnabled(false);

    }


//    public static void setTextChangedListener(EditText editText, final TextInputLayout layout) {
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.length() != 0) {
//                    layout.setError(null);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//    }


    public void showPassportAlertBox(int totalPassportNumber) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OneDayTourTicket.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.one_day_passport_layout, null);
        alertDialog.setView(convertView);

        TextAwesome btnClosePopUpWindow;
        RecyclerView idPassportLayoutRecycler;
        Button submitBtn, cancelBtn;

        btnClosePopUpWindow = (TextAwesome) convertView.findViewById(R.id.btnClosePopUpWindow);
        idPassportLayoutRecycler = (RecyclerView) convertView.findViewById(R.id.idPassportLayoutRecycler);
        submitBtn = convertView.findViewById(R.id.idPopupPassportSaveButton);
        cancelBtn = convertView.findViewById(R.id.idPopupPassportCancelButton);

        passportNumberArray = new String[totalPassportNumber];


        popupPassportAdapter = new PopupPassportAdapter(context, totalPassportNumber, lang);
        idPassportLayoutRecycler.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getApplicationContext());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        idPassportLayoutRecycler.setAdapter(popupPassportAdapter);
        idPassportLayoutRecycler.setLayoutManager(MyLayoutManager);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (int i = 0; i < totalPassportNumber; i++) {
                    View view = idPassportLayoutRecycler.getChildAt(i);
                    TextInputEditText passportTextView = view.findViewById(R.id.idPassportRowInput);
                    passportNumberArray[i] = passportTextView.getText().toString();
                }

                modalDialog.dismiss();


            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modalDialog.dismiss();
            }
        });

//        isPassportAllFieldComplete = "true";


        modalDialog = alertDialog.create();
        btnClosePopUpWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modalDialog.dismiss();
            }
        });


        modalDialog.show();


    }


    private void checkForSameDateOrSpot() {

//        newStartingPointIdForCheck = autoStartingPoint.getText().toString();


        if (parentSendOneDayTourModelList.size() == 0) {

            prepareOneDayTicketModel();


            mainVisitDateForCheck = newVisitDateForCheck;
            mainStartingPointIdForCheck = newStartingPointIdForCheck;


        } else if (parentSendOneDayTourModelList.size() > 0) {

            if (newVisitDateForCheck.equalsIgnoreCase(mainVisitDateForCheck) && newStartingPointIdForCheck.equalsIgnoreCase(mainStartingPointIdForCheck)) {
                prepareOneDayTicketModel();

            } else {
                showDialogForDeletePreviousTicket();

            }
        }
    }


    private void showDialogForDeletePreviousTicket() {
        Dialog dialog = new Dialog(OneDayTourTicket.this);
        dialog.setContentView(R.layout.previous_delete_ticket_dialoge_box);

        Button sureBtn = dialog.findViewById(R.id.idAlertBoxSureBtn);
        Button cancelBtn = dialog.findViewById(R.id.idAlertBoxCancelBtn);

        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemCount = 0;
                parentSendOneDayTourModelList = new ArrayList<>();
                prepareOneDayTicketModel();
                mainVisitDateForCheck = newVisitDateForCheck;
                mainStartingPointIdForCheck = newStartingPointIdForCheck;

                dialog.dismiss();

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    private void showPopUpForVesselEntry() {
        Button saveBtn, cancelBtn, registeredVesselBtn, unRegisteredVesselBtn;
        LinearLayout unRegisterVesselLayout;

        Dialog dialog = new Dialog(OneDayTourTicket.this);
        dialog.setContentView(R.layout.pop_up_vessel_layout);

        registeredVesselBtn = dialog.findViewById(R.id.idVesselRegisterBtn);
        unRegisteredVesselBtn = dialog.findViewById(R.id.idVesselUnRegisterBtn);
        saveBtn = dialog.findViewById(R.id.idPopupVesselSaveButton);
        cancelBtn = dialog.findViewById(R.id.idPopupVesselCancelButton);
        registerVesselLayout = dialog.findViewById(R.id.idRegisteredBtnChildLayout);
        unRegisterVesselLayout = dialog.findViewById(R.id.idUnregisterVesselLayout);
        unRegisterVesselTypeLayout = dialog.findViewById(R.id.idUnRegisteredVesselTypeLayout);
        unRegisterVesselNameLayout = dialog.findViewById(R.id.idUnRegisteredVesselNameLayout);
        unRegisterVesselName = dialog.findViewById(R.id.idUnRegisteredVesselName);

        autoUnregisterVesselType = dialog.findViewById(R.id.idUnRegisteredVesselType);
        autoRegisterVesselNumberWithNumber = dialog.findViewById(R.id.idRegisteredVesselNameWithNumber);

        if (preferences.getExternalUserType().equalsIgnoreCase("operator")) {
            loadVesselDataFromServe();
        }

        registeredVesselBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerVesselLayout.setVisibility(View.VISIBLE);
                unRegisterVesselLayout.setVisibility(View.GONE);
                unRegisteredVesselBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                registeredVesselBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.btnActivated));

                autoUnregisterVesselType.getText().clear();
                unRegisterVesselName.getText().clear();


                modeForVessel = "registeredVessel";


            }
        });

        unRegisteredVesselBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                unRegisteredVesselBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.btnActivated));
                registeredVesselBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.white));

                registerVesselLayout.setVisibility(View.GONE);
                unRegisterVesselLayout.setVisibility(View.VISIBLE);
                autoRegisterVesselNumberWithNumber.getText().clear();

                modeForVessel = "unRegisteredVessel";


            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (modeForVessel != null && modeForVessel.equalsIgnoreCase("registeredVessel")) {
                    if (validationForRegisteredVesselInput()) {
                        populateRegisteredVesselData(selectedRegisteredVesselModel, "1");

                        VesselRecyclerAdapter vesselRecyclerAdapter = new VesselRecyclerAdapter(context, sendVesselModelList);
                        vesselRecyclerView.setAdapter(vesselRecyclerAdapter);

                        dialog.dismiss();

                    }


                } else if (modeForVessel != null && modeForVessel.equalsIgnoreCase("unRegisteredVessel")) {

                    if (validationForUnRegisteredVesselInput()) {
                        String unRegisteredVesselName = autoRegisterVesselNumberWithNumber.getText().toString();

                        populateUnRegisteredVesselData(selectedUnRegisteredVesselModel, "0", unRegisteredVesselName);

                        VesselRecyclerAdapter vesselRecyclerAdapter = new VesselRecyclerAdapter(context, sendVesselModelList);
                        vesselRecyclerView.setAdapter(vesselRecyclerAdapter);

                        dialog.dismiss();


                    }


                } else if (modeForVessel != null && modeForVessel.equalsIgnoreCase("")) {
                    Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();

                }

//                modeForVessel = "";
//                dialog.dismiss();


            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


    }

    private void populateRegisteredVesselData(RegisteredVessel model, String Is_bfd_registered) {

        sendOneDayVesselModel = new SendOneDayVesselModel();

        sendOneDayVesselModel.setIs_bfd_registered(Is_bfd_registered);
        sendOneDayVesselModel.setVessel_id(String.valueOf(model.getVessele_id()));
        sendOneDayVesselModel.setVessel_category_id(String.valueOf(model.getVessel_category_id()));
        sendOneDayVesselModel.setVessel_size_id(String.valueOf(model.getVessel_size_id()));
        sendOneDayVesselModel.setVesselName(model.getVessel_name_en());
        sendOneDayVesselModel.setEntry_fee(model.getVessel_entry_fee());
        sendOneDayVesselModel.setTotal_fee(model.getTotal_entry_fee());
        sendOneDayVesselModel.setTotal_vat(model.getTotal_vat_fee());
        sendOneDayVesselModel.setVessel_size_id(String.valueOf(model.getVessel_size_id()));

        sendVesselModelList.add(sendOneDayVesselModel);


    }

    private void populateUnRegisteredVesselData(VesseleType model, String Is_bfd_registered, String name) {

        sendOneDayVesselModel = new SendOneDayVesselModel();

        sendOneDayVesselModel.setIs_bfd_registered(Is_bfd_registered);
        sendOneDayVesselModel.setVessel_id(null);
        sendOneDayVesselModel.setVessel_category_id(String.valueOf(model.getVessel_category_id()));
        sendOneDayVesselModel.setVessel_size_id(String.valueOf(model.getVessel_size_id()));
        sendOneDayVesselModel.setVesselName(name);
        sendOneDayVesselModel.setEntry_fee(model.getVessel_entry_fee());
        sendOneDayVesselModel.setTotal_fee(model.getTotal_entry_fee());
        sendOneDayVesselModel.setTotal_vat(model.getTotal_vat_fee());
        sendOneDayVesselModel.setVessel_size_id(String.valueOf(model.getVessel_size_id()));

        sendVesselModelList.add(sendOneDayVesselModel);


    }

    private void prepareOneDayTicketModel() {

        prepareOneDayTicketList();
        loadAllSpotInRecyclerView(spotModels);

        loadTouristTypeInRecyclerview(new ArrayList<>());
//        typeIdAndCounter = new HashMap<>();
        typeIdAndCounter = null;
        OneDayTourTouristTypeAdapter.allPassportCount = 0;
        autoTimeSlot.getText().clear();
        guardCounter = 0;
        numberOfGuard.setText(String.valueOf(guardCounter));

    }


    public void vesselsBtnClicked(View view) {
        showPopUpForVesselEntry();

        modeForVessel = "";
    }
}