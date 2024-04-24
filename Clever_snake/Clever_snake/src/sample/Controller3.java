package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Controller3 {
    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_bek2;

    @FXML
    private TextField text_login2;

    @FXML
    private TextField text_pass2;

    @FXML
    private TextField text_ugadal;

    @FXML
    private TextField text_ne_ugadal;

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, String> idColumn;

    @FXML
    private TableColumn<User, Integer> rehColumn;

    @FXML
    private TableColumn<User, Integer> no_rehColumn;



    @FXML
    void initialize() {
        start3(MyInput());

        button_bek2.setOnAction(event->{// повернутись на попередню форму
            Stage stage =  (Stage) button_bek2.getScene().getWindow();
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
            stage.setTitle("Відповіді на запитання");
            stage.setScene(new Scene(root, 700, 500));
            stage.show();
        });


    }//---------------------------------------------------------
    public HashMap<String,Object> MyInput() {// повертае колекцію
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
        HashMap<String,Object> hashMap = save.getHashMap();
        return hashMap;
    }
    public void start3 ( HashMap<String,Object> hashMap) {
        Gamer gamer = (Gamer) hashMap.get(Temp.getId());


        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        rehColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("reh"));
        no_rehColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("no_reh"));

        // заполняем таблицу данными
        tableUsers.setItems(usersData);


        text_login2.setText(Temp.getId());
        text_pass2.setText(gamer.getPass());
        text_ne_ugadal.setText(Integer.toString(gamer.getReh()));
        text_ugadal.setText(Integer.toString(gamer.getNo_reh()));

    }


    private void initData() {

        //usersData.add(new User("1",2, 12));
        Map<String, Object> map = MyInput();

        for (Map.Entry<String, Object> pair : map.entrySet())
        {
            String key = pair.getKey();                      //ключ
            Gamer gamer =(Gamer) pair.getValue();                  //значение
            usersData.add(new User(key, gamer.getReh(), gamer.getNo_reh()));
        }

    }
}




