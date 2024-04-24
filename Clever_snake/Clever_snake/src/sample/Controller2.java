package sample;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class Controller2 {
    HashMap<String,Boolean> hashMap;
    Boolean flag;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_vidpovid;

    @FXML
    private Label text_spich2;

    @FXML
    private Button button_snecs2;

    @FXML
    private MenuItem menu_riv1;

    @FXML
    private MenuItem menu_riv2;

    @FXML
    private MenuItem menu_riv3;

    @FXML
    private MenuItem menu_bek;

    @FXML
    private MenuItem menu_rez;

    @FXML
    private MenuItem menu_my_rez;

    @FXML
    private MenuItem menu_avtor;

    @FXML
    private MenuItem menu_pravela;

    @FXML
    private MenuItem menu_game;

    @FXML
    private MenuItem menu_close;

    @FXML
    private RadioButton chek_DA;

    @FXML
    private ToggleGroup Group;

    @FXML
    private RadioButton chek_NE;

    @FXML
    private Label text_vopros;
    @FXML
    private Label text_login;

    @FXML
    void initialize() {
        start();
        menu_bek.setOnAction(event->{// повернення до форми реэстрації
            Stage stage =  (Stage) button_snecs2.getScene().getWindow();
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
            stage.setTitle("Відповіді на запитання");
            stage.setScene(new Scene(root, 700, 500));
            stage.show();
        });//-----------------------------------------------
        menu_close.setOnAction(event->{//вихід
            Stage stage =  (Stage) button_snecs2.getScene().getWindow();
            stage.close();
        });//----------------------------------------------------------------------
       //відповіды-------------------------------------------------------------
        chek_DA.setOnAction(event->{
            flag = true;
        });
        chek_NE.setOnAction(event->{
            flag = false;
        });
        //-----------------------------------------------------------------------
        button_snecs2.setOnAction(event->{//розмова зі змійкою
            Juja juja = new Juja();
            text_spich2.setText(juja.boltat());
        });
        //-----------------------------------------------------------------------
        button_vidpovid.setOnAction(event->{//обробка натиснення кнопки здачі відповіді

           //робота з відповідями-------------------------------------------------------

            if (hashMap.get(text_vopros.getText())==flag){
                //вірна відповідь-------------------------------------------------
                text_spich2.setText("Гарно пограли! Мені сподобалось!!!\n"+"Давай зіграймо ЩЕ!");
                Ze_Game();
                //-----------------------------------------------------------------
                HashMap <String ,Object> hashMap = MyInput();
                Gamer gamer =(Gamer)hashMap.get(Temp.getId());
                gamer.setReh(gamer.getReh()+1);
                hashMap.put(Temp.getId(),gamer);
                MyOutput(hashMap);
                //----------------------------------------------------------------
            }
            else//невірна відповідь---------------------------------------------
            {
                text_spich2.setText("Відповідь не правильна.Спробуй ще!");

                //-----------------------------------------------------------------
                HashMap <String ,Object> hashMap = MyInput();
                Gamer gamer =(Gamer)hashMap.get(Temp.getId());
                gamer.setNo_reh(gamer.getNo_reh()+1);
                hashMap.put(Temp.getId(),gamer);
                MyOutput(hashMap);
                //----------------------------------------------------------------
            }

            ArrayList<String> arrayList = new ArrayList<>();
            for (HashMap.Entry<String,Boolean> pair : hashMap.entrySet())
            {
                arrayList.add(pair.getKey());                      //ключ
                System.out.println(arrayList);
            }
            //----------------------------------------------------------------
            int kol =arrayList.size();
            int n = (int) (Math.random()*arrayList.size());
            System.out.println(n);
            text_vopros.setText(arrayList.get(n));

            chek_DA.setSelected(false);
            chek_NE.setSelected(false);
            //--------------------------------------------------------------
        });
        menu_riv1.setOnAction(event->{//перший рівень складності
            Temp.setRiven(1);
            text_spich2.setText("Щоб зіграти дай відповідь на моє запитання");
            start();
        });
        menu_riv2.setOnAction(event->{//другий рівень складності
            Temp.setRiven(2);
            text_spich2.setText("Щоб зіграти дай відповідь на моє запитання");
            start();
        });
        menu_riv3.setOnAction(event->{//третій рівень складності
            Temp.setRiven(3);
            text_spich2.setText("Щоб зіграти дай відповідь на моє запитання");
            start();
        });
        menu_avtor.setOnAction(event->{//інформація стосовно автора
            text_spich2.setText("Студентка групи РПЗ16 1/9\n"+
                    "Оголь Ірина Віталіївна");
        });
        menu_avtor.setOnAction(event->{//інформація стосовно автора
            text_spich2.setText("Студентка групи РПЗ16 1/9\n"+
                    "Оголь Ірина Віталіївна");
        });
        menu_pravela.setOnAction(event->{//правела гри
            text_spich2.setText("Все просто\n"+
                    "Порібно збирати їжу\n"+
                    "і не натикатись на стінки та перешкоди.\n"+
                    "Вдалої гри ;)");
        });

        menu_game.setOnAction(event->{//Управління
            text_spich2.setText("Все просто\n"+
                    "Щоб рухатись вперед натисни - W\n"+
                    "Щоб рухатись назад натисни - S\n"+
                    "Щоб рухатись вліво натисни - A\n"+
                    "Щоб рухатись вправо натисни - D\n"+
                    "Вдалої гри ;)");
        });
        menu_rez.setOnAction(event->{// перехыд на форму з результатамы
            Stage stage =  (Stage) button_snecs2.getScene().getWindow();
            stage.close();
//------------------------------
            //создаём новую форму
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("sample3.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //------
            //запускаем
            stage = new Stage();
            stage.setTitle("Результати");
            stage.setScene(new Scene(root, 700, 500));
            stage.show();
        });//-----------------------------------------------

    }

    public void start(){//Cтартові позиції
        text_login.setText("Логін користувача  "+Temp.getId());
       Zadachi zadachi =new Zadachi();
       switch (Temp.getRiven()){
           case 1: {
               hashMap= zadachi.Riv1();break;
           }
           case 2: {
               hashMap= zadachi.Riv2();break;
           }
           case 3: {
               hashMap= zadachi.Riv3();break;
           }
       }

        ArrayList<String> arrayList = new ArrayList<>();
        for (HashMap.Entry<String,Boolean> pair : hashMap.entrySet())
        {
           arrayList.add(pair.getKey());                      //ключ
            System.out.println(arrayList);
        }
        int kol =arrayList.size();
        int n = (int) (Math.random()*arrayList.size());
        System.out.println(n);
        text_vopros.setText(arrayList.get(n));
    }
//----------------------------------------------------------------------------------------
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




//запис даних в файл//--------------------------------------------------
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
//Запуск гри---------------------------------------------------
    public  void Ze_Game(){

        File file =new File("Proba_Snec.jar");
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    }





  /*  */