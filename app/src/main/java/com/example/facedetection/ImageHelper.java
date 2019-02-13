package com.example.facedetection;

import android.graphics.Bitmap;

import com.microsoft.projectoxford.face.contract.FaceRectangle;

public class ImageHelper {
    public static FaceRectangle calculateFaceRectangle (Bitmap bitmap, FaceRectangle faceRectangle, double faceRectEnlargeRatio) {

        double sideLength = faceRectangle.width * faceRectEnlargeRatio;
        sideLength = Math.min(sideLength, bitmap.getWidth());
        sideLength = Math.min(sideLength, bitmap.getHeight());

        double left = faceRectangle.left - faceRectangle.width * (faceRectEnlargeRatio);
        left = Math.max(left, 0.0);
        left = Math.min(left, bitmap.getWidth() - sideLength);

        double top = faceRectangle.top - faceRectangle.height * (faceRectEnlargeRatio);
        top = Math.max(top, 0.0);
        top = Math.min(top, bitmap.getHeight() - sideLength);

        double shiftTop = faceRectEnlargeRatio - 1.0;
        shiftTop = Math.max(shiftTop, 0.0);
        shiftTop = Math.min(shiftTop, 1.0);
        top = 0.15 * shiftTop * faceRectangle.height;
        top = Math.max(top, 0.0);

        FaceRectangle result = new FaceRectangle();
        result.left = (int) left;
        result.top = (int) top;
        result.width = (int) sideLength;
        result.height = (int) sideLength;
        return result;

    }

    public static Bitmap generateThumbnail (Bitmap originalBitmap, FaceRectangle faceRectangle) {
        FaceRectangle face = calculateFaceRectangle(originalBitmap, faceRectangle, 1.3);
        return Bitmap.createBitmap(originalBitmap, faceRectangle.left, faceRectangle.top, faceRectangle.width, faceRectangle.height);
    }
}

