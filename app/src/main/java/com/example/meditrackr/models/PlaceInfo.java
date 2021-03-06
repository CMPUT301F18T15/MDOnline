/*--------------------------------------------------------------------------
 * FILE: PlaceInfo.java
 *
 * PURPOSE:
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
package com.example.meditrackr.models;



import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

/**
 *
 * PlaceInfo: Used for geolocation map activity. Tracks various information
 * about a specific location on a world map.
 *
 * @author Orest Cokan
 * @version 1.0 Nov, 16 2018
 */
public class PlaceInfo {
    // Attributes
    private String name;
    private String address;
    private String phoneNumber;
    private String id;
    private Uri websiteUri;
    private LatLng latlng;
    private float rating;
    private String attributions;

    /**
     * creates variables for class to use
     * @author Orest Cokan
     * @param name              name of the user
     * @param address           address of location
     * @param phoneNumber       phone number of user
     * @param id                id of the problem
     * @param websiteUri        websites url
     * @param latlng            lat and longitude values
     * @param rating            a rating of the problem
     * @param attributions
     */
    public PlaceInfo(String name, String address, String phoneNumber, String id, Uri websiteUri,
                     LatLng latlng, float rating, String attributions) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.websiteUri = websiteUri;
        this.latlng = latlng;
        this.rating = rating;
        this.attributions = attributions;
    }


    public PlaceInfo() {

    }


    // Calls to PlaceInfo methods
    /**
     * gets the name
     *
     * @author Orest Cokan
     * @return      the name
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setWebsiteUri(Uri websiteUri) {
        this.websiteUri = websiteUri;
    }


    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }


    public void setRating(float rating) {
        this.rating = rating;
    }


    /**
     * Converts the object to a string representation.
     *
     * @author  Orest Cokan
     * @return  returns a string representation of the object
     */
    @Override
    public String toString() {
        return "PlaceInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id='" + id + '\'' +
                ", websiteUri=" + websiteUri +
                ", latlng=" + latlng +
                ", rating=" + rating +
                ", attributions='" + attributions + '\'' +
                '}';
    }
}