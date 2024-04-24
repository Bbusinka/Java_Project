package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML
    private ImageView zastavka;

    @FXML
    private Button prodolzhit;

    @FXML
    private Label text;

    @FXML
    private Label text2;

    @FXML
    private Label text3;

    @FXML
    void  initialize() {

        prodolzhit.setOnAction(event -> {
            Stage stage = (Stage) prodolzhit.getScene().getWindow();
            stage.close();
            //Переход на главную страницу
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("system.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace(); }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Aircraft Stability");
            stage.getIcons().add(new Image("sample/image/иконка.jpg"));
            stage.setScene(new Scene(root,900,619));
            stage.setResizable(false);
            stage.show();
        }); }}
