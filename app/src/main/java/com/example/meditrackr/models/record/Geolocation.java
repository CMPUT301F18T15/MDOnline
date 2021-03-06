/*--------------------------------------------------------------------------
 * FILE: Geolocation.java
 *
 * PURPOSE: Stores information related to geolocation for records.
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

package com.example.meditrackr.models.record;

//imports
import java.io.Serializable;

/**
 * Geolocation: represents a geolocation point on a map by tracking latitude,
 * longitude, and a string address name of the geolocation for a particular record.
 *
 * Getters and setters can be used to retrieve or modify any of the geolocation
 * attributes.
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 8, 2018.
 */

// A Geolocation class that holds all methods pertaining to Geolocation
public class Geolocation implements Serializable {

    // Initialize class variables
    private double longitude;
    private double latitude;
    private String distance;
    private String address;


    /**
     * Constructs a new Geolocation object with latitude, longitude, and address.
     *
     * @author  Orest Cokan
     * @param latitude  latitude coordinate
     * @param longitude longitude coordinate
     * @param address   address name
     */
    // Constructor
    public Geolocation(double latitude, double longitude, String address){
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * Gets the longituede coordinate.
     * @author  Orest Cokan
     * @return longitude, the double integer of the longitude location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Takes a new double value and sets the longitude coordinate.
     * @author  Orest Cokan
     * @param longitude the integer of the longitude location
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the latitude coordinate.
     * @author  Orest Cokan
     * @return latitude, the double integer of the latitude location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Takes a new double value and sets the latitude coordinate.
     * @author  Orest Cokan
     * @param latitude the integer of the latitude location
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the address name.
     * @author  Orest Cokan
     * @return address, the name of the address location
     */
    public String getAddress() {
        return address;
    }

    /**
     * Takes a new string value and sets the address.
     * @author  Orest Cokan
     * @param address the name of the address location
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the distance of the address
     * @author  Orest Cokan
     * @return distance the string value of distance from the users location
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Takes a new string value and sets the distance.
     * @author  Orest Cokan
     * @param distance the distance of the location
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

}


