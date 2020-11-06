package com.example.CarShop;

public class Car {
    private String brand;
    private String model;
    private int miles;
    private int year;
    private int price;
    private String description;
    private String pictureUrl;
    private String slug;

    public Car() {
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Car(String brand, String model, int miles, int year, int price, String description, String pictureUrl) {
        this.brand = brand;
        this.model = model;
        this.miles = miles;
        this.year = year;
        this.price = price;
        this.description = description;
        this.pictureUrl = pictureUrl;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public int compareTo(Car car) {
        return (this.price - car.price);
    }

    public int getPrice() {
        return price;
    }

    public int getMiles() {
        return miles;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSlug() {
        return brand + "  " + model + " ,  " + price + " SEK";
    }
}
