package com.ets.bfd.visitor.models.touristfee;

public class TouristDetails {

  String tourist_type_with_fee;
  String tourist_type;
  String tourist_fee;
  int id;

    public TouristDetails(String tourist_type_with_fee, String tourist_type, String tourist_fee, int id) {
        this.tourist_type_with_fee = tourist_type_with_fee;
        this.tourist_type = tourist_type;
        this.tourist_fee = tourist_fee;
        this.id = id;
    }

    public TouristDetails() {
    }


    public String getTourist_type_with_fee() {
        return tourist_type_with_fee;
    }

    public void setTourist_type_with_fee(String tourist_type_with_fee) {
        this.tourist_type_with_fee = tourist_type_with_fee;
    }

    public String getTourist_type() {
        return tourist_type;
    }

    public void setTourist_type(String tourist_type) {
        this.tourist_type = tourist_type;
    }

    public String getTourist_fee() {
        return tourist_fee;
    }

    public void setTourist_fee(String tourist_fee) {
        this.tourist_fee = tourist_fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
