package com.example.CarShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
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

    public int addCustomer(Customer customer) {

        int generatedId = 0;
        Connection conn = null;

        String SqlStatement = "INSERT INTO CUSTOMER (NAME, EMAIL, ADDRESS, ADDRESS2, ZIPCODE, CITY, COUNTRY)" +
                " VALUES('" +customer.getName() + "','" + customer.getEmail() + "','" + customer.getAddress() + "','" + customer.getAddress2() +"','" +
                customer.getZipcode() + "','" + customer.getCity() + "','" + customer.getCountry()+ "')";

        try {
            conn = dataSource.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO CUSTOMER (NAME, EMAIL, ADDRESS, ADDRESS2, ZIPCODE, CITY, COUNTRY)" +
                    " VALUES('" +customer.getName() + "','" + customer.getEmail() + "','" + customer.getAddress() + "','" + customer.getAddress2() +"','" +
                    customer.getZipcode() + "','" + customer.getCity() + "','" + customer.getCountry()+ "')", Statement.RETURN_GENERATED_KEYS);
           // Statement stmt = conn.createStatement();
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return generatedId;
    }

    public void removeCustomer(int customerId) {

        Connection conn = null;

        String SqlStatement = "DELETE FROM CUSTOMER WHERE ID = " + customerId;

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

    public void replace(Customer customer) {
        if (customer.getId() > 0) {
            removeCustomer(customer.getId());
        }
        addCustomer(customer);
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
