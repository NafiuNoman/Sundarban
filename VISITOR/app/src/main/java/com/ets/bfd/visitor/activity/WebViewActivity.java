package com.ets.bfd.visitor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.models.MyTicketListModel;
import com.ets.bfd.visitor.models.ResponseModel;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.retrofit.RetrofitApiClient;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.MyProgressDialog;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.ets.bfd.visitor.utilities.PdfWebViewClient;
import com.ets.bfd.visitor.utilities.UserPermission;

import java.util.Timer;
import java.util.TimerTask;

import Interface.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WebViewActivity extends AppCompatActivity {
    private String modeFor = "";
    private String scopeId = "0";
    private String ekpaySecureToken = "https://www.google.com/";
    private String oneDayTicketPdfURL = "";
    WebView webView;

    private String lang = "bn";
    private Context context;
    private Activity activity;
    private MyPreference preferences;
    private AppService networkCall;
    private MyProgressDialog progressDialog;
    private ApiInterface apiInterface ;
    private Timer t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getResources().getText(R.string.app_full_name));

        context = getApplicationContext();
        lang = CommonUtils.getCurrentLanguage( context );
        //share preferences
        preferences = MyPreference.getPreferences(this);
        // Initialize service model
        networkCall = new AppService(this);
        //Create an instance of Interface
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        // Instantiating progress bar
        progressDialog = new MyProgressDialog(this);

        t =new Timer();

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //for edit mode
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            modeFor = extras.getString("mode_for");
            scopeId =  extras.getString("scope_id");
            if(modeFor.equalsIgnoreCase("ekpay_one_day_ticket")){
                ekpaySecureToken =  extras.getString("ekpay_secure_token");
                webView.loadUrl(ekpaySecureToken);
            }else if(modeFor.equalsIgnoreCase("one_day_ticket_pdf")){
                oneDayTicketPdfURL = App_Config.BASE_URL_FOR_ONLY_IMAGE +lang+"/frontend/tour-ticket/"+ scopeId+"/pdf";

                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);
                webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

                PdfWebViewClient pdfWebViewClient = new PdfWebViewClient(this, webView);
                pdfWebViewClient.loadPdfUrl("http://docs.google.com/gview?embedded=true&url="+oneDayTicketPdfURL);

            }

        }

        if(modeFor.equalsIgnoreCase("ekpay_one_day_ticket")) {
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            checkTicketIsSuccessfullyPurchase();
                        }
                    });
                }
            }, 1000, 3000);
        }

    }


    public void checkTicketIsSuccessfullyPurchase(){
        Call<ResponseModel> call = apiInterface.getOneDayTicketSuccessDataByOneDayId(scopeId);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel ticketList = response.body();
                if(response.code()== 200 && ticketList.getCode().equalsIgnoreCase("200")){
                    getMyTicketFromServerByOneDayTourId();
                } else {    // If response code 404
//                    CommonUtils.showToastError(context,"No data found!");
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                CommonUtils.showToastError(context,"Server response not found for UP Member Data..");
            }
        });

    }


    public void getMyTicketFromServerByOneDayTourId(){
        progressDialog.setMessage("Please Wait Ticket Searching...");
        progressDialog.show();

        Call<ResponseModel> call = apiInterface.getOneDayTicketLisById(scopeId);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel ticketList = response.body();
                if(response.code()== 200 && ticketList!=null){

                    if(ticketList.getTicketLists().size() > 0){
                        t.cancel();

                        Intent newIntent = new Intent(context, DynamicTabsActivity.class);
                        newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        newIntent.putExtra("one_day_tour_id",scopeId);
                        newIntent.putExtra("oneDayTourInfo",ticketList.getTicketLists().get(0));
                        context.startActivity(newIntent);
                        finish();
                        progressDialog.hide();
                    }

                } else {    // If response code 404
                    CommonUtils.showToastError(context,"No data found!");
                    progressDialog.hide();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressDialog.hide();
                CommonUtils.showToastError(context,"Server response not found for UP Member Data..");
//              CustomToast.showToastError(context,t.getMessage());
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(WebViewActivity.this, OneDayTourTicket.class));
        super.onBackPressed();

    }


}