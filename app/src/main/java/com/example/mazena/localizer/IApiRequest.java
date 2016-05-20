package com.example.mazena.localizer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiRequest {

    @GET("json?&sensor=true")
    Call<CityModel> getCity(@Query("address") String id);

}
