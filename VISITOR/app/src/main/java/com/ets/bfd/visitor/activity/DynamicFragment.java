package com.ets.bfd.visitor.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.adapters.MyTicketListAdapter;
import com.ets.bfd.visitor.adapters.TicketListItemAdapter;
import com.ets.bfd.visitor.models.DynamicOneDayTicketListItemModel;
import com.ets.bfd.visitor.models.DynamicOneDayTicketModel;
import com.ets.bfd.visitor.models.MyTicketListModel;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DynamicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DynamicFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_TICKET_DATA = "ticket_data";
    private static final String ARG_TICKET_ARRAY_LIST = "ticket_array_list";
    private static final String ARG_SET_LANG = "ARG_SET_LANG";
    private int sectionNumber;
    private String lang;
    private MyTicketListModel ticketObjectModel;
    private ArrayList<DynamicOneDayTicketModel> dynamicOneDayTicketModelArrayList;
    private TicketListItemAdapter ticketListItemAdapter;
    public DynamicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sectionNumber = getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1;
        lang = getArguments() != null ? getArguments().getString(ARG_SET_LANG) : "en";
        ticketObjectModel = getArguments() != null ? (MyTicketListModel) getArguments().getSerializable(ARG_TICKET_DATA) : null;
        dynamicOneDayTicketModelArrayList = getArguments() != null ? (ArrayList<DynamicOneDayTicketModel>) getArguments().getSerializable(ARG_TICKET_ARRAY_LIST) : null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        TextView textView = view.findViewById(R.id.txtTabItemNumber);
        TextView textSpotName = view.findViewById(R.id.textSpotName);
        TextView textTravelDate = view.findViewById(R.id.textTravelDate);
        TextView textTravelTime = view.findViewById(R.id.textTravelTime);
        TextView textTicketHolderName = view.findViewById(R.id.textTicketHolderName);
        TextView textTicketHolderMobile = view.findViewById(R.id.textTicketHolderMobile);
        TextView textTicketHolderEmail = view.findViewById(R.id.textTicketHolderEmail);
        TextView textTicketHolderPurchaseDate = view.findViewById(R.id.textTicketHolderPurchaseDate);
        TextView textGrandTotalPrice = view.findViewById(R.id.textGrandTotalPrice);
        ImageView qr_code = view.findViewById(R.id.qr_code);
        RecyclerView recyclerListView = view.findViewById(R.id.recyclerListView);
        TextView textTicketWiseTotalAmount = view.findViewById(R.id.textTicketWiseTotalAmount);
        Button idDownlaodAsPdfButton = view.findViewById(R.id.idDownlaodAsPdfButton);

        textView.setText(CommonUtils.translateNumber(ticketObjectModel.getApplication_number(),lang));
//        textView.setText("Ticket Content " + sectionNumber);

        textTravelDate.setText(ticketObjectModel.getTour_date());
        textTicketHolderName.setText(ticketObjectModel.getApplicant_name());
        textTicketHolderMobile.setText(ticketObjectModel.getMobile_no());
        textTicketHolderEmail.setText(ticketObjectModel.getEmail());
        textTicketHolderPurchaseDate.setText(CommonUtils.translate_date_EnToBn(ticketObjectModel.getCreated_at(),lang));
        textGrandTotalPrice.setText(ticketObjectModel.getAmount_paid());
        textSpotName.setText(dynamicOneDayTicketModelArrayList.get(sectionNumber-1).getSpot_name_en());
        textTravelTime.setText(dynamicOneDayTicketModelArrayList.get(sectionNumber-1).getTime_slot());

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(dynamicOneDayTicketModelArrayList.get(sectionNumber-1).getQr_code(), BarcodeFormat.QR_CODE, 400, 400);
            qr_code.setImageBitmap(bitmap);
        } catch(Exception e) {

        }

        ticketListItemAdapter = new TicketListItemAdapter((ArrayList<DynamicOneDayTicketListItemModel>) dynamicOneDayTicketModelArrayList.get(sectionNumber-1).getTicketItemList(),textTicketWiseTotalAmount,lang);
        recyclerListView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(this.getContext());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerListView.setAdapter(ticketListItemAdapter);
        recyclerListView.setLayoutManager(MyLayoutManager);



        idDownlaodAsPdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getContext(), WebViewActivity.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                newIntent.putExtra("mode_for", "one_day_ticket_pdf");
                newIntent.putExtra("scope_id", ticketObjectModel.getId());
                getContext().startActivity(newIntent);
            }
        });

        return  view;
    }

    public static DynamicFragment newInstance(int sectionNumber, MyTicketListModel ticketData, ArrayList<DynamicOneDayTicketModel> ticketArrayList,String lang) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_SET_LANG, lang);
        args.putSerializable(ARG_TICKET_DATA, ticketData);
        args.putSerializable(ARG_TICKET_ARRAY_LIST, ticketArrayList);
        fragment.setArguments(args);
        return fragment;
    }


}