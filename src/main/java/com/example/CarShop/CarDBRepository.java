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
             ResultSet rs = stmt.executeQuery("select CARSTOCK.ID AS CARID, BRAND.NAME AS BRANDNAME, CARMODEL.NAME AS MODEL, CARSTOCK.MILES, CARSTOCK.YEAR, CARSTOCK.PRICE, CARSTOCK.DESCRIPTION, CARSTOCK.PICTUREURL, CARSTOCK.SOLD\n" +
                     "FROM CARSTOCK \n" +
                     "JOIN CARMODEL ON CARMODEL.ID = CARSTOCK.CARMODELID\n" +
                     "JOIN BRAND ON BRAND.ID = CARMODEL.BRANDID")) {

            while (rs.next()) {
                cars.add(rsCar(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    private Car rsCar(ResultSet rs) throws SQLException {
        return new Car(
                rs.getInt("CARID"),
                rs.getString("BRANDNAME"),
                rs.getString("MODEL"),
                rs.getInt("MILES"),
                rs.getInt("YEAR"),
                rs.getInt("PRICE"),
                rs.getString("DESCRIPTION"),
                rs.getString("PICTUREURL"),
                rs.getBoolean("SOLD"));
    }

    private List<Car> carList = new ArrayList<>();

    public CarDBRepository() {
        //   carList = getCarList();
        //  System.out.println("Number of cars:" + carList.size());
    }

    public void addCar(Car car) {
        int brandID = getBrand(car.getBrand());
        int modelID = getModel(car.getModel(), brandID);

        Connection conn = null;

        String SqlStatement = "INSERT INTO CARSTOCK (CARMODELID, MILES, YEAR, PRICE, DESCRIPTION, PICTUREURL, SOLD)" +
                " VALUES(" + modelID + "," + car.getMiles() + "," + car.getYear() + "," + car.getPrice() + ", '" +
                car.getDescription() + "', '" + car.getPictureUrl() + "', " + false + ")";

        try {
            conn = dataSource.getConnection();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SqlStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void updateCar(Car car) {
        int brandID = getBrand(car.getBrand());
        int modelID = getModel(car.getModel(), brandID);

        Connection conn = null;

        String SqlStatement = "UPDATE CARSTOCK " +
                " SET CARMODELID ='" + modelID + "', MILES = " + car.getMiles() + ", YEAR = " + car.getYear() +
                ", PRICE = " + car.getPrice() + ", DESCRIPTION = '" +
                car.getDescription() + "', PICTUREURL = '" + car.getPictureUrl() + "', SOLD = " + car.isSold() +
                " WHERE ID = " + car.getId();
        try {
            conn = dataSource.getConnection();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SqlStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void removeCar(int carId) {

        Connection conn = null;

        String SqlStatement = "DELETE FROM CARSTOCK WHERE ID = " + carId;

        try {
            conn = dataSource.getConnection();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SqlStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public int getBrand(String brandName) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select ID from BRAND WHERE NAME = '" + brandName + "'")) {

            if (rs.next()) {
                return (rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getModel(String modelName, int brandID) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select ID from CARMODEL WHERE CARMODEL.BRANDID = '" + brandID + "' AND NAME = '" + modelName + "'")) {

            if (rs.next()) {
                return (rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
        for (Car aCar : carList) {
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

    public void replace(Car car) {
        if (car.getId() > 0) {
            removeCar(car.getId());
        }
        addCar(car);
    }
}

