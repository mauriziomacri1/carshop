/*
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
public class PurchaseDBRepository {
    @Autowired
    private DataSource dataSource;

    public List<Purchase> getPurchaseList() {
        List<Purchase> purchase = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * FROM PURCHASE")) {
            while (rs.next()) {
                purchase.add(rsPurchase(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchase;
    }
  */
/*  private Purchase rsPurchase(ResultSet rs) throws SQLException {
        return new Purchase(
                rs.getInt("ID"),
                rs.getString("CUSTOMERID"),
                rs.getInt("CARSTOCKID"),
                rs.getDate("PURCHASEDATE"),
                rs.getString("PAIDAMOUNT"),
                rs.getString("FINALPRICE"),
                rs.getString("DELIVERYDATE"),
                rs.getString("DELIVERED"));
    }*//*

}

*/
