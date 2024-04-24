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
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.stage.Modality;
        import javafx.stage.Stage;
        import sample.Users.Save;
        import sample.Users.User;
        import sample.Users.Work;

public class VhodController {

    private HashMap<String, Object> hashMap;
    private HashMap<String, Object> hash;
    public User user1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button vhod;

    @FXML
    private TextField tel;

    @FXML
    private PasswordField password;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        //Удаление файла, где хранятся данные про конкретного пользователя
        File file=new File("work.ser");
        file.delete();


        //Событие кнопки ОТМЕНА
        back.setOnAction(event -> {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.close();
            //Переход на главную страницу
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Glavnaya.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Glavnaya");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        });
        //Событие кнопки ВХОД (вход в систему)
        vhod.setOnAction(event -> {
                    if (new File("save.ser").exists()) {
                        if (!tel.getText().isEmpty() && !password.getText().isEmpty()) {
                            hashMap = MyInput();
                            if (good_avto(MyInput(), tel.getText(), password.getText())) {
                                save();
                                Stage stage = (Stage) vhod.getScene().getWindow();
                                stage.close();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("System.fxml"));
                                Parent root = null;
                                try {
                                    root = (Parent) fxmlLoader.load();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage = new Stage();
                                stage.setTitle("System");
                                stage.setScene(new Scene(root));
                                stage.setResizable(false);
                                stage.show();
                                return;
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Помилка!");
                                alert.setHeaderText(null);
                                alert.setContentText("Ви ввели дані неправильно! Перевірте введення ще раз!");
                                alert.showAndWait();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Помилка!");
                            alert.setHeaderText(null);
                            alert.setContentText("Ви не ввели всі дані! Перевірте введення ще раз!");
                            alert.showAndWait();
                        }

                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Помилка!");
                        alert.setHeaderText(null);
                        alert.setContentText("Реєстраціій не знайдено! Для початку зареєструйтеся!");
                        alert.showAndWait();

                        Stage stage = (Stage) vhod.getScene().getWindow();
                        stage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registraciya.fxml"));
                        Parent root = null;
                        try {
                            root = (Parent) fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage = new Stage();
                        stage.setTitle("Registraciya");
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.show();
                        return;

                    }

                }
        );
    }


    //---------------------------------------------------------------------------------------------------
//   читаемо з файла
    public HashMap<String, Object> MyInput(String name_file) {
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

        Work work = null;
        try {
            work = (Work) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> hashMap = work.getHashMap();
        return hashMap;
    }

    //---------------------------------------------------------------------------------------------
    public void MyOutput(HashMap<String, Object> hashMap, String name_file) {
        //--------------------------------------------
        // здерігаемо данні в файл
        Work work = new Work(hashMap);

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
            objectOutputStream.writeObject(work);
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


    public HashMap<String, Object> MyInput() {// повертае колекцію  для порівняння
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
        HashMap<String, Object> hashMap = save.getHashMap();
        return hashMap;
    }

    // порівняння авторезації-------------------------------------------------------------------
    public boolean good_avto(HashMap<String, Object> hashMap, String id, String pas) {
        boolean yes_id = false;
        boolean rez = true;
        Set<String> keys = hashMap.keySet();
        String num_id = null;
        for (String i :
                keys) {
            if (i.equals(id)) {
                num_id = i;
                yes_id = true;
                break;
            } else {
                yes_id = false;

            }
        }
        if (yes_id) {
            User user = (User) hashMap.get(num_id);
            if (pas.equals(user.getPassword())) {
                rez = true;
            } else {
                rez = false;
            }
        } else {
            rez = false;
        }
        return rez;
    }

    private void save(){
        String id = null;
        //Створення об`экта з полями------------------------------------------------------
        user1= (User) hashMap.get(tel.getText());


//Встановлення наявносты файла

        if (new File("work.ser").exists()) {
            hash = new HashMap<>();
            hash = MyInput("work.ser");
            hash.put(id, user1);
            MyOutput(hash, "work.ser");
        } else {
            hash = new HashMap<>();
            hash.put(id, user1);
            MyOutput(hash, "work.ser");
        }

    }
}


