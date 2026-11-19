package com.example.apka_faktury;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class AppController {
    @FXML private TableView<faktury> tv1;
    @FXML private TableView<polozky_faktury> tv2;
    @FXML private TableColumn<faktury, Number> col_id;
    @FXML private TableColumn<faktury, String> col_date;
    @FXML private TableColumn<faktury, String> col_status;
    @FXML private TableColumn<polozky_faktury, String> col_name;
    @FXML private TableColumn<polozky_faktury, String> col_category;
    @FXML private TableColumn<polozky_faktury, Number> col_price;
    @FXML private TableColumn<polozky_faktury, Number> col_amount;
    @FXML private Label vysledok;

    private ObservableList<faktury> fakturyData = FXCollections.observableArrayList();
    private ObservableList<polozky_faktury> polozkyData = FXCollections.observableArrayList();

    private final String URL = "jdbc:mysql://localhost/faktury";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private void getFaktury() {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                fakturyData.add(new faktury(rs.getInt("id"),
                        rs.getString("date"), rs.getString("status")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void getPolozky(int invoiceId) {
        try {
            polozkyData.clear();
            Double total = 0.0;
            Connection conn = DriverManager.getConnection(URL,USERNAME, PASSWORD);
            String sql = "SELECT invoice_items.id as id, id_invoice, amount, product.name as name, "+
                    " price, category.name as category FROM invoice_items JOIN invoice ON invoice_items.id_invoice = invoice.id "+
                    "JOIN product ON invoice_items.id_product = product.id JOIN category ON product.category_id = category.id "+
                    "WHERE id_invoice = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,invoiceId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int amount = rs.getInt("amount");
                Double price = rs.getDouble("price");
                total += (amount*price);
                polozky_faktury ii = new polozky_faktury(rs.getInt("id"),
                        rs.getInt("id_invoice"), amount,
                        rs.getString("name"), price, rs.getString("category"));
                polozkyData.add(ii);
            }
            vysledok.setText(String.format("%.2f EUR", total));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void initialize() {
        tv1.setItems(fakturyData);
        col_id.setCellValueFactory(c->c.getValue().idProperty());
        col_date.setCellValueFactory(c->c.getValue().dateProperty());
        col_status.setCellValueFactory(c->c.getValue().statusProperty());

        tv2.setItems(polozkyData);
        col_name.setCellValueFactory(c->c.getValue().getNameProperty());
        col_category.setCellValueFactory(c->c.getValue().getCategoryProperty());
        col_price.setCellValueFactory(c->c.getValue().getPriceProperty());
        col_amount.setCellValueFactory(c->c.getValue().getAmountProperty());

        getFaktury();
        tv1.setOnMouseClicked(e-> {
            if(tv1.getSelectionModel().getSelectedItem() != null) {
                int id = tv1.getSelectionModel().getSelectedItem().idProperty().get();
                getPolozky(id);
            }
        });
    }
}
