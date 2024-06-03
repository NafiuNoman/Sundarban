package com.ets.bfd.visitor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ets.bfd.visitor.R;
import com.google.android.material.textfield.TextInputEditText;

public class OneDayTourRecyclerPassportAdapter extends RecyclerView.Adapter<OneDayTourRecyclerPassportAdapter.PassportViewHolder> {

    Context context;
    int passPortCount;

    public OneDayTourRecyclerPassportAdapter(Context context, int passPortCount) {
        this.context = context;
        this.passPortCount = passPortCount;
    }

    @NonNull
    @Override
    public PassportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.passport_row, parent, false);

        return new PassportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassportViewHolder holder, int position) {

//        holder.passportNumberInput.setText("12456398");

    }

    @Override
    public int getItemCount() {
        return passPortCount;
    }

    public class PassportViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText passportNumberInput;

        public PassportViewHolder(@NonNull View itemView) {
            super(itemView);

          passportNumberInput=  itemView.findViewById(R.id.idPassportRowInput);
        }
    }
}
