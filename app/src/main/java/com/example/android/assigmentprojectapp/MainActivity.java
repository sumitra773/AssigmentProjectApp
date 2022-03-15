package com.example.android.assigmentprojectapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.assigmentprojectapp.adapter.DataAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Data> mDataArrayList = new ArrayList<>();

    FloatingActionButton mFab;
    RecyclerView mRecyclerView;
    DataAdapter mAdapter;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DataAdapter(mDataArrayList, new GalleryPickListener() {
            @Override
            public void onGalleryItems(int position) {
                selectedPosition = position;
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                someActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
            }
        }, this);
        mRecyclerView.setAdapter(mAdapter);
        mFab = findViewById(R.id.btn_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog, null);

                final EditText editText = (EditText) mView.findViewById(R.id.edt_input);
                Button btn_ok = (Button) mView.findViewById(R.id.btn_ok);
                Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);


                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editText.getText().toString().trim().isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please enter title", Toast.LENGTH_LONG).show();
                            return;
                        }
                        mDataArrayList.add(new Data(editText.getText().toString().trim()));
                        mAdapter.updateData(mDataArrayList);
                        alertDialog.dismiss();

                    }

                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }


        });


    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();
                        ArrayList<Uri> images = new ArrayList<>();
                        if (data.getClipData() != null) {
                            int count = data.getClipData().getItemCount();
                            for(int i = 0; i < count; i++) {
                                Uri imageUri = data.getClipData().getItemAt(i).getUri();
                                images.add(imageUri);
                            }
                        }  else if(data.getData() != null) {
                            Uri imageUri = data.getData();
                            images.add(imageUri);
                        }

                        if(selectedPosition != -1) {
                            mDataArrayList.get(selectedPosition).setImages(images);
                            mAdapter.updateData(mDataArrayList);
                        }

                    }
                }
            });

}