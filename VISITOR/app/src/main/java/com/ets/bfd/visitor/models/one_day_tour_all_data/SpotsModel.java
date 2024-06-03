package com.ets.bfd.visitor.models.one_day_tour_all_data;

import java.util.List;

public class SpotsModel {

   int id;
   String spotName;
   String spotNameBn;
   String spotPic;
   String spotDetails;
   String spotDetailsBn;
   List<EntryTimeModel> entryTime;
   List<TouristTypeModel> touristType;

   public SpotsModel() {
   }

   public SpotsModel(int id, String spotName, String spotNameBn, String spotPic, String spotDetails, String spotDetailsBn, List<EntryTimeModel> entryTime, List<TouristTypeModel> touristType) {
      this.id = id;
      this.spotName = spotName;
      this.spotNameBn = spotNameBn;
      this.spotPic = spotPic;
      this.spotDetails = spotDetails;
      this.spotDetailsBn = spotDetailsBn;
      this.entryTime = entryTime;
      this.touristType = touristType;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getSpotName() {
      return spotName;
   }

   public void setSpotName(String spotName) {
      this.spotName = spotName;
   }

   public String getSpotNameBn() {
      return spotNameBn;
   }

   public void setSpotNameBn(String spotNameBn) {
      this.spotNameBn = spotNameBn;
   }

   public String getSpotPic() {
      return spotPic;
   }

   public void setSpotPic(String spotPic) {
      this.spotPic = spotPic;
   }

   public String getSpotDetails() {
      return spotDetails;
   }

   public void setSpotDetails(String spotDetails) {
      this.spotDetails = spotDetails;
   }

   public String getSpotDetailsBn() {
      return spotDetailsBn;
   }

   public void setSpotDetailsBn(String spotDetailsBn) {
      this.spotDetailsBn = spotDetailsBn;
   }

   public List<EntryTimeModel> getEntryTime() {
      return entryTime;
   }

   public void setEntryTime(List<EntryTimeModel> entryTime) {
      this.entryTime = entryTime;
   }

   public List<TouristTypeModel> getTouristType() {
      return touristType;
   }

   public void setTouristType(List<TouristTypeModel> touristType) {
      this.touristType = touristType;
   }
}
