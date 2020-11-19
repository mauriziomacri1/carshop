package com.example.CarShop;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private String address2;
    private String zipcode;
    private String city;
    private String country;

    public Customer(int id, String name, String email, String address, String address2, String zipcode, String city, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.address2 = address2;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
