package com.example.CarShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository

public class CarDBRepository {
    @Autowired
    private DataSource dataSource;

    public List<Car> getCarList() {
                List<Car> cars = new ArrayList<>();
                try (Connection conn = dataSource.getConnection();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("select  BRAND.NAME AS BRANDNAME, CARMODEL.NAME AS MODEL, CARSTOCK.MILES, CARSTOCK.YEAR, CARSTOCK.PRICE, CARSTOCK.DESCRIPTION, CARSTOCK.PICTUREURL, CARSTOCK.SOLD\n" +
                             "FROM CARSTOCK \n" +
                             "JOIN CARMODEL ON CARMODEL.ID = CARSTOCK.CARMODELID\n" +
                             "JOIN BRAND ON BRAND.ID = CARMODEL.BRANDID")) {

                    while (rs.next()){
                        cars.add(rsCar(rs));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return cars;
            }


    private Car rsCar(ResultSet rs) throws SQLException {
        return new Car(
                rs.getString("BRANDNAME"),
                rs.getString("MODEL"),
                rs.getInt("MILES"),
                rs.getInt("YEAR"),
                rs.getInt("PRICE"),
                rs.getString("DESCRIPTION"),
                rs.getString("PICTUREURL"));
        //     rs.getBoolean("SOLD"));
    }

        private List<Car> carList = new ArrayList<>();

        public CarDBRepository()
        {
         //   carList = getCarList();
          //  System.out.println("Number of cars:" + carList.size());
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
            carList = getCarList();
            List<Car> filterList = new ArrayList<>();
            for (Car aCar: carList) {
                if (aCar.isSold() == false)
                    filterList.add(aCar);
            }
            return filterList;
        }

        public Car getCar(int index) {
            List<Car> templist = getCars();
            return templist.get(index);
        }

        public boolean carInStore(String brand) {
            for (Car car : carList) {
                if (car.getBrand() == brand) {
                    return true;
                }
            }
            return false;
        }
        public void replace(int pos, Car car)
        {
            Car loopcar;
            int localpos = 0;
            for (int i=0; i< carList.size(); i++)   {
                loopcar = carList.get(i);
                if (pos == localpos)   {
                    carList.remove(i);
                    break;
                }
                if (loopcar.isSold() == false) {
                    localpos++;
                }
            }
            carList.add(car);
        }
    }

