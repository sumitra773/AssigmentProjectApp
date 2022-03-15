package com.example.android.assigmentprojectapp.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.assigmentprojectapp.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

   private ArrayList<Uri> mUriArrayList;

   public ImageAdapter(ArrayList<Uri> mUriArrayList) {
      this.mUriArrayList = mUriArrayList;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
      return new ViewHolder(v);
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.mImageView.setImageURI(mUriArrayList.get(position));
   }

   @Override
   public int getItemCount() {
      return mUriArrayList.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      ImageView mImageView;
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         mImageView = itemView.findViewById(R.id.image_view);
      }
   }
}
