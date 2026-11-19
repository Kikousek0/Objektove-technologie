package com.example.apka_faktury;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class polozky_faktury {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty id_invoice = new SimpleIntegerProperty();
    private SimpleIntegerProperty amount = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleStringProperty category = new SimpleStringProperty();

    public polozky_faktury(int id, int id_invoice, int amount, String name, double price, String category) {
        this.id.set(id);
        this.id_invoice.set(id_invoice);
        this.amount.set(amount);
        this.name.set(name);
        this.price.set(price);
        this.category.set(category);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty getIdProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId_invoice() {
        return id_invoice.get();
    }

    public SimpleIntegerProperty getId_invoiceProperty() {
        return id_invoice;
    }

    public void setId_invoice(int id_invoice) {
        this.id_invoice.set(id_invoice);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty getAmountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty getPriceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty getCategoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }
}
