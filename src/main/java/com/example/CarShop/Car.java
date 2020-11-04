package com.example.CarShop;

public class Car {
    private String brand;
    private int miles;
    private int year;
    private int price;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Car(String brand, int miles, int year, int price) {
        this.brand = brand;
        this.miles = miles;
        this.year = year;
        this.price = price;
    }

    public String getBrand() { return brand;    }

    public int getYear() { return year; }

    public int compareTo(Car car) {
        return (this.price  - car.price);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
