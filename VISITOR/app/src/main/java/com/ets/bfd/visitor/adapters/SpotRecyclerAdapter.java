package com.ets.bfd.visitor.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.models.one_day_tour_all_data.EntryTimeModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.SpotsModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.TouristTypeModel;
import com.ets.bfd.visitor.utilities.App_Config;

import java.util.List;

public class SpotRecyclerAdapter extends RecyclerView.Adapter<SpotRecyclerAdapter.SpotViewHolder> {

    private OnSpotItemListener onSpotItemListener;

    List<EntryTimeModel> entryTimeModelList;
    List<TouristTypeModel> touristTypeModelList;

    Context context;
    List<SpotsModel> spotsModelList;


    boolean isRadioButtonSelected = false;

    int lastSelectedPosition = -1;
    int editSpotId = 0;

    public SpotRecyclerAdapter(Context context, List<SpotsModel> spotsModelList, OnSpotItemListener onSpotItemListener,int editSpotId) {
        this.context = context;
        this.spotsModelList = spotsModelList;
        this.onSpotItemListener = onSpotItemListener;
        this.editSpotId = editSpotId;
    }

    @NonNull
    @Override
    public SpotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spot_row, parent, false);

        return new SpotViewHolder(view, onSpotItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotViewHolder holder, int position) {

        holder.spotName.setText(spotsModelList.get(position).getSpotName());

        String imageUrl = App_Config.BASE_URL_FOR_ONLY_IMAGE + spotsModelList.get(position).getSpotPic();
        Glide.with(context).load(imageUrl).into(holder.spotImage);
        if(editSpotId != 0 && editSpotId == spotsModelList.get(position).getId()){
            lastSelectedPosition = position;
            holder.spotRowSelectSpot.setChecked(true);

            int spotId = spotsModelList.get(lastSelectedPosition).getId();
            String spotName = spotsModelList.get(lastSelectedPosition).getSpotName();
            entryTimeModelList = spotsModelList.get(lastSelectedPosition).getEntryTime();
            touristTypeModelList = spotsModelList.get(lastSelectedPosition).getTouristType();
            onSpotItemListener.onSpotClick(entryTimeModelList, spotId, spotName, touristTypeModelList);

        }else{
            holder.spotRowSelectSpot.setChecked(lastSelectedPosition == position);
        }


        Log.d("position", "" + lastSelectedPosition);

        holder.showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "how details click", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return spotsModelList.size();
    }

    public class SpotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnSpotItemListener onSpotItemListener;

        ImageView spotImage;

        TextView spotName, pickUpPointName;

        Button btnTourTicket, btnViewDetails;
        ConstraintLayout showDetails;
        RadioButton spotRowSelectSpot;

        public SpotViewHolder(@NonNull View itemView, OnSpotItemListener onSpotItemListener) {
            super(itemView);

            this.onSpotItemListener = onSpotItemListener;

            spotImage = itemView.findViewById(R.id.idSpotImage);

            showDetails = itemView.findViewById(R.id.idSpotRowShowDetails);
            spotRowSelectSpot = itemView.findViewById(R.id.idSpotRowSelectSpot);


            spotName = itemView.findViewById(R.id.idSpotNameHeading);
            pickUpPointName = itemView.findViewById(R.id.idSpotPickUpPointHeading);


            spotRowSelectSpot.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            lastSelectedPosition = getAdapterPosition();
            notifyDataSetChanged();

            int spotId = spotsModelList.get(lastSelectedPosition).getId();
            String spotName = spotsModelList.get(lastSelectedPosition).getSpotName();
            entryTimeModelList = spotsModelList.get(lastSelectedPosition).getEntryTime();


            touristTypeModelList = spotsModelList.get(lastSelectedPosition).getTouristType();

            onSpotItemListener.onSpotClick(entryTimeModelList, spotId, spotName, touristTypeModelList);


        }
    }

    public interface OnSpotItemListener {

        void onSpotClick(List<EntryTimeModel> entryTimeModelList, int spotId, String spotName, List<TouristTypeModel> touristTypeModelList);


    }


}
