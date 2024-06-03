package com.ets.bfd.visitor.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ets.bfd.visitor.activity.DynamicFragment;
import com.ets.bfd.visitor.models.DynamicOneDayTicketModel;
import com.ets.bfd.visitor.models.MyTicketListModel;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int noOfItems;
    private MyTicketListModel ticketObjectModel;
    private ArrayList<DynamicOneDayTicketModel> dynamicOneDayTicketModelArrayList;
    private String lang = "bn";
    public ViewPagerAdapter(FragmentManager fm, int noOfItems, MyTicketListModel ticketData,ArrayList<DynamicOneDayTicketModel> ticketArrayList,String lang) {
        super(fm);
        this.noOfItems = noOfItems;
        this.ticketObjectModel = ticketData;
        this.dynamicOneDayTicketModelArrayList = ticketArrayList;
        this.lang = lang;
    }

    @Override
    public Fragment getItem(int position) {
        int itemPosition = position + 1;
        return DynamicFragment.newInstance(itemPosition,ticketObjectModel,dynamicOneDayTicketModelArrayList,lang);
    }

    @Override
    public int getCount() {
        return noOfItems;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Ticket # "+(position+1);
    }
}