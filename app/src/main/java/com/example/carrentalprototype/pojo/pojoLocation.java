package com.example.carrentalprototype.pojo;

public class pojoLocation {

    public   int locationId;
    public String building;
    public String street;
    public String postalCode;
    public String city;
    public String provience;
    public String country;
    public String longitude;
    public String lettitude;
    public String title;


    public pojoLocation(int locationId, String title) {
        this.locationId = locationId;
        this.title = title;
    }


    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvience() {
        return provience;
    }

    public void setProvience(String provience) {
        this.provience = provience;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLettitude() {
        return lettitude;
    }

    public void setLettitude(String lettitude) {
        this.lettitude = lettitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
