package com.example.CarShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

  private Purchase rsPurchase(ResultSet rs) throws SQLException {
        return new Purchase(
                rs.getInt("ID"),
                rs.getInt("CUSTOMERID"),
                rs.getInt("CARSTOCKID"),
                rs.getDate("PURCHASEDATE"),
                rs.getInt("PAIDAMOUNT"),
                rs.getInt("FINALPRICE"),
                rs.getDate("DELIVERYDATE"),
                rs.getBoolean("DELIVERED"));
    }
    public void addPurchase(Purchase purchase) {

        Connection conn = null;

        String SqlStatement = "INSERT INTO PURCHASE (CUSTOMERID, CARSTOCKID, PURCHASEDATE, PAIDAMOUNT, FINALPRICE, DELIVERYDATE, DELIVERED)" +
                " VALUES(" + purchase.getCustomerid() + "," + purchase.getCarstockid() + "," + purchase.formatDate(purchase.getPurchasedate()) + "," + purchase.getPaidamount() + ", " +
                purchase.getFinalprice() + ", " + purchase.formatDate(purchase.getDeliverydate()) + ", " + purchase.isDelivered() + ")";

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
}

