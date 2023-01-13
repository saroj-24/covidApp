package com.example.covidupdate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface APIinterface {

     //String BASE_URL="https://corona-virus-world-and-india-data.p.rapidapi.com/";
     String BASE_URL="https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/npm-covid-data/";
    //String BASE_URL="https://covid-193.p.rapidapi.com/";

    @Headers(
             {
                     "Accept: application/json",
                     "X-RapidAPI-Key: e9d964d7d2msh6ccec09db524705p150fd9jsna1dde369374b",
                     "X-RapidAPI-Host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com"
             }
     )
     @GET("countries")
    Call<List<ModelClass>> getcountryclass();

}
