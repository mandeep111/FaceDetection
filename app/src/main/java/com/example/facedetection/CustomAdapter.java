package com.example.facedetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.FaceDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.projectoxford.face.contract.Face;

public class CustomAdapter extends BaseAdapter {
    private Face[] face;
    private Context context;
    private LayoutInflater inflater;
    private Bitmap originalBitmap;

    public CustomAdapter(Face[] face, Context context, Bitmap originalBitmap) {
        this.face = face;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.originalBitmap = originalBitmap;
    }

    @Override
    public int getCount() {
        return face.length;
    }

    @Override
    public Object getItem(int position) {
        return face[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_view, null);

            TextView textAge, textGender;
            ImageView userImage;

            textAge = view.findViewById(R.id.set_age);
            textGender = view.findViewById(R.id.set_gender);
            userImage = view.findViewById(R.id.set_image);

            textAge.setText("Age: " + face[position].faceAttributes.age);
            textGender.setText("Gender: " + face[position].faceAttributes.gender);

            Bitmap bitmap = (Bitmap) ImageHelper.generateThumbnail(originalBitmap, face[position].faceRectangle);
            userImage.setImageBitmap(bitmap);

//            userImage.setImageBitmap(
//                    MainActivity.drawFaceRectanglesOnBitmap(originalBitmap, face));
        }
        return view;
    }
}
