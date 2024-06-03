package com.ets.bfd.visitor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.SendOneDayVesselModel;

import java.util.List;
import java.util.zip.Inflater;

public class VesselRecyclerAdapter extends RecyclerView.Adapter<VesselRecyclerAdapter.VesselViewHolder> {
    Context context;
    List<SendOneDayVesselModel> modelList;

    public VesselRecyclerAdapter(Context context, List<SendOneDayVesselModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public VesselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vessel_row, parent, false);
        return new VesselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VesselViewHolder holder, int position) {

//        holder.vesselName.setText(modelList.get(position).getVesselName());
        holder.vesselName.setText("vessel");
//        holder.vesselCounter.setText();
        holder.vesselNetPrice.setText(modelList.get(position).getEntry_fee());
        holder.vesselTotalPrice.setText(modelList.get(position).getTotal_fee());

        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                modelList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class VesselViewHolder extends RecyclerView.ViewHolder {

        TextView vesselName, vesselNetPrice, vesselCounter, vesselTotalPrice;
        ImageView cancelBtn;

        public VesselViewHolder(@NonNull View itemView) {
            super(itemView);

            vesselName = itemView.findViewById(R.id.idVesselRowVesselName);
            vesselCounter = itemView.findViewById(R.id.idVesselRowVesselCount);
            vesselNetPrice = itemView.findViewById(R.id.idVesselRowNetPrice);
            vesselTotalPrice = itemView.findViewById(R.id.idVesselRowTotalPrice);
            cancelBtn = itemView.findViewById(R.id.idVesselRowRemove);


        }
    }
}
