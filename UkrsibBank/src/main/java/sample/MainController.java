package sample;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;

public class MainController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button vhod;

    @FXML
    private Button registraciya;

    @FXML
    void initialize() {
        //Переход на окно входа
        vhod.setOnAction(event -> {
            Stage stage = (Stage) vhod.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Vhod.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.setTitle("Vhod");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show(); });
//Переход на окно регистрации
        registraciya.setOnAction(event -> {
            Stage stage = (Stage) registraciya.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registraciya.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace(); }
            stage = new Stage();
            stage.setTitle("Registraciya");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show(); });
    }}