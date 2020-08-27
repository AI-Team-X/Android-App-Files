package com.teamx.bottomnav;

public class DataPoint {
    int NumberOfBaskets, Price;
    String NameOfCustomer, Email,CropToSell;

    public DataPoint() {
    }

    public DataPoint(int numberOfBaskets, int price) {
        NumberOfBaskets = numberOfBaskets;
        Price = price;
    }

    public DataPoint(int numberOfBaskets, int price, String nameOfCustomer, String email, String cropToSell) {
        NumberOfBaskets = numberOfBaskets;
        Price = price;
        NameOfCustomer = nameOfCustomer;
        Email = email;
        CropToSell = cropToSell;
    }

    public int getNumberOfBaskets() {
        return NumberOfBaskets;
    }

    public void setNumberOfBaskets(int numberOfBaskets) {
        NumberOfBaskets = numberOfBaskets;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getNameOfCustomer() {
        return NameOfCustomer;
    }

    public void setNameOfCustomer(String nameOfCustomer) {
        NameOfCustomer = nameOfCustomer;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCropToSell() {
        return CropToSell;
    }

    public void setCropToSell(String cropToSell) {
        CropToSell = cropToSell;
    }
}
