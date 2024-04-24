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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller {
    byte fleg=0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView button_snec;

    @FXML
    private TextField text_id;

    @FXML
    private TextField text_pass;

    @FXML
    private TextField text_pass_remuv;

    @FXML
    private Button button_vhid;

    @FXML
    private Button button_reestr;

    @FXML
    private Label text_spich;

    @FXML
    private Button button_snecs;

    @FXML
    void initialize() {

        button_snecs.setOnAction(event -> {//інтерактивне спылкування зі змыйкою
            Juja juja = new Juja();
            text_spich.setText(juja.boltat());
        });

        button_reestr.setOnAction(event -> {//обробка натиснення на кнопку реєстрація

            if(fleg==0){
                text_pass_remuv.setVisible(true);
                button_reestr.setText("Підтвердити");
                text_spich.setText("Для реєстрації потрібно придумати та вказати свій уникальний логін та пароль " +
                        "підтвердження паролю повино бути таке ж саме як и пароль " +
                        "дивись не переплутай!");
                text_id.setText("");
                text_pass.setText("");
                text_pass_remuv.setText("");
                fleg =1;
            }else {
                //процес реєстрації-------------------------------------------------------------------------------------------
                if (fleg == 1) {

                   if ((text_pass.getText().equals(""))||(text_pass_remuv.getText().equals(""))||(text_id.getText().equals(""))){

                       text_spich.setText("Перевірь будть-ласка чи всі поля ти заповнив?");
                   }else {

                           if (text_pass.getText().equals(text_pass_remuv.getText())){
//Встановлення наявносты файла------------------------------------------------
                               //
                               if (new File("save.ser").exists())
                               {
                                   //Якщо файл існує
                                   if (good_id(MyInput(),text_id.getText())){
                                       text_spich.setText("Користувач з таким ніком уже існує");

                                   }else {

                                       HashMap<String,Object>hashMap = MyInput();
                                       Gamer gamer =new Gamer();
                                       gamer.setPass(text_pass.getText());
                                       gamer.setReh(0);
                                       gamer.setNo_reh(0);
                                       hashMap.put(text_id.getText(),gamer);
                                       MyOutput(hashMap);

                                       text_pass_remuv.setVisible(false);
                                       button_reestr.setText("Зареєструватися");
                                       text_spich.setText("Реєстраця прошла успішно! Для входу потрібно ввесть ваш логін та пароль");
                                       text_id.setText("");
                                       text_pass.setText("");
                                       text_pass_remuv.setText("");

                                   }
//----------------------------------

                               }else//Якщо файлу немає
                               {
                                   HashMap<String,Object>hashMap =new HashMap<>();
                                   Gamer gamer =new Gamer();
                                   gamer.setPass(text_pass.getText());
                                   gamer.setReh(0);
                                   gamer.setNo_reh(0);
                                   hashMap.put(text_id.getText(),gamer);
                                   MyOutput(hashMap);

                                   text_pass_remuv.setVisible(false);
                                   button_reestr.setText("Зареєструватися");
                                   text_spich.setText("Реєстраця прошла успішно! Для входу потрібно ввесть ваш логін та пароль");
                                   text_id.setText("");
                                   text_pass.setText("");
                                   text_pass_remuv.setText("");
                               }


                           }//--------------------------------------------------------------------------

                           else {
                               text_spich.setText("Пароль та підтвердження паролю неспівпадають. Спробуй ще раз");
                               text_pass.setText("");
                               text_pass_remuv.setText("");
                           }
                   }
                }
                //-------------------------------------------------------------
            }
        });//----------------------------------------------------------------------------------------------------------------
        button_vhid.setOnAction(event -> {//авторезація користквача
            if ((text_pass.getText().equals(""))||(text_id.getText().equals(""))){
                text_spich.setText("Перевірь будть-ласка чи всі поля ти заповнив?");
            }else {



            //Встановлення наявносты файла------------------------------------------------
            //
            if (new File("save.ser").exists())
            {
                if (good_avto(MyInput(),text_id.getText(),text_pass.getText())){
                    text_spich.setText("ура!!!");
                    Temp temp =new Temp();
                    temp.setId(text_id.getText());
                    temp.setRiven(1);
                    //Закрить форму

                    Stage stage = (Stage) button_vhid.getScene().getWindow();
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
                }
                else
                {
                    text_spich.setText("Невірний логін або пароль. Спробуй ще!");
                }
            }else//Якщо файлу немає
            {
                text_spich.setText("Немае жодної регестрації!");
            }
            }
        });
    }
    //методи-----------------------------------------------------









    public HashMap<String,Object> MyInput() {// повертае колекцію  для порівняння
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
    // порівняння авторезації-------------------------------------------------------------------
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
                yes_id = false;

            }
        }
        if(yes_id){
            Gamer gamer= (Gamer) hashMap.get(num_id);
            if (pas.equals(gamer.getPass())){
                rez=true;
            }
               else {
                rez=false;
            }
        }else {
            rez=false;
        }
        return rez;
    }

    // порівняння id-------------------------------------------------------------------
    public boolean good_id (HashMap<String,Object>hashMap,String id){

        Set<String> keys = hashMap.keySet();
       boolean rez =false;
        for (String i:
                keys ) {
           if(i.equals(id)) {
               rez =true; break;
           }else {
               rez =false;}
        }
        return rez;
    }
    //Стаорення файлу та внесення до нього даних
    //---------------------------------------------------------------------------------------------
    public void MyOutput (HashMap<String,Object>hashMap){
        //--------------------------------------------
        // здерігаемо данні в файл
        Save save = new Save(hashMap);

        //создаем 2 потока для сериализации объекта и сохранения его в файл
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("save.ser");
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
