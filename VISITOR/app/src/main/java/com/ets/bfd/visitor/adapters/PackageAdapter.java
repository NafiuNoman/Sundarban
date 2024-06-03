package com.ets.bfd.visitor.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder> {


    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder {


        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
