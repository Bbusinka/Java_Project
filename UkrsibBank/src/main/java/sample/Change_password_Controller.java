package sample;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Users.Save;
import sample.Users.User;
public class Change_password_Controller {
    private HashMap<String,Object> hashMap ;
    public static User user;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button podtverdit;
    @FXML
    private TextField tel;
    @FXML
    private PasswordField password;
    @FXML
    private Button back;
    @FXML
    private TextField new_password;
    @FXML
    private TextField repeat_new_password;
    @FXML
    void initialize() {
//        зміна паролю
        podtverdit.setOnAction(event -> {
                    if (!tel.getText().isEmpty() && !password.getText().isEmpty() && !new_password.getText().isEmpty()&& !repeat_new_password.getText().isEmpty() ) {
                        hashMap = MyInput();
                        if (good_avto(MyInput(), tel.getText(), password.getText())) {
                            String a=new_password.getText();
                            if (a.equalsIgnoreCase(repeat_new_password.getText())){
                            hashMap = MyInput();
                            User user = (User)hashMap.get(tel.getText());
                            user.setPassword(new_password.getText());
                            MyOutput(hashMap,"save.ser");
                            Stage stage = (Stage) podtverdit.getScene().getWindow();
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
                            return; }
                        else {Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Помилка!");
                                alert.setHeaderText(null);
                                alert.setContentText("Новий пароль не співпадає! Перевірте введення ще раз!");
                                alert.showAndWait();} } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Помилка!");
                            alert.setHeaderText(null);
                            alert.setContentText("Ви ввели дані неправильно! Перевірте введення ще раз!");
                            alert.showAndWait(); } }else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Помилка!");
                        alert.setHeaderText(null);
                        alert.setContentText("Ви не ввели всі дані! Перевірте введення ще раз!");
                        alert.showAndWait(); }});
        //Перехід на головне вікно системи
        back.setOnAction(event -> {
            Stage stage = (Stage) podtverdit.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("System.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace(); }
            stage = new Stage();
            stage.setTitle("System");
            stage.setScene(new Scene(root));
            stage.show();
            return; });}

    public void MyOutput (HashMap<String,Object> hashMap, String name_file){
        //--------------------------------------------
        // збереження даних в файл save.ser
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
        // зберігаємо  в файл
        try {
            objectOutputStream.writeObject(save);
        } catch (IOException e) {
            e.printStackTrace(); }
        //закрываем поток и освобождаем ресурсы
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace(); }
        //-------------------------
    }
    public HashMap<String,Object> MyInput() {// повертає колекцію для порівняння
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
        return hashMap; }
    // авторизація-------------------------------------------------------------------
    public boolean good_avto (HashMap<String,Object>hashMap,String id,String pas){
        boolean yes_id =false;
        boolean rez =true;
        Set<String> keys = hashMap.keySet();
        String num_id=null;
        for (String i:
                keys ) {
            if (i .equals(id)) {
                num_id = i;
                yes_id = true;
                break;
            } else {
                yes_id = false; }}
        if(yes_id){
            User user= (User) hashMap.get(num_id);
            if (pas.equals(user.getPassword())){
                rez=true; }
            else {
                rez=false; }
        }else {
            rez=false; }
        return rez; }
}
