package com.example.mazena.localizer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements CityListFragment.CityListFragmentConnector{

    private Toolbar myToolbar;
    private CityListFragment listFragment = new CityListFragment();
    private CityInfoFragment infoFragment = new CityInfoFragment();
    private FragmentManager fragmentManager = getFragmentManager();
    private final String API_URL = "http://maps.googleapis.com/maps/api/geocode/";

    @Override
    public void showSelectedCity(String city) {

        showInfoFragment();

        prepareResponse(city).enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                if (response.body() != null && response.body().getStatus().equals("OK")){
                    retrieveData(response);
                } else {
                    placeNotFound("No place with this name");
                }
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {
                placeNotFound("Connection problem");
            }
        });
    }

    private Call<CityModel> prepareResponse(String city) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        IApiRequest iar = retrofit.create(IApiRequest.class);
        Call<CityModel> parsObj = iar.getCity(city);
        return parsObj;
    }

    private void showInfoFragment() {
        final TextView toolbarTop = (TextView) findViewById(R.id.back_label);
        toolbarTop.setText("Back");
        toolbarTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSupportActionBar(myToolbar);
                getSupportActionBar().setTitle(R.string.Localize);
                toolbarTop.setText("");
                
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, listFragment, "CityList");
                fragmentTransaction.commit();
            }
        });
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, infoFragment, "CityInfo");
        fragmentTransaction.commit();

    }

    private void retrieveData(Response<CityModel> response) {
        String place = response.body().getResults().get(0).getAddress_components().get(0).getLong_name();
        String community = response.body().getResults().get(0).getAddress_components().get(1).getLong_name();
        String county = response.body().getResults().get(0).getAddress_components().get(2).getLong_name();
        String province = response.body().getResults().get(0).getAddress_components().get(3).getLong_name();
        String country = response.body().getResults().get(0).getAddress_components().get(4).getLong_name();

        infoFragment.showCityInfo(place, community, county, province, country);
    }

    private void placeNotFound(String alert) {
        Toast toast = Toast.makeText(getApplication(), alert, Toast.LENGTH_LONG);
        toast.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.Localize);
        getSupportActionBar().setIcon(R.drawable.ic_toolbar);



        if (getFragmentManager().findFragmentByTag("CityList") == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, listFragment, "CityList");
            fragmentTransaction.commit();
        }


    }
}
