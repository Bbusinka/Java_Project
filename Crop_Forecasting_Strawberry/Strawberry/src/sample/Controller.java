package sample;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Berries.Grunt;
import sample.Berries.Save;
import sample.Berries.Strawberrys;
import sample.Berries.Urojai;

public class Controller {
    public   String chek1;
   public String chek2;
    private HashMap<Integer,Object> hashMap ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton chek_grynt;

    @FXML
    private ToggleGroup group1;

    @FXML
    private RadioButton chek_tep;

    @FXML
    private ComboBox<String> chek_vubor;

    @FXML
    private TextField text_id;

    @FXML
    private Button button_rozchot;

    @FXML
    private Button button_save;

    @FXML
    private Button button_null;

    @FXML
    private Button button_rezult;

    @FXML
    private TextField texe_vol1;

    @FXML
    private TextField texe_vol2;

    @FXML
    private TextField texe_vol3;

    @FXML
    private TextField text_tem1;

    @FXML
    private TextField text_tem2;

    @FXML
    private RadioButton chek_op1;

    @FXML
    private ToggleGroup group2;

    @FXML
    private RadioButton chek_op2;

    @FXML
    private TextField text_vrojai;

    @FXML
    void initialize() {
        button_rezult.setOnAction(event -> { //оброботчик нажатия на кнопку
            //Закрить форму
            Stage stage = (Stage) button_rezult.getScene().getWindow();
            stage.close();
//------------------------------
            //создаём новую форму
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //------
            //запускаем
            stage = new Stage();
            stage.setTitle("Результати");
            stage.setScene(new Scene(root, 700, 500));
            stage.show();

        });
        chek_grynt.setOnAction(event -> { //не тепличний спосіб
            chek_vubor.getItems().clear();
          chek_vubor.setValue("Класичним способом");
            chek_vubor.getItems().addAll("Класичним способом");
            Grunt grunt =new Grunt();
            texe_vol1.setText(Integer.toString(grunt.getV1()));
            texe_vol2.setText(Integer.toString(grunt.getV2()));
            texe_vol3.setText(Integer.toString(grunt.getV3()));
            text_tem1.setText(Integer.toString(grunt.getT1()));
            text_tem2.setText(Integer.toString(grunt.getT2()));
            text_vrojai.setText(Integer.toString(grunt.getRezult()));
            chek_op1.setSelected(true);
            chek1 =chek_grynt.getText();
            chek2 ="Класичне";
        });

        chek_tep.setOnAction(event -> { // тепличний спосіб
            chek_vubor.getItems().clear();
            chek_vubor.setValue("Класичним способом");
            chek_vubor.getItems().addAll("Російським способом","Посадка в мішки","Голанським способом","Ярусами в горщиках");
            texe_vol1.setText(null);
            texe_vol2.setText(null);
            texe_vol3.setText(null);
            text_tem1.setText(null);
            text_tem2.setText(null);
            text_vrojai.setText(null);
            chek_op1.setSelected(false);
            chek1 =chek_tep.getText();

        });
        chek_op1.setOnAction(event -> { // опилення 1
            chek2= chek_op1.getText();


        });
        chek_op2.setOnAction(event -> { // опилення 1
            chek2= chek_op2.getText();


        });

        button_rozchot.setOnAction(event -> { // розрахунок
            Urojai urojai = new Urojai(texe_vol1.getText(),texe_vol2.getText(),texe_vol3.getText(),
                    text_tem1.getText(),text_tem2.getText());
            text_vrojai.setText(Integer.toString(urojai.getRez()));


        });
        button_null.setOnAction(event -> { // розрахунок
            chek_vubor.getItems().clear();
            chek_op2.setSelected(false);
            texe_vol1.setText(null);
            texe_vol2.setText(null);
            texe_vol3.setText(null);
            text_tem1.setText(null);
            text_tem2.setText(null);
            text_vrojai.setText(null);
            chek_op1.setSelected(false);
            chek_grynt.setSelected(false);
            chek_tep.setSelected(false);


        });
        button_save.setOnAction(event -> { // зреження данних
            int id=0;
            //Створення об`экта з полями------------------------------------------------------
            Strawberrys strawberrys = null;
            try {strawberrys= new Strawberrys(chek1,chek_vubor.getValue().toString(),
                    texe_vol1.getText(),texe_vol2.getText(),texe_vol2.getText(),text_tem1.getText(),text_tem1.getText(),
                    chek2, text_vrojai.getText());
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("повідомлення");
                alert.setHeaderText("Введення даних");
                alert.setContentText("не коректно введені данні");
                alert.showAndWait();
            }
            try {
                id=Integer.parseInt(text_id.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("повідомлення");
                alert.setHeaderText("Введення даних");
                alert.setContentText("не коректно введені данні");
                alert.showAndWait();
            }


//Встановлення наявносты файла
            //
            if (new File("save.ser").exists())
            {
                hashMap =new HashMap<>();
                hashMap= MyInput("save.ser");

                hashMap.put(id,strawberrys);
                MyOutput(hashMap,"save.ser");
                     /* ObservableList<String> langs = FXCollections.observableArrayList(arrayList);
                        rez_list.setItems(langs);*/
//----------------------------------

            }else
            {
                hashMap =new HashMap<>();
                hashMap.put(id,strawberrys);
                MyOutput(hashMap,"save.ser");
                      /*  ObservableList<String> langs = FXCollections.observableArrayList(arrayList);
                        rez_list.setItems(langs);*/
            }


         //   text_help3.setText("результат збережено");}*/


        });
    }
    //---------------------------------------------------------------------------------------------------
//   читаемо з файла
public HashMap<Integer,Object> MyInput(String name_file) {
        FileInputStream fileInputStream = null;
        try {
        fileInputStream = new FileInputStream(name_file);
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
        HashMap<Integer,Object> hashMap = save.getHashMap();
        return hashMap;
        }
    //---------------------------------------------------------------------------------------------
    public void MyOutput (HashMap<Integer,Object>hashMap,String name_file){
        //--------------------------------------------
        // здерігаемо данні в файл
        Save save = new Save(hashMap);

        //создаем 2 потока для сериализации объекта и сохранения его в файл
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(name_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // сохраняем  в файл
        try {
            objectOutputStream.writeObject(save);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //закрываем поток и освобождаем ресурсы
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //-------------------------
    }
}
