package com.example.CarShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDBRepository {
    @Autowired
    private DataSource dataSource;

    public List<Customer> getCustomerList() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * FROM CUSTOMER")) {
            while (rs.next()) {
                customers.add(rsCustomer(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private Customer rsCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("ID"),
                rs.getString("NAME"),
                rs.getString("EMAIL"),
                rs.getString("ADDRESS"),
                rs.getString("ADDRESS2"),
                rs.getString("ZIPCODE"),
                rs.getString("CITY"),
                rs.getString("COUNTRY"));
    }
}
