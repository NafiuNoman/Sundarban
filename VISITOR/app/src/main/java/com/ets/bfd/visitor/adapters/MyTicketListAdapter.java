package com.ets.bfd.visitor.adapters;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.activity.DynamicTabsActivity;
import com.ets.bfd.visitor.activity.MyTicketActivity;
import com.ets.bfd.visitor.activity.PackageDetailsActivity;
import com.ets.bfd.visitor.models.MyTicketListModel;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.TextAwesome;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class MyTicketListAdapter extends RecyclerView.Adapter<MyTicketListAdapter.TicketViewHolder> {
    private MyTicketListModel ticketObjectModel;
    private ArrayList<MyTicketListModel> list;
    private ArrayList<MyTicketListModel> ticketListFiltered;
    private Activity activity;
    private Context context;
    private AlertDialog alert;
    Dialog alertDialog;
    TextAwesome total_list_count;
    private String lang = "bn";

    public MyTicketListAdapter(ArrayList<MyTicketListModel> Data, MyTicketListModel objectData, Context context, TextAwesome total_list_count) {
        this.list = Data;
        this.ticketListFiltered = Data;
        this.ticketObjectModel = objectData;
        this.context = context;
        alertDialog = new Dialog(context);
        this.total_list_count = total_list_count;
        lang = CommonUtils.getCurrentLanguage(  this.context  );

    }

    @NonNull
    @Override
    public MyTicketListAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_ticket_list_item, parent, false);
        return new MyTicketListAdapter.TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketListAdapter.TicketViewHolder holder, int position) {

        int totalList = getItemCount();
        int serialNo = position+1 ;
        String totalCount = String.valueOf(totalList);
        String dataSerialNo = String.valueOf(serialNo);
        total_list_count.setText(totalCount);
        holder.data_serial_no.setText( CommonUtils.translateNumber(dataSerialNo,lang) );

        holder.textTicketNumber.setText(ticketListFiltered.get(position).getApplication_number());
        holder.textApplicantName.setText(ticketListFiltered.get(position).getApplicant_name());
        holder.textMobileNumber.setText(ticketListFiltered.get(position).getMobile_no());
        holder.textSpotName.setText(ticketListFiltered.get(position).getSpots_name_en());
        holder.textEntryTime.setText(ticketListFiltered.get(position).getStart_time());

        holder.btnViewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DynamicTabsActivity
                ticketObjectModel = ticketListFiltered.get(position);

                Intent newIntent = new Intent(context, DynamicTabsActivity.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                newIntent.putExtra("one_day_tour_id",ticketListFiltered.get(position).getId());
                newIntent.putExtra("oneDayTourInfo",ticketObjectModel);
                context.startActivity(newIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ticketListFiltered.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView textTicketNumber, textApplicantName, textMobileNumber, textSpotName,textEntryTime;
        MaterialButton btnViewTicket;
        TextAwesome data_serial_no;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            btnViewTicket = itemView.findViewById(R.id.btnViewTicket);
            textTicketNumber = itemView.findViewById(R.id.textTicketNumber);
            textApplicantName = itemView.findViewById(R.id.textApplicantName);
            textMobileNumber = itemView.findViewById(R.id.textMobileNumber);
            textSpotName = itemView.findViewById(R.id.textSpotName);
            textEntryTime = itemView.findViewById(R.id.textEntryTime);
            data_serial_no = itemView.findViewById(R.id.data_serial_no);

        }
    }
}
