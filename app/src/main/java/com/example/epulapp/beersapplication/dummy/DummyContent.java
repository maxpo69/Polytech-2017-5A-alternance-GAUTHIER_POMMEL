package com.example.epulapp.beersapplication.dummy;

import android.util.Log;

import com.example.epulapp.beersapplication.BiereAPI;
import com.example.epulapp.beersapplication.Model.Beer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DummyContent {


    public static final ArrayList<Beer> ITEMS = new ArrayList<Beer>();

    public static final Map<String, Beer> ITEM_MAP = new HashMap<String, Beer>();

    private static final int COUNT = 25;
    private static final String BASE_URL = "https://api.punkapi.com/v2/";

    static {

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BiereAPI beerAPI = retrofit.create(BiereAPI.class);
        Call<ArrayList<Beer>> call = beerAPI.getBeers();

        call.enqueue(new Callback<ArrayList<Beer>>() {
             @Override
             public void onResponse(Call<ArrayList<Beer>> call, Response<ArrayList<Beer>> response) {
                 Log.d("response : ",response.toString());
                 try {
                     ArrayList<Beer> beers = response.body();
                     // Add some sample items.
                     for (Beer biere: beers) {
                         Log.d("Biere : ",biere.toString());
                         ITEMS.add(biere);
                         ITEM_MAP.put(String.valueOf(biere.getId()), biere);
                     }

                 } catch (Error e){
                     Log.e("E : ","error "+e);
                 }
             }

             @Override
             public void onFailure(Call<ArrayList<Beer>> call, Throwable t) {
                Log.e("E : ","error "+t.getMessage());
             }
         });
    }

}
