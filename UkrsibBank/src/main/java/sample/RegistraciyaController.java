package sample;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.UnaryOperator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Users.Save;
import sample.Users.User;
public class RegistraciyaController implements Serializable {
    private HashMap<String,Object> hashMap ;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button vhod_r;
    @FXML
    private Button back;
    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private TextField carta;
    @FXML
    private TextField tel;
    @FXML
    private TextField patronymic;
    @FXML
    private TextField surname;
    @FXML
    private TextField pin;
    @FXML
    void initialize() {
        //Вызов на запрет ввода символов
        zapret();
//Возврат на главное окно программы
        back.setOnAction(event -> {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Glavnaya.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace(); }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Glavnaya");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        });
//процесс регистрации
        vhod_r.setOnAction(event -> {
                if (!pin.getText().isEmpty() && !surname.getText().isEmpty() && !patronymic.getText().isEmpty() && !name.getText().isEmpty()
                        && !tel.getText().isEmpty() && !carta.getText().isEmpty() && !password.getText().isEmpty()) {
                        Stage stage = (Stage) vhod_r.getScene().getWindow();
                        stage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Vhod.fxml"));
                        Parent root = null;
                        try {
                            root = (Parent) fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace(); }
                        stage = new Stage();
                        stage.setTitle("Vhod");
                        stage.setScene(new Scene(root));
                        stage.show();
                    String id = null;
                    //Створення об`экта з полями------------------------------------------------------
                    User user =  new User(surname.getText(), name.getText(), patronymic.getText(), tel.getText(), Integer.parseInt(carta.getText()), Integer.parseInt(pin.getText()), password.getText());
                    id = tel.getText();
//Встановлення наявносты файла
                    //
                    if (new File("save.ser").exists()) {
                        hashMap = new HashMap<>();
                        hashMap = MyInput("save.ser");

                        hashMap.put(id, user);
                        MyOutput(hashMap, "save.ser");
//----------------------------------
                    } else {
                        hashMap = new HashMap<>();
                        hashMap.put(id, user);
                        MyOutput(hashMap, "save.sAlert alert = new Alert(Alert.AlertType.INFORMATION);\n" +
                                "                    alert.setTitle(\"Помилка!\");\n" +
                                "                    alert.setHeaderText(null);\n" +
                                "                    alert.setContentText(\"Ви не ввели всі данні! Перевірте введення ще раз!\");\n" +
                                "                    alert.showAndWait();\n" +
                                "                    return;er");
                    }
                } else {

                }
  });}
    //   читаемо з файла
    public HashMap<String,Object> MyInput(String name_file) {
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
        HashMap<String,Object> hashMap = save.getHashMap();
        return hashMap;
    }
    //---------------------------------------------------------------------------------------------
    public void MyOutput (HashMap<String,Object>hashMap,String name_file) {
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
            e.printStackTrace(); }}
    //Запрет на ввод символов
    public void zapret(){
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change; }return null; };
        pin.setTextFormatter(new TextFormatter<String>(integerFilter));
        carta.setTextFormatter(new TextFormatter<String>(integerFilter)); }}
