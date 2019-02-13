package com.example.facedetection;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.microsoft.projectoxford.face.contract.Face;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String data = getIntent().getStringExtra("list_faces");
        ImageView imageView = findViewById(R.id.imageView1);
//        Bitmap originalBitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();

        Gson gson = new Gson();
        Face[] faces = gson.fromJson(data, Face[].class);
        Bitmap originalBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.photo);
        ListView myListView = findViewById(R.id.list_view);

        CustomAdapter customAdapter = new CustomAdapter(faces, this, originalBitmap);
        myListView.setAdapter(customAdapter);

    }
}
