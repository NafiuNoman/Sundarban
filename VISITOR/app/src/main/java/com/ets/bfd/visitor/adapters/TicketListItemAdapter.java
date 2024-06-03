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
import com.ets.bfd.visitor.models.DynamicOneDayTicketListItemModel;
import com.ets.bfd.visitor.utilities.CommonUtils;
import java.util.ArrayList;
import java.util.List;

public class TicketListItemAdapter extends RecyclerView.Adapter<TicketListItemAdapter.PackageViewHolder> {
    private ArrayList<DynamicOneDayTicketListItemModel> list;
    private List<DynamicOneDayTicketListItemModel> itemListFiltered;
    private String lang = "bn";
    private TextView textTotalAmount;
    private double totalAmount = 0;
    public TicketListItemAdapter(ArrayList<DynamicOneDayTicketListItemModel> Data,TextView textTicketWiseTotalAmount,String lang) {
        this.list = Data;
        this.itemListFiltered = Data;
        this.lang = lang;
        this.textTotalAmount = textTicketWiseTotalAmount;
    }

    @NonNull
    @Override
    public TicketListItemAdapter.PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item_row_layout, parent, false);
        return new TicketListItemAdapter.PackageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder holder, int position) {
        String ItemName = itemListFiltered.get(position).getItem_name_en() + ": " + itemListFiltered.get(position).getTotal_person();
        if(lang.equalsIgnoreCase("bn")){
            if(itemListFiltered.get(position).getItem_name_bn() != null){
                ItemName = itemListFiltered.get(position).getItem_name_bn() + ": " + CommonUtils.translateNumber(itemListFiltered.get(position).getTotal_person(),"bn");
            }
        }

        String TotalAmount = "৳ " + itemListFiltered.get(position).getTotal_person();
        if(lang.equalsIgnoreCase("bn")){
            if(itemListFiltered.get(position).getTotal_person() != null){
                TotalAmount = "৳ " + CommonUtils.translateNumber(itemListFiltered.get(position).getTotal_fee(),"bn");
            }
        }

        holder.textItemName.setText( ItemName );
        holder.textTotalAmount.setText( TotalAmount );
        totalAmount = totalAmount + Double.parseDouble(itemListFiltered.get(position).getTotal_fee());
        textTotalAmount.setText(String.valueOf(totalAmount));

    }

    @Override
    public int getItemCount() {
        return itemListFiltered.size();
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder {
        TextView textItemName, textTotalAmount;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
            textItemName = itemView.findViewById(R.id.textItemName);
            textTotalAmount = itemView.findViewById(R.id.textTotalAmount);

        }
    }
}
