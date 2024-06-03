package com.ets.bfd.visitor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ets.bfd.visitor.R;
import com.google.android.material.textfield.TextInputEditText;

public class PopupPassportAdapter extends RecyclerView.Adapter<PopupPassportAdapter.PopupPassportViewHolder> {
    Context context;
    private String lang = "bn";
    public static int passportCounter;
    public PopupPassportAdapter(Context context, int counter, String lang) {
        this.passportCounter = counter;
        this.lang = lang;
    }

    @NonNull
    @Override
    public PopupPassportAdapter.PopupPassportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.passport_row, parent, false);
        return new PopupPassportAdapter.PopupPassportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopupPassportAdapter.PopupPassportViewHolder holder, int position) {
//        holder.idPassportRowInput.requestFocusFromTouch();
    }

    @Override
    public int getItemCount() {
        return passportCounter;
    }

    public class PopupPassportViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText idPassportRowInput;

        public PopupPassportViewHolder(@NonNull View itemView) {
            super(itemView);
            idPassportRowInput = itemView.findViewById(R.id.idPassportRowInput);
        }
    }
}
