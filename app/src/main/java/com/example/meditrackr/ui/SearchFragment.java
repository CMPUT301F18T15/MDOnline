/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */
package com.example.meditrackr.ui;

//imports
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


import com.example.meditrackr.R;
import com.example.meditrackr.adapters.SearchAdapter;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.utils.CustomFilter;
import com.example.meditrackr.utils.ImageRecognition;
import com.example.meditrackr.utils.ParseText;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import br.com.mauker.materialsearchview.MaterialSearchView;
import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;


/**
 * Crated by Skryt on Nov 18, 2018
 */

// Class creates search fragment
public class SearchFragment extends Fragment {
    // Initialize class objects
    private Profile profile = LazyLoadingManager.getProfile();
    private ArrayList<CustomFilter> customFilter;
    private Patient patient;
    private RecyclerView rv;
    private int selected;
    private RadioButton regularButton, geoLocationButton, bodyLocationButton;

    // Indicator/Request code
    private static final int PLACE_PICKER_REQUEST = 2;

    // Create new fragment instance
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    // Creates search fragment view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_search, container, false);

        // Initialize ui attributes
        SearchView mSearch = (SearchView) rootView.findViewById(R.id.mSearch);
        ImageView icon = mSearch.findViewById(android.support.v7.appcompat.R.id.search_button);
        regularButton = (RadioButton) rootView.findViewById(R.id.regular_search_button);
        geoLocationButton = (RadioButton) rootView.findViewById(R.id.geolocation_search_button);
        bodyLocationButton = (RadioButton) rootView.findViewById(R.id.bodylocation_search_button);
        regularButton.setChecked(true);
        // UI beautify
        icon.setColorFilter(Color.BLACK);
        mSearch.setIconified(false);
        mSearch.setClickable(true);
        rv = rootView.findViewById(R.id.myRecycler);

        // Set view properties
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());


        if(!profile.getisCareProvider()){
            patient = (Patient) profile;
        }

        onCreate();


        // Sets a listener for user text input
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                customFilter = new ArrayList<>();
                if(!profile.getisCareProvider()) {
                    customFilter = ParseText.parseRecordProblem(query, patient, customFilter);
                }else {
                    ArrayList<Patient> patients = LazyLoadingManager.getPatients();
                    for(int i = 0; i < patients.size(); i++){
                        customFilter = ParseText.parseRecordProblem(query, patients.get(i), customFilter);
                    }
                }
                SearchAdapter adapter = new SearchAdapter(getActivity(), getContext(), customFilter, selected);
                rv.setAdapter(adapter);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customFilter = new ArrayList<>();
                if(!profile.getisCareProvider()) {
                    customFilter = ParseText.parseRecordProblem(newText, patient, customFilter);
                }else {
                    ArrayList<Patient> patients = LazyLoadingManager.getPatients();
                    for(int i = 0; i < patients.size(); i++){
                        customFilter = ParseText.parseRecordProblem(newText, patients.get(i), customFilter);
                    }
                }


                SearchAdapter adapter = new SearchAdapter(getActivity(), getContext(), customFilter, selected);
                rv.setAdapter(adapter);

                return false;
            }
        });


        // Onclick listener for one of three parameters to search for
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regularButton.setChecked(false);
                geoLocationButton.setChecked(false);
                bodyLocationButton.setChecked(false);
                if(v == regularButton){
                    regularButton.toggle();
                    selected = 1;
                }else if (v == geoLocationButton){
                    geoLocationButton.toggle();
                    selected = 2;
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    try{
                        startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                    }
                    catch (Exception e){
                        Log.d("Error","Place Picker Error");
                    }
                } else {
                    bodyLocationButton.toggle();
                    selected = 3;
                }
            }
        };
        regularButton.setOnClickListener(listener);
        geoLocationButton.setOnClickListener(listener);
        bodyLocationButton.setOnClickListener(listener);

        return rootView;
    }



    public void onCreate(){
        customFilter = new ArrayList<>();
        if(!profile.getisCareProvider()) {
            customFilter = ParseText.parseRecordProblem("", patient, customFilter);
        }else {
            ArrayList<Patient> patients = LazyLoadingManager.getPatients();
            for(int i = 0; i < patients.size(); i++){
                customFilter = ParseText.parseRecordProblem("", patients.get(i),customFilter);
            }
        }
        SearchAdapter adapter = new SearchAdapter(getActivity(), getContext(), customFilter, selected);
        rv.setAdapter(adapter);
    }

}




