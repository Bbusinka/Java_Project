package sample;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Users.Druk;
import sample.Users.User;
import sample.Users.Work;
public class Na_kartuController {
    private HashMap<String,Object> hash;
    public User user1;
    public Stage stage;
    public Parent root;
    @FXML
    private Label n_karti;
    @FXML
    private Label shet;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button contin;
    @FXML
    private TextField suma;
    @FXML
    private Button back;
    @FXML
    void initialize() {
        //Старотові функції
        start();
        zapret();
//Повернення на головне вікно системи
        back.setOnAction(event -> {
             stage = (Stage) back.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("System.fxml"));
            root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace(); }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("System");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show(); });
//Підтвердження процеса поповнення карти
            contin.setOnAction(event -> {if (!suma.getText().isEmpty() && Integer.parseInt(suma.getText())>0) {
                double a = user1.getSumm();
                double c = Integer.parseInt(suma.getText())*0.01;
                double b = a + Integer.parseInt(suma.getText())-c;
                user1.setSumm(b);
                //-----------------------------
                Druk druk = new Druk(user1.getSurname(),user1.getName(),user1.getPatronymic(),Integer.parseInt(suma.getText()),Double.parseDouble(String.valueOf(new DecimalFormat("#0.00").format(c)).replace(',','.')), "4073"+" **** "+"**** "+user1.getN_karti(),Double.parseDouble(String.valueOf(new DecimalFormat("#0.00").format(b)).replace(',','.')));
                String id = user1.getTel();
                //Створення об`экта з полями------------------------------------------------------
                if (new File("work.ser").exists()) {
                    hash = new HashMap<>();
                    hash = MyInput("work.ser");
                    hash.put(id, user1);
                    MyOutput(hash, "work.ser");
//----------------------------------
                } else {
                    hash = new HashMap<>();
                    hash.put(id, user1);
                    MyOutput(hash, "work.ser"); }
                stage = (Stage) contin.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("System.fxml"));
                root = null;
                try {
                    root = (Parent) fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace(); }
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("System");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show(); }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Помилка!");
            alert.setHeaderText(null);
            alert.setContentText("Ви не ввели всі дані! Перевірте введення ще раз!");
            alert.showAndWait();}});}
    public void MyOutput (HashMap<String,Object> hash, String name_file){
        //--------------------------------------------
        // зберігаємо данні в файл
        Work work = new Work(hash);
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
            objectOutputStream.writeObject(work);
        } catch (IOException e) {
            e.printStackTrace(); }
        //закрываем поток и освобождаем ресурсы
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }}
    //   читаємо з файла
    public HashMap<String,Object> MyInput(String name_file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(name_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace(); }
        Work work = null;
        try {
            work = (Work) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); }
        HashMap<String,Object> hash = work.getHashMap();
        return hash;
    }
    //Заповнення вікна даними
    public void start(){
        hash = MyInput("work.ser");
        for (HashMap.Entry<String, Object> name:hash.entrySet())
        {user1=(User) name.getValue();
            n_karti.setText(user1.getN_karti()+" Карта для виплат");
            shet.setText(String.valueOf(new DecimalFormat("#0.00").format(user1.getSumm())).replace(',','.'));}
    }
    //Заборона на введення символів
    public void zapret(){
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change; }
            return null; };
        suma.setTextFormatter(new TextFormatter<String>(integerFilter)); }}