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
import com.ets.bfd.visitor.activity.OverNightTour;
import com.ets.bfd.visitor.activity.PackageDetailsActivity;
import com.ets.bfd.visitor.models.PackagesModel;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.PackageViewHolder> {
    private PackagesModel pacakgesObjectModel;
    private ArrayList<PackagesModel> list;
    private List<PackagesModel> pacakgesListFiltered;
    private Activity activity;
    private Context context;
    private AlertDialog alert;
    Dialog alertDialog;

    public PackageListAdapter(ArrayList<PackagesModel> Data, PackagesModel objectData, Context context) {
        this.list = Data;
        this.pacakgesListFiltered = Data;
        this.pacakgesObjectModel = objectData;
        this.context = context;
        alertDialog = new Dialog(context);

    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_package_row, parent, false);
        return new PackageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder holder, int position) {
        String getTitle = pacakgesListFiltered.get(position).getTitle_en();
        if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
            if(pacakgesListFiltered.get(position).getTitle_bn() != null){
                getTitle = pacakgesListFiltered.get(position).getTitle_bn();
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


        holder.packageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pacakgesObjectModel = pacakgesListFiltered.get(position);

                Intent newIntent = new Intent(context, PackageDetailsActivity.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                newIntent.putExtra("mode","view");
                newIntent.putExtra("pacakgesObject",pacakgesObjectModel);
                context.startActivity(newIntent);

//                holder.packageLayout.getContext().startActivity(new Intent(holder.packageLayout.getContext(), PackageDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pacakgesListFiltered.size();
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder {
        TextView  packageTitle, operatorName, tourTime, costRange;
        ImageView packageImage;
        LinearLayout packageLayout;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
            packageLayout = itemView.findViewById(R.id.idLayoutPackageList);
            packageTitle = itemView.findViewById(R.id.idPackageRowPackageTitle);
            operatorName = itemView.findViewById(R.id.idPackageRowOperatorName);
            tourTime = itemView.findViewById(R.id.idPacakgeRowTourTime);
            costRange = itemView.findViewById(R.id.idPacakgeRowCostRange);
            packageImage = itemView.findViewById(R.id.packageImage);

        }
    }
}
