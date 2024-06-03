package com.ets.bfd.visitor.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.SendOneDayGuardModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.SendOneDayTouristModel;

import java.util.List;

public class ChildTouristTypeCalculationAdapter extends RecyclerView.Adapter<ChildTouristTypeCalculationAdapter.TouristTypeCalculationViewHolder> {
    Context context;
    List<SendOneDayTouristModel> touristList;
    SendOneDayGuardModel oneDayGuardModel;

    public ChildTouristTypeCalculationAdapter(Context context, List<SendOneDayTouristModel> touristList, SendOneDayGuardModel oneDayGuardModel) {
        this.context=context;
        this.touristList=touristList;
        this.oneDayGuardModel=oneDayGuardModel;
    }

    @NonNull
    @Override
    public TouristTypeCalculationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_tourist_row,parent,false);
        return new TouristTypeCalculationViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TouristTypeCalculationViewHolder holder, int position) {


        holder.typeName.setText(touristList.get(position).getTouristName());
        holder.netPrice.setText(touristList.get(position).getNetPrice());
        holder.vatPrice.setText(touristList.get(position).getVatPrice());
        holder.typeCount.setText(touristList.get(position).getTotalPerson());
        holder.typeTotalPrice.setText(touristList.get(position).getTotal());






    }

    @Override
    public int getItemCount() {
        return touristList.size();
    }

    public class TouristTypeCalculationViewHolder extends RecyclerView.ViewHolder {

        TextView typeName,netPrice,vatPrice,typeCount,typeTotalPrice;



        public TouristTypeCalculationViewHolder(@NonNull View itemView) {
            super(itemView);
            typeName = itemView.findViewById(R.id.idChildCalculationRowTypeName);
            netPrice = itemView.findViewById(R.id.idChildTouristNetPrice);
            vatPrice = itemView.findViewById(R.id.idChildTouristVatPrice);
            typeCount = itemView.findViewById(R.id.idChildTouristCount);
            typeTotalPrice = itemView.findViewById(R.id.idChildTouristTotal);


        }
    }
}
