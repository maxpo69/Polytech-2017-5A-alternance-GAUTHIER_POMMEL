package com.example.epulapp.beersapplication;

import com.example.epulapp.beersapplication.Model.Beer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Epulapp on 05/12/2017.
 */

public interface BiereAPI {

    @Headers("Content-Type: application/json")
    @GET("beers")
    Call<ArrayList<Beer>> getBeers();
}
