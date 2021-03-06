/*--------------------------------------------------------------------------
 * FILE: CustomInfoWindowAdapter.java
 *
 * PURPOSE: Used to render the mini map view for displaying geolocations of
 *          a record.
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
package com.example.meditrackr.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Class that provides the customized rendering of the mini Google Map view.
 *
 * @author Orest Cokan
 * @version 1.0 Nov 18, 2018
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    // Class objects
    private final View mWindow;
    private Context mContext;

    /**
     * Creates a new instance.
     *
     * @author Orest Cokan
     * @version 1.0 Nov 18, 2018
     * @param context       the context for the adapter
     */
    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }


    /**
     * Renders window text.
     *
     * @author Orest Cokan
     * @version 1.0 Nov 18, 2018
     * @param marker       marker for the text position
     * @param view         the view to resize
     */
    private void rendowWindowText(Marker marker, View view){

        // Gets problem title
        String title = marker.getTitle();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);

        if(!title.equals("")){ // If title is not empty then set problem title
            tvTitle.setText(title);
        }

        // Gets the number of records, date, and description of problem
        String snippet = marker.getSnippet();
        TextView tvSnippet = (TextView) view.findViewById(R.id.snippet);

        if(!snippet.equals("")){ // If info is not empty then set the info
            tvSnippet.setText(snippet);
        }
    }

    // Returns the view for the window text
    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }

    // Returns the view for the window text
    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }
}
