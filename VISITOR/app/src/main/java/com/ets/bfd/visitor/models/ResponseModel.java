package com.ets.bfd.visitor.models;

import java.util.List;

public class ResponseModel {
    private String message;
    private String code;
    private List<BookingPackageListModel> bookingList;
    private List<MyTicketListModel> ticketLists;
    private List<DynamicOneDayTicketModel> ticketListByOneDayId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BookingPackageListModel> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<BookingPackageListModel> bookingList) {
        this.bookingList = bookingList;
    }

    public List<MyTicketListModel> getTicketLists() {
        return ticketLists;
    }

    public void setTicketLists(List<MyTicketListModel> ticketLists) {
        this.ticketLists = ticketLists;
    }

    public List<DynamicOneDayTicketModel> getTicketListByOneDayId() {
        return ticketListByOneDayId;
    }

    public void setTicketListByOneDayId(List<DynamicOneDayTicketModel> ticketListByOneDayId) {
        this.ticketListByOneDayId = ticketListByOneDayId;
    }
}
