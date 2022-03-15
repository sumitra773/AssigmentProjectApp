package com.example.android.assigmentprojectapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.assigmentprojectapp.Data;
import com.example.android.assigmentprojectapp.GalleryPickListener;
import com.example.android.assigmentprojectapp.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Data> mDataArrayList;
    private Context mContext;
    private GalleryPickListener mListener;

    public DataAdapter(ArrayList<Data> dataArrayList, GalleryPickListener listener, Context context) {
        mDataArrayList = dataArrayList;
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_title.setText(mDataArrayList.get(position).getTitle());
        if(mDataArrayList.get(position).getImages().size() > 0) {
            ImageAdapter adapter = new ImageAdapter(mDataArrayList.get(position).getImages());
            holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            holder.mRecyclerView.setAdapter(adapter);
        }
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null) {
                    mListener.onGalleryItems(holder.getAdapterPosition());
                }
             }
        });

    }

    @Override
    public int getItemCount() {
        return mDataArrayList.size();
    }

    public void updateData(ArrayList<Data> dataArrayList) {
        this.mDataArrayList = dataArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView mImageView;
        RecyclerView mRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.rv_item);
            tv_title = itemView.findViewById(R.id.tv_title);
            mImageView = itemView.findViewById(R.id.img_view);
        }
    }
}
