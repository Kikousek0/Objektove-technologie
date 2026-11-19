package com.example.apka_filmy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class AppController {
    @FXML private TableView<filmy> tfilm;
    @FXML private TableView<genres> tgenre;
    @FXML private TableColumn<filmy, String> col_title;
    @FXML private TableColumn<filmy, Number> col_length;
    @FXML private TableColumn<filmy, Number> col_year;
    @FXML private TableColumn<filmy, String> col_link;
    @FXML private TableColumn<genres, String> col_genre;
    @FXML private Label text;

    private ObservableList<filmy> filmyData = FXCollections.observableArrayList();
    private ObservableList<genres> genreData = FXCollections.observableArrayList();

    private final String URL = "jdbc:mysql://localhost/films";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private void getMovies() {
        filmyData.clear();
        String sql = "SELECT * FROM movie";
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                filmyData.add(new filmy(rs.getInt("id"), rs.getString("title"),
                        rs.getInt("length"), rs.getInt("year"), rs.getString("link")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void getGenresForMovies(filmy selectedMovie) {
        genreData.clear();
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(" SELECT g.id, g.name FROM genre g JOIN movie_genres mg ON g.id = mg.genre_id WHERE mg.movie_id = ? ");
            ps.setInt(1, selectedMovie.getId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    genreData.add(new genres(rs.getInt("id"),rs.getString("name")));
                }
                int pocetZanrov = genreData.size();
            text.setText(selectedMovie.getTitle() + " - počet žánrov: " + pocetZanrov);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void initialize() {
        tfilm.setItems(filmyData);
        col_title.setCellValueFactory(c->c.getValue().getTitleProperty());
        col_length.setCellValueFactory(c->c.getValue().getLengthProperty());
        col_year.setCellValueFactory(c->c.getValue().getYearProperty());
        col_link.setCellValueFactory(c->c.getValue().getLinkProperty());

        tgenre.setItems(genreData);
        col_genre.setCellValueFactory(c->c.getValue().getNameProperty());

        getMovies();

        tfilm.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, selectedMovie)-> {
            if(selectedMovie != null) {
                getGenresForMovies(selectedMovie);
            }
        });
    }
}
