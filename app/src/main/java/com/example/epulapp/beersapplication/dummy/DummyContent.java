package com.example.epulapp.beersapplication.dummy;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.epulapp.beersapplication.BiereAPI;
import com.example.epulapp.beersapplication.Model.Beer;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DummyContent {


    public static final ArrayList<Beer> ITEMS = new ArrayList<Beer>();

    public static final Map<String, Beer> ITEM_MAP = new HashMap<String, Beer>();

    public static Boolean LOADED = false;

    static {
    }

}
