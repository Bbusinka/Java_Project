package sample;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Users.User;
import sample.Users.Work;
public class SystemController {
    private HashMap<String,Object> hash;
    public User user1;
    public Parent root;
    public Stage stage;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button popolnenie;

    @FXML
    private Label name_user;

    @FXML
    private Label tel;

    @FXML
    private Button na_kartu;

    @FXML
    private Button s_karti;

    @FXML
    private Button krediti;

    @FXML
    private Button change_password;

    @FXML
    private Button change_user;

    @FXML
    private Button internet;

    @FXML
    private Button delete;


    @FXML
    private Label n_karti;

    @FXML
    private Label sum;

    @FXML
    private Button programm;

    @FXML
    void initialize() {
        //стартова функція
       start();
       //перехід на вікно поповнення мобільного телефону
        popolnenie.setOnAction(event -> {//
            stage = (Stage) popolnenie.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Popolnenie.fxml"));
            root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace(); }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Popolnenie");
            stage.setScene(new Scene(root));
            stage.show(); });
        //перехід на голвне вікно програми для зміни користувача
         change_user.setOnAction(event -> {
             stage = (Stage) change_user.getScene().getWindow();
             stage.close();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Glavnaya.fxml"));
              root = null;
             try {
                 root = (Parent) fxmlLoader.load();
             } catch (IOException e) {
                 e.printStackTrace(); }
             stage = new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setTitle("Glavnaya");
             stage.setScene(new Scene(root));
             stage.show(); });
         //перехід на вікно поповнення інтернету
         internet.setOnAction(event -> {
             stage = (Stage) internet.getScene().getWindow();
             stage.close();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Internet.fxml"));
             root = null;
             try {
                 root = (Parent) fxmlLoader.load();
             } catch (IOException e) {
                 e.printStackTrace(); }
             stage = new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setTitle("Internet");
             stage.setScene(new Scene(root));
             stage.show(); });
//перехід на вікно зміни паролю
         change_password.setOnAction(event -> {
             stage = (Stage) change_password.getScene().getWindow();
             stage.close();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Change_password.fxml"));
             root = null;
             try {
                 root = (Parent) fxmlLoader.load();
             } catch (IOException e) {
                 e.printStackTrace(); }
             stage = new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setTitle("Change User");
             stage.setScene(new Scene(root));
             stage.show(); });
//перехід на вікно видалення користувача
         delete.setOnAction(event -> {
             stage = (Stage) delete.getScene().getWindow();
             stage.close();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Podtverzhdenie_Delete.fxml"));
              root = null;
             try {
                 root = (Parent) fxmlLoader.load();
             } catch (IOException e) {
                 e.printStackTrace(); }
             stage = new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setTitle("Delete_User");
             stage.setScene(new Scene(root));
             stage.show(); });
//перехід на вікно оплати кредиту
         krediti.setOnAction(event -> {
             stage = (Stage) krediti.getScene().getWindow();
             stage.close();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Kredit.fxml"));
             root = null;
             try {
                 root = (Parent) fxmlLoader.load();
             } catch (IOException e) {
                 e.printStackTrace(); }
            stage = new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setTitle("Kredit");
             stage.setScene(new Scene(root));
             stage.show(); });
         //перехід на вікно зняття коштів з карти
         s_karti.setOnAction(event -> {
             stage = (Stage) s_karti.getScene().getWindow();
             stage.close();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("S_karti.fxml"));
              root = null;
             try {
                 root = (Parent) fxmlLoader.load();
             } catch (IOException e) {
                 e.printStackTrace(); }
             stage = new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setTitle("S_karti");
             stage.setScene(new Scene(root));
             stage.show();});
         //перехід на вікно поповнення карти
         na_kartu.setOnAction(event -> {
             stage = (Stage) change_user.getScene().getWindow();
             stage.close();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Na_kartu.fxml"));
              root = null;
             try {
                 root = (Parent) fxmlLoader.load();
             } catch (IOException e) {
                 e.printStackTrace(); }
             stage = new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setTitle("Na_kartu");
             stage.setScene(new Scene(root));
             stage.show();
         });
//Відкриття довідкового файлу про програму
         programm.setOnAction(event -> {
             Runtime r = Runtime.getRuntime();
             Process process =null;
             String cmd ="notepad README.txt";
             try {
                 process = r.exec(cmd);
             } catch (IOException e) {
                 e.printStackTrace();
             }});}
    //читаємо з файла
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
        return hash; }
//завантаження даних користувача з файлу work.ser
public void start(){
    hash = MyInput("work.ser");
    for (HashMap.Entry<String, Object> name:hash.entrySet())
    {  user1=(User) name.getValue();
        tel.setText(user1.getTel());
        name_user.setText(user1.getSurname()+" "+user1.getName()+" "+user1.getPatronymic());
        n_karti.setText("4073"+" **** "+"**** "+user1.getN_karti());
        sum.setText(String.valueOf(new DecimalFormat("#0.00").format(user1.getSumm())).replace(',','.'));}

}}
