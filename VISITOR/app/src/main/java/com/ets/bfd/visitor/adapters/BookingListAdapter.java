package com.ets.bfd.visitor.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.activity.PackageDetailsActivity;
import com.ets.bfd.visitor.models.BookingPackageListModel;
import com.ets.bfd.visitor.models.PackagesModel;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.TextAwesome;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.PackageViewHolder> {
    private BookingPackageListModel pacakgesObjectModel;
    private ArrayList<BookingPackageListModel> list;
    private List<BookingPackageListModel> pacakgesListFiltered;
    private Activity activity;
    private Context context;
    private AlertDialog alert;
    Dialog alertDialog;
    private String lang = "bn";

    public BookingListAdapter(ArrayList<BookingPackageListModel> Data, BookingPackageListModel objectData, Context context) {
        this.list = Data;
        this.pacakgesListFiltered = Data;
        this.pacakgesObjectModel = objectData;
        this.context = context;
        alertDialog = new Dialog(context);
        lang = CommonUtils.getCurrentLanguage( context );

    }

    @NonNull
    @Override
    public BookingListAdapter.PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_package_list_item, parent, false);
        return new BookingListAdapter.PackageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingListAdapter.PackageViewHolder holder, int position) {

        int serialNo = position+1 ;
        String dataSerialNo = String.valueOf(serialNo);
        holder.data_serial_no.setText( CommonUtils.translateNumber(dataSerialNo,lang) );

        String visitorName = pacakgesListFiltered.get(position).getBooking_by_name_en();
        if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
            if(pacakgesListFiltered.get(position).getBooking_by_name_bn() != null){
                visitorName = pacakgesListFiltered.get(position).getBooking_by_name_bn();
            }
        }

        String numberOfAdult = pacakgesListFiltered.get(position).getNumber_of_adult() + " " + context.getString(R.string.persons);
        String numberOfChild = pacakgesListFiltered.get(position).getNumber_of_children() + " " + context.getString(R.string.persons);
        if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
            if(pacakgesListFiltered.get(position).getNumber_of_adult() != null){
                numberOfAdult = CommonUtils.translateNumber(pacakgesListFiltered.get(position).getNumber_of_adult(),lang) + " " + context.getString(R.string.persons);
            }
            if(pacakgesListFiltered.get(position).getNumber_of_children() != null){
                numberOfChild = CommonUtils.translateNumber(pacakgesListFiltered.get(position).getNumber_of_children(),lang) + " " + context.getString(R.string.persons);
            }
        }

        holder.txtVisitorName.setText(visitorName);
        holder.textNumberOfAdult.setText(numberOfAdult);
        holder.textNumberOfChild.setText(numberOfChild);


        String visitDate = pacakgesListFiltered.get(position).getDate_of_trip();
        if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
            if(pacakgesListFiltered.get(position).getDate_of_trip() != null){
                visitDate = CommonUtils.translate_date_EnToBn(pacakgesListFiltered.get(position).getDate_of_trip(),lang);
            }
        }

        holder.txtTourDate.setText(visitDate);


        String getTitle = pacakgesListFiltered.get(position).getPackage_title_en();
        if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
            if(pacakgesListFiltered.get(position).getPackage_title_bn() != null){
                getTitle = pacakgesListFiltered.get(position).getPackage_title_bn();
            }
        }

        String getOrganizationName = pacakgesListFiltered.get(position).getOrganization_name_en();
        if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
            if(!pacakgesListFiltered.get(position).getOrganization_name_bn().equalsIgnoreCase("")){
                getOrganizationName = pacakgesListFiltered.get(position).getOrganization_name_bn();
            }
        }

        holder.packageTitle.setText( getTitle );
        holder.operatorName.setText( getOrganizationName );
        String dayNight = pacakgesListFiltered.get(position).getDuration_day() +" "+context.getString(R.string.day) +" - "+pacakgesListFiltered.get(position).getDuration_night() +" "+ context.getString(R.string.night);

        if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
            dayNight = CommonUtils.translateNumber(pacakgesListFiltered.get(position).getDuration_day(),"bn") +" "+context.getString(R.string.day) +" - "+pacakgesListFiltered.get(position).getDuration_night() +" "+ context.getString(R.string.night);
        }
        holder.tourTime.setText(dayNight);
        if(pacakgesListFiltered.get(position).getPackage_image() != null ){
            Uri imgUri = null;
            if(pacakgesListFiltered.get(position).getPackage_image() !=null) {
                imgUri = Uri.parse(App_Config.BASE_URL_FOR_ONLY_IMAGE + pacakgesListFiltered.get(position).getPackage_image());
            }
            Picasso.with(context)
//                .load("https://i.picsum.photos/id/1/5616/3744.jpg?hmac=kKHwwU8s46oNettHKwJ24qOlIAsWN9d2TtsXDoCWWsQ")
                    .load(imgUri)
                    .resize(1500, 150)
                    .placeholder(R.drawable.placeholder_iamge)
                    .error(R.drawable.error_no_iamge)
                    .into(holder.packageImage);
        }else{
            holder.costRange.setText(pacakgesListFiltered.get(position).getPrice_start_from());
            Uri imgUri = Uri.parse("android.resource://" + context.getPackageName() + "/drawable/" + "slide3");
            holder.packageImage.setImageURI(imgUri);
        }


//        holder.packageLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pacakgesObjectModel = pacakgesListFiltered.get(position);
//
//                Intent newIntent = new Intent(context, PackageDetailsActivity.class);
//                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                newIntent.putExtra("mode","view");
////                newIntent.putExtra("pacakgesObject",pacakgesObjectModel);
//                context.startActivity(newIntent);
//
////                holder.packageLayout.getContext().startActivity(new Intent(holder.packageLayout.getContext(), PackageDetailsActivity.class));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return pacakgesListFiltered.size();
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder {
        TextView packageTitle, operatorName, tourTime, costRange,txtVisitorName,txtTourDate,textNumberOfAdult,textNumberOfChild;
        ImageView packageImage;
        LinearLayout packageLayout;
        public TextAwesome data_serial_no;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
            data_serial_no = (TextAwesome) itemView.findViewById(R.id.data_serial_no);
            packageLayout = itemView.findViewById(R.id.idLayoutPackageList);
            packageTitle = itemView.findViewById(R.id.idPackageRowPackageTitle);
            operatorName = itemView.findViewById(R.id.idPackageRowOperatorName);
            tourTime = itemView.findViewById(R.id.idPacakgeRowTourTime);
            costRange = itemView.findViewById(R.id.idPacakgeRowCostRange);
            packageImage = itemView.findViewById(R.id.packageImage);
            txtVisitorName = itemView.findViewById(R.id.txtVisitorName);
            txtTourDate = itemView.findViewById(R.id.txtTourDate);
            textNumberOfAdult = itemView.findViewById(R.id.textNumberOfAdult);
            textNumberOfChild = itemView.findViewById(R.id.textNumberOfChild);

        }
    }
}
