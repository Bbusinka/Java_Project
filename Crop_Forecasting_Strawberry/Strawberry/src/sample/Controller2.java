package sample;


import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Berries.Druk;
import sample.Berries.Save;
import sample.Berries.Strawberrys;

public class Controller2 {
    private HashMap<Integer,Object> hashMap ;
    String id;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField rez_met;

    @FXML
    private TextField rez_tip;

    @FXML
    private Button button_druk;

    @FXML
    private Button button_bek;

    @FXML
    private Button button_help;

    @FXML
    private TextField texe_vol1_rez;

    @FXML
    private TextField texe_vol2_rez;

    @FXML
    private TextField texe_vol3_rez;

    @FXML
    private TextField text_tem1_rez;

    @FXML
    private TextField text_tem2_rez;

    @FXML
    private TextField rez_opul;

    @FXML
    private TextField text_vrojai_rez;

    @FXML
    private ListView<String> list_ID;

    @FXML
    void initialize() {
        StartS();

        button_bek.setOnAction(event -> { //оброботчик возврата
            //Закрить форму
            Stage stage = (Stage) button_bek.getScene().getWindow();
            stage.close();
//------------------------------
            //создаём новую форму
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //------
            //запускаем
            stage = new Stage();
            stage.setTitle("Курсовий проект Власенко Євгена Віталійовича");
            stage.setScene(new Scene(root, 700, 500));
            stage.show();

        });
        button_druk.setOnAction(event -> { //друк
            Druk druk = new Druk(id,rez_met.getText(),
            rez_tip.getText(),
            texe_vol1_rez.getText(),
            texe_vol2_rez.getText(),
            texe_vol3_rez.getText(),
            text_tem1_rez.getText(),
            text_tem2_rez.getText(),
            rez_opul.getText(),
            text_vrojai_rez.getText());

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Повідомлення");
            alert1.setHeaderText("Файл сформовано для друку");
            alert1.setContentText("Файл знаходиться у папці з програмою");
            alert1.showAndWait();

        });
        button_help.setOnAction(event -> { //посилання на інтернет ресурс

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://mfc04.ru/klubnika-v-teplitse-urozhajnost/"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

        list_ID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {//обробка ліста
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                //System.out.println("Selected item: " + newValue);
               Strawberrys strawberrys = (Strawberrys) hashMap.get(Integer.parseInt(newValue));
               id = newValue;
               rez_met.setText(strawberrys.getVid());
                rez_tip.setText(strawberrys.getTip());
                texe_vol1_rez.setText(strawberrys.getV1());
                texe_vol2_rez.setText(strawberrys.getV2());
                texe_vol3_rez.setText(strawberrys.getV3());
                text_tem1_rez.setText(strawberrys.getT1());
                text_tem2_rez.setText(strawberrys.getT2());
                rez_opul.setText(strawberrys.getOpl());
                text_vrojai_rez.setText(strawberrys.getRez());
               // System.out.println("1: " + strawberrys.getVid());
            }
        });


    }
    public void StartS (){// стартовий метод

        if (new File("save.ser").exists())
        {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream("save.ser");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Save save = null;
            try {
                save = (Save) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
          //  HashMap<Integer,Object>
                    hashMap = save.getHashMap();

            ArrayList<String> arrayList = new ArrayList<>();
            Set<Integer> keys = hashMap.keySet();
            for (Integer i:
                keys ) {
                arrayList.add(Integer.toString(i));
            }

            ObservableList<String> langs = FXCollections.observableArrayList(arrayList);
            list_ID.setItems(langs);
//----------------------------------

        }else
        {   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("повідомлення");
            alert.setHeaderText(null);
            alert.setContentText("файл не існує");
            alert.showAndWait();
        }}}
