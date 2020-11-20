package com.example.CarShop;

import java.util.Date;

public class Purchase {
    private int id;
    private int customerid;
    private int carstockid;
    private Date purchasedate;
    private int paidamount;
    private int finalprice;
    private Date deliverydate;
    private boolean delivered;

    public Purchase() { }
    
    public Purchase(int id, int customerid, int carstockid, Date purchasedate, int paidamount, int finalprice, Date deliverydate, boolean delivered) {
        this.id = id;
        this.customerid = customerid;
        this.carstockid = carstockid;
        this.purchasedate = purchasedate;
        this.paidamount = paidamount;
        this.finalprice = finalprice;
        this.deliverydate = deliverydate;
        this.delivered = delivered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getCarstockid() {
        return carstockid;
    }

    public void setCarstockid(int carstockid) {
        this.carstockid = carstockid;
    }

    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    public int getPaidamount() {
        return paidamount;
    }

    public void setPaidamount(int paidamount) {
        this.paidamount = paidamount;
    }

    public int getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(int finalprice) {
        this.finalprice = finalprice;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public String formatDate(Date mydate) { return "'2020-11-20'"; }

}
