package com.example.epulapp.beersapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.epulapp.beersapplication.Interface.OnPreTaskExecute;
import com.example.epulapp.beersapplication.Interface.OnTaskCompleted;
import com.example.epulapp.beersapplication.Model.Beer;

import java.io.InputStream;

/**
 * Created by Epulapp on 07/12/2017.
 */

// DownloadImage AsyncTask
class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    private OnPreTaskExecute preTask;
    private OnTaskCompleted listener;
    private Beer beer;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.preTask.onPreTaskExecute();
    }

    public DownloadImage(OnPreTaskExecute preTask, OnTaskCompleted listener , Beer beer){
        this.preTask = preTask;
        this.listener = listener;
        this.beer = beer;
    }
    @Override
    protected Bitmap doInBackground(String... URL) {

        String imageURL = URL[0];

        Bitmap bitmap = null;
        try {
            // Download Image from URL
            InputStream input = new java.net.URL(imageURL).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        // Set the bitmap into ImageView
        this.beer.setImg(result);
        this.listener.onTaskCompleted();
    }
}