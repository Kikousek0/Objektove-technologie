package com.example.apka_filmy;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class filmy {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleIntegerProperty length = new SimpleIntegerProperty();
    private SimpleIntegerProperty year = new SimpleIntegerProperty();
    private SimpleStringProperty link = new SimpleStringProperty();

    public filmy(int id, String title, int length, int year, String link) {
        this.id.set(id);
        this.title.set(title);
        this.length.set(length);
        this.year.set(year);
        this.link.set(link);
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

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty getTitleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getLength() {
        return length.get();
    }

    public SimpleIntegerProperty getLengthProperty() {
        return length;
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty getYearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public String getLink() {
        return link.get();
    }

    public SimpleStringProperty getLinkProperty() {
        return link;
    }

    public void setLink(String link) {
        this.link.set(link);
    }
}
