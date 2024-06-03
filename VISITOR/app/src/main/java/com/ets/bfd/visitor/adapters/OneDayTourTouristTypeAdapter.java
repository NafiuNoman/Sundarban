package com.ets.bfd.visitor.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.models.one_day_tour_all_data.TouristTypeModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OneDayTourTouristTypeAdapter extends RecyclerView.Adapter<OneDayTourTouristTypeAdapter.TouristTypeViewHolder> {


    public static int[] counter;
    Context context;
    List<TouristTypeModel> touristTypeModelList;
    HashMap<Integer, String> idAndCounter;
    String mode="";

    int totalPassportCountForForeignTourist = 0;
    int totalPassportCountForForeignResearcher = 0;
    public static int allPassportCount = 0;

    public OneDayTourTouristTypeAdapter(int[] counter, Context context, List<TouristTypeModel> touristTypeModelList, HashMap<Integer, String> idAndCounter,String mode) {
        this.counter = counter;
        this.context = context;
        this.touristTypeModelList = touristTypeModelList;
        this.idAndCounter = idAndCounter;
        this.mode= mode;

    }

    @NonNull
    @Override
    public TouristTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_one_day_tourist_type, parent, false);

        return new TouristTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TouristTypeViewHolder holder, int position) {


        holder.touristTypeName.setText(touristTypeModelList.get(position).getType());

        holder.touristTypeNetPrice.setText(touristTypeModelList.get(position).getNetPrice());
        holder.touristTypeVat.setText(touristTypeModelList.get(position).getVat());
        holder.touristTypeTotalPrice.setText(touristTypeModelList.get(position).getTotalPrice());

        if(mode != null && mode.equalsIgnoreCase("editMode")) {


            Set<Integer> set = idAndCounter.keySet();
            Iterator<Integer> i = set.iterator();
            while (i.hasNext()) {
                //res== id//idAndCounter.get(res)==counter
                Integer res = i.next();
                if(res == touristTypeModelList.get(position).getId()){
                    counter[holder.getAdapterPosition()]=Integer.parseInt(idAndCounter.get(res));
                    holder.personCount.setText(String.valueOf(counter[holder.getAdapterPosition()]));
                }

            }


        }





//        if (touristTypeModelList.get(position).getId() == 3) {
//            Log.d("searching", "" + touristTypeModelList.get(position).getType());
//        }



        holder.minus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (counter[holder.getAdapterPosition()] <= 0) {
                    Toast.makeText(context, "can't be negative", Toast.LENGTH_SHORT).show();

                } else {

                    counter[holder.getAdapterPosition()]--;

                    holder.personCount.setText(String.valueOf(counter[holder.getAdapterPosition()]));

                }


                if (touristTypeModelList.get(holder.getAdapterPosition()).getId() == 3) {

                    totalPassportCountForForeignTourist--;
                    allPassportCount = totalPassportCountForForeignTourist + totalPassportCountForForeignResearcher;



                } else if (touristTypeModelList.get(holder.getAdapterPosition()).getId() == 7) {

                    totalPassportCountForForeignResearcher--;
                    allPassportCount = totalPassportCountForForeignTourist + totalPassportCountForForeignResearcher;


                }

                idAndCounter.put(touristTypeModelList.get(holder.getAdapterPosition()).getId(), String.valueOf(counter[holder.getAdapterPosition()]));


            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter[holder.getAdapterPosition()]++;


                holder.personCount.setText(String.valueOf(counter[holder.getAdapterPosition()]));


                if (touristTypeModelList.get(holder.getAdapterPosition()).getId() == 3) {

                    totalPassportCountForForeignTourist++;

                    allPassportCount = totalPassportCountForForeignTourist + totalPassportCountForForeignResearcher;


                } else if (touristTypeModelList.get(holder.getAdapterPosition()).getId() == 7) {
                    totalPassportCountForForeignResearcher++;
                    allPassportCount = totalPassportCountForForeignTourist + totalPassportCountForForeignResearcher;

                }


                idAndCounter.put(touristTypeModelList.get(holder.getAdapterPosition()).getId(), String.valueOf(counter[holder.getAdapterPosition()]));


            }
        });


//        idAndCounter.put(touristTypeModelList.get(holder.getAdapterPosition()).getId(),String.valueOf(counter[holder.getAdapterPosition()]));

//        touristIdCounterList.get(position).setTypeId(touristTypeModelList.get(position).getId());


    }

    @Override
    public int getItemCount() {
        return touristTypeModelList.size();
    }

    public class TouristTypeViewHolder extends RecyclerView.ViewHolder {


        TextView touristTypeName, touristTypeNetPrice, touristTypeVat, touristTypeTotalPrice, personCount;
        ImageView minus, plus;

        public TouristTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            touristTypeName = itemView.findViewById(R.id.idTouristTypeName);
            touristTypeNetPrice = itemView.findViewById(R.id.idRowTouristTypeNetPrice);
            touristTypeVat = itemView.findViewById(R.id.idRowTouristTypeVat);
            touristTypeTotalPrice = itemView.findViewById(R.id.idRowTouristTypeTotalPrice);
            minus = itemView.findViewById(R.id.idRowTouristTypeMinus);
            plus = itemView.findViewById(R.id.idRowTouristTypePlus);
            personCount = itemView.findViewById(R.id.idRowTouristTypeCounter);


        }

    }


}
