package com.example.CarShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarRepository {

        List<Car> carList = new ArrayList<>();
        public CarRepository()
        {
            addCar(new Car("SAAB", 10000, 1999, 7000));
            addCar(new Car("Volvo", 8000, 2012, 95000));
            addCar(new Car("Koeningsegg", 2000, 2018, 700000));
            addCar(new Car("BMW", 10000, 2001, 80000));
            addCar(new Car("Audi", 10000, 1999, 7000));
        }
        public void addCar(Car car) {
            carList.add(car);
        }

        public int getNumberOfCarsInStore() {
            return carList.size();
        }

        public void sortCarsByPrice() {
            Collections.sort(carList, Car::compareTo);
        }

        public List<Car> getCars() {
            return carList;
        }
    }

