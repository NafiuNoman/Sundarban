package com.ets.bfd.visitor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.activity.ConfirmTicket;
import com.ets.bfd.visitor.activity.OneDayTourTicket;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.MainSendModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.ParentSendOneDayTourModel;

import java.util.ArrayList;
import java.util.List;

public class TicketItemRecyclerAdapter extends RecyclerView.Adapter<TicketItemRecyclerAdapter.TicketItemViewHolder> {


    Context context;
    List<ParentSendOneDayTourModel> parentSendOneDayTourModelList;
    MainSendModel mainSendModel;

    public TicketItemRecyclerAdapter(Context context, List<ParentSendOneDayTourModel> parentSendOneDayTourModelList, MainSendModel mainSendModel) {
        this.context = context;
        this.parentSendOneDayTourModelList = parentSendOneDayTourModelList;
        this.mainSendModel = mainSendModel;
    }

    @NonNull
    @Override
    public TicketItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.confirm_ticket_row, parent, false);
        return new TicketItemViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull TicketItemViewHolder holder, int position) {


        holder.spotName.setText(parentSendOneDayTourModelList.get(position).getSpotName());
        holder.tourDate.setText(parentSendOneDayTourModelList.get(position).getVisitDate());
        holder.timeSlot.setText(parentSendOneDayTourModelList.get(position).getTimeSlot());
        holder.totalSingleTicketPrice.setText(parentSendOneDayTourModelList.get(position).getTotalSingleTicketFee());

        if (Integer.parseInt(parentSendOneDayTourModelList.get(position).getSendOneDayGuardModel().getGuardPerson()) > 0) {
            holder.guardLayout.setVisibility(View.VISIBLE);
            holder.guardNetPrice.setText(parentSendOneDayTourModelList.get(position).getSendOneDayGuardModel().getNetFee());
            holder.guardVat.setText(parentSendOneDayTourModelList.get(position).getSendOneDayGuardModel().getVat());
            holder.guardCount.setText(parentSendOneDayTourModelList.get(position).getSendOneDayGuardModel().getGuardPerson());
            holder.totalGuardPrice.setText(parentSendOneDayTourModelList.get(position).getSendOneDayGuardModel().getTotalFee());

        }

        ChildTouristTypeCalculationAdapter childTouristTypeCalculationAdapter = new ChildTouristTypeCalculationAdapter(context, parentSendOneDayTourModelList.get(position).getSendTouristList(), parentSendOneDayTourModelList.get(position).getSendOneDayGuardModel());

        holder.recyclerView.setAdapter(childTouristTypeCalculationAdapter);

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfirmTicket.allTicketFee = ConfirmTicket.allTicketFee - Double.parseDouble(parentSendOneDayTourModelList.get(holder.getAdapterPosition()).getTotalSingleTicketFee());

                ConfirmTicket.allTicketGrandTotal.setText(String.valueOf(ConfirmTicket.allTicketFee));

                parentSendOneDayTourModelList.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());


            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ticketPosition = holder.getAdapterPosition();

                editTicket(ticketPosition);


                parentSendOneDayTourModelList.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());

//                parentSendOneDayTourModelList.remove(holder.getAdapterPosition());



            }
        });


    }

    private void editTicket(int ticketPosition) {

//        parentSendOneDayTourModelList.remove(ticketPosition);
//
//        notifyItemRemoved(ticketPosition);


        Intent newIntent = new Intent(context, OneDayTourTicket.class);

        newIntent.putExtra("forEdit", mainSendModel);
        newIntent.putExtra("itemPositionFromList", ticketPosition);
        newIntent.putExtra("modeFor", "editTicket");

        context.startActivity(newIntent);

    }


    @Override
    public int getItemCount() {
        return parentSendOneDayTourModelList.size();
    }

    public class TicketItemViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        Button removeBtn, editBtn;
        TextView spotName, tourDate, timeSlot, totalSingleTicketPrice, guardNetPrice, guardVat, guardCount, totalGuardPrice;
        ConstraintLayout guardLayout;

        public TicketItemViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.idTicketChildListRecycler);

            guardLayout = itemView.findViewById(R.id.idSecurityGuardRowLayout);

            spotName = itemView.findViewById(R.id.idConfirmTicketRowSpotName);
            tourDate = itemView.findViewById(R.id.idConfirmTicketRowDate);
            timeSlot = itemView.findViewById(R.id.idConfirmTicketRowTimeSlot);
            totalSingleTicketPrice = itemView.findViewById(R.id.idConfirmTicketRowTotalTicketPrice);
            guardNetPrice = itemView.findViewById(R.id.idConfirmTicketRowGuardNetPrice);
            guardVat = itemView.findViewById(R.id.idConfirmTicketRowGuardVatPrice);
            guardCount = itemView.findViewById(R.id.idConfirmTicketRowTotalGuardCount);
            totalGuardPrice = itemView.findViewById(R.id.idConfirmTicketRowTotalGuardPrice);

            removeBtn = itemView.findViewById(R.id.idRemoveButton);
            editBtn = itemView.findViewById(R.id.idEditButton);


        }
    }
}
