package com.example.meditrackr.controllers;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Skryt on Nov 13, 2018
 */

public class ImageController {
    //image
    private Bitmap selectImage;

    public ImageController (){

    }

    /**
     * pass the file path to decode it into bitmap
     * then resize and compress it to desired file size
     * then set it to image view to show it
     *
     * @param filePath the file path of image in this phone
     */
    public Bitmap decodeFile(String filePath) {
        //set max image file size
        int maxSize = 65536;

        // get the original image size
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, option);

        // The new size we want to scale to 500
        final int REQUIRED_SIZE = 500;
        // Find the correct scale value. It should be the power of 2.
        int width_origin = option.outWidth, height_origin = option.outHeight;
        int scale = 1;
        while (true) {
            if (width_origin < REQUIRED_SIZE && height_origin < REQUIRED_SIZE)
                break;
            width_origin /= 2;
            height_origin /= 2;
            scale *= 2;
        }

        // get image with desired size
        BitmapFactory.Options optionSet = new BitmapFactory.Options();
        optionSet.inSampleSize = scale;
        selectImage = BitmapFactory.decodeFile(filePath, optionSet);

        // compress the image to desired file size
        int compressQuality = 100;
        int streamLength = maxSize;

        while (streamLength >= maxSize) {
            compressQuality -= 1;
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            selectImage.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream);

            byte[] bmpPicByteArray = bmpStream.toByteArray();
            streamLength = bmpPicByteArray.length;
        }

        return selectImage;
    }
}