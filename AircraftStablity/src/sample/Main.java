package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("glavnaya.fxml"));
        stage.setTitle("Aircraft Stability");
        stage.getIcons().add(new Image("sample/image/иконка.jpg"));
        stage.setScene(new Scene(root, 361, 548));
        stage.setResizable(false);
        stage.show(); }
    public static void main(String[] args) {
        launch(args);
    }
}
