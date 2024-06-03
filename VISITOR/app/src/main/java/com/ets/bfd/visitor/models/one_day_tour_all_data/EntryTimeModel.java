package com.ets.bfd.visitor.models.one_day_tour_all_data;

public class EntryTimeModel {

    int id;
    int spot_id;
    String time_slot;
    String start_date;
    String person;

    public EntryTimeModel() {
    }

    public EntryTimeModel(int id, int spot_id, String time_slot, String start_date, String person) {
        this.id = id;
        this.spot_id = spot_id;
        this.time_slot = time_slot;
        this.start_date = start_date;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
