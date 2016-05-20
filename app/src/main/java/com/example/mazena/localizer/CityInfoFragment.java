package com.example.mazena.localizer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CityInfoFragment extends Fragment {

    private static TextView placeText;
    private static TextView communityText;
    private static TextView countyText;
    private static TextView provinceText;
    private static TextView counryText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_info, container, false);
        placeText = (TextView) view.findViewById(R.id.place);
        communityText = (TextView) view.findViewById(R.id.community);
        countyText = (TextView) view.findViewById(R.id.county);
        provinceText = (TextView) view.findViewById(R.id.province);
        counryText = (TextView) view.findViewById(R.id.country);

        return view;
    }

    public void showCityInfo(String place, String community, String county, String province, String country) {
        placeText.setText("Place: " + place);
        communityText.setText("Community: " + community);
        countyText.setText("County: " + county);
        provinceText.setText("Province: " + province);
        counryText.setText("Country: " + country);
    }


}
