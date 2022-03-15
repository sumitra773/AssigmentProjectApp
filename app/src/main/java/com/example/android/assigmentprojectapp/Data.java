package com.example.android.assigmentprojectapp;

import android.net.Uri;

import java.util.ArrayList;

public class Data {
   private String title;
   private ArrayList<Uri> images = new ArrayList<>();

   public Data() {
   }

   public Data(String title) {
      this.title = title;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public ArrayList<Uri> getImages() {
      return images;
   }

   public void setImages(ArrayList<Uri> images) {
      this.images = images;
   }
}
