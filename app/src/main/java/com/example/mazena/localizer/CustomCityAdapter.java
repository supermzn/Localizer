package com.example.mazena.localizer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


public class CustomCityAdapter extends ArrayAdapter<String> {
    public CustomCityAdapter(Context context, List<String> cities) {
        super(context, R.layout.custom_city_layout, cities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater cityInflater = LayoutInflater.from(getContext());
        View cityRow = cityInflater.inflate(R.layout.custom_city_layout, parent, false);

        String city = getItem(position);
        TextView cityName = (TextView) cityRow.findViewById(R.id.city_name);
        cityName.setText(city);
        if (position%2 == 0) {
            cityRow.setBackgroundColor(Color.parseColor("#3ECDF1"));
        } else {
            cityRow.setBackgroundColor(Color.parseColor("#3CB9D9"));
        }
        return cityRow;
    }
}
