/*--------------------------------------------------------------------------
 * FILE: MapFragment.java
 *
 * PURPOSE: For showing a geolocation inside the record view.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/

package com.example.meditrackr.ui.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;

/**
 * @author  Orest Cokan
 * @version 1.0 Nov 12, 2018.
 */

// Class creates Map Fragment for patients
public class MapFragment extends Fragment {
    // Create new MapFragment object
    public static MapFragment newInstance(){
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    // Creates view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_map, container, false);

        return rootView;
    }

}