package com.example.mazena.localizer;


import android.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class CityListFragment extends Fragment {

    private List<String> cities = new ArrayList<String>();
    CityListFragmentConnector activityConnector;

    public interface CityListFragmentConnector {
        public void showSelectedCity(String city);
    }

    public List<String> getCities() {
        return cities;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.city_list, container, false);
        final ArrayAdapter citiesListAdapter = new CustomCityAdapter(getActivity().getApplicationContext(), cities);
        ListView cityList = (ListView) view.findViewById(R.id.city_list);
        cityList.setAdapter(citiesListAdapter);

        ImageButton addButton = (ImageButton) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButtnClicked(view);
                citiesListAdapter.notifyDataSetChanged();
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                activityConnector = (CityListFragmentConnector) getActivity();
                activityConnector.showSelectedCity((String) adapterView.getItemAtPosition(i).toString());
            }
        });
        return view;
    }
    public void addButtnClicked(View vi) {
        EditText cityInput = (EditText) getView().findViewById(R.id.city_input);
        getCities().add(cityInput.getText().toString());
        cityInput.setText("");




    }



}
