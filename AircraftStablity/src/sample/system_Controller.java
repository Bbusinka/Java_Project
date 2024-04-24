package sample;

import Aircraft.Aircraft;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Aircraft.Save;
import Aircraft.Druk;
import Aircraft.Druk2;
import Aircraft.Druk3;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class system_Controller implements Serializable {
public ArrayList<Object> arr;
public Aircraft air;


public double d,g,e;
    @FXML
    private RadioMenuItem r1;

    @FXML
    private RadioMenuItem r2;

    @FXML
    private RadioMenuItem r3;

    @FXML

    private MenuItem progr;

    @FXML
    private Label nazva;


    @FXML
    private Label X_vv;

    @FXML
    private Label step_xt;

    @FXML
    private Label t_vv;

    @FXML
    private Label xf_vv;

    @FXML
    private Label f_vv;

    @FXML
    private Label b_vv;

    @FXML
    private Label a_vv;

    @FXML
    private Label x_viv;

    @FXML
    private Label t_viv;

    @FXML
    private Label step_xf;

    @FXML
    private Label xf_viv;

    @FXML
    private Label f_viv;

    @FXML
    private Label result;

    @FXML
    private Label result_xt;

    @FXML
    private Label result_xf;

    @FXML
    private Label z_result;

    @FXML
    private Label result_prod_ust;

    @FXML
    private Label vivod;

    @FXML
    private TextField zn1;

    @FXML
    private TextField zn2;

    @FXML
    private TextField zn3;

    @FXML
    private Button rashet;

    @FXML
    private Button nov;

    @FXML
    private Button druk;

    @FXML
    private TableView<Aircraft> aircraft_table;

    @FXML
    private TableColumn<Aircraft, Double> z1;

    @FXML
    private TableColumn<Aircraft, Double> z2;

    @FXML
    private TableColumn<Aircraft, Double> z3;

    @FXML
    private TableColumn<Aircraft, Double> z4;

    @FXML
    private TableColumn<Aircraft, Double> z5;

    @FXML
    private TableColumn<Aircraft, Double> z6;

    private final ObservableList<Aircraft> usersData= FXCollections.observableArrayList();

    @FXML
    void  initialize() {
        druk.setDisable(true);
        File file=new File("save.ser");
        if (file.exists()){
        arr=MyInput("save.ser");
        for (Object text: arr){
            Aircraft aircraft = (Aircraft) text;
            usersData.add(new Aircraft(aircraft.getZn1(),aircraft.getZn2(),aircraft.getZn3(),Double.parseDouble(new DecimalFormat("#0.00").format(aircraft.getRez1()).replace(',', '.')), Double.parseDouble(new DecimalFormat("#0.00").format(aircraft.getRez2()).replace(',', '.')), Double.parseDouble(new DecimalFormat("#0.00").format(aircraft.getRez3()).replace(',', '.'))));
            usersData.add(new Aircraft(14.5,12,4,3.63,3,0.63));
            usersData.add(new Aircraft(25.5,6,3,8.5,2,6.5));
            usersData.add(new Aircraft(5,15,3.5,1.42,4.28,-2.86));
        }

        z1.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn1"));
        z2.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn2"));
        z3.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn3"));
        z4.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez1"));
        z5.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez2"));
        z6.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez3"));
        // заполняем таблицу данными
        aircraft_table.setItems(usersData);
        }

         Pattern p = Pattern.compile("(\\d+\\.?\\d*)?");
            zn1.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!p.matcher(newValue).matches()) zn1.setText(oldValue); });
            zn2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!p.matcher(newValue).matches()) zn2.setText(oldValue); });
            zn3.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!p.matcher(newValue).matches()) zn3.setText(oldValue); });




            //запуск программы----------------------------------------------------------
            r1.setSelected(true);
            zn1.setText(""); zn2.setText(""); zn3.setText("");
            X_vv.setText("X"); t_vv.setText("T");
            xf_vv.setText("X"); f_vv.setText("F");
            b_vv.setText("B"); a_vv.setText("A");
            x_viv.setText("X"); t_viv.setText("T");
            xf_viv.setText("X"); f_viv.setText("F");
            step_xf.setText("-"); step_xt.setText("-");
            result.setText("σ"); z_result.setText("Z");
            nazva.setText("Розрахунок поздовжньої стійкості літака");
            vivod.setText(""); result_prod_ust.setText("="); result_xf.setText("="); result_xt.setText("=");
            // нажатие первого пункта меню-----------------------------------------------
            r1.setOnAction(event -> {
                r2.setSelected(false); r3.setSelected(false);
                zn1.setText(""); zn2.setText(""); zn3.setText("");
                X_vv.setText("X"); t_vv.setText("T");
                xf_vv.setText("X"); f_vv.setText("F");
                b_vv.setText("B"); a_vv.setText("A");
                x_viv.setText("X"); t_viv.setText("T");
                xf_viv.setText("X"); f_viv.setText("F");
                step_xf.setText("-"); step_xt.setText("-");
                result.setText("σ"); z_result.setText("Z");
                nazva.setText("Розрахунок поздовжньої стійкості літака");
                vivod.setText(""); result_prod_ust.setText("="); result_xf.setText("="); result_xt.setText("=");
            });
            // нажатие второго пункта меню-----------------------------------------------
            r2.setOnAction(event -> {
                r1.setSelected(false); r3.setSelected(false);
                zn1.setText(""); zn2.setText(""); zn3.setText("");
                vivod.setText(""); result_prod_ust.setText("="); result_xf.setText("="); result_xt.setText("=");
                X_vv.setText("X"); t_vv.setText("T");
                xf_vv.setText("X"); f_vv.setText("F");
                b_vv.setText("l"); a_vv.setText("кр");
                x_viv.setText("X"); t_viv.setText("T");
                xf_viv.setText("X"); f_viv.setText("F");
                step_xf.setText("-"); step_xt.setText("-");
                result.setText("σ"); z_result.setText("у");
                nazva.setText("Розрахунок шляхової стійкості літака");
            });
            // нажатие третьего пункта меню-----------------------------------------------
            r3.setOnAction(event -> {
                r2.setSelected(false); r1.setSelected(false);
                zn1.setText(""); zn2.setText(""); zn3.setText("");
                vivod.setText(""); result_prod_ust.setText("="); result_xf.setText("="); result_xt.setText("=");
                X_vv.setText("У"); t_vv.setText("T");
                xf_vv.setText("У"); f_vv.setText("F");
                b_vv.setText("l"); a_vv.setText("кр");
                x_viv.setText("У"); t_viv.setText("T");
                xf_viv.setText("У"); f_viv.setText("F");
                step_xf.setText("-"); step_xt.setText("-");
                result.setText("σ"); z_result.setText("х");
                nazva.setText("Розрахунок поперечної стійкості літака");
            });
// новые данные-----------------------------------------------
            nov.setOnAction(event -> {
                vivod.setText("");
                result_prod_ust.setText("");
                result_xf.setText("");
                result_xt.setText("");
                zn1.setText("");
                zn2.setText("");
                zn3.setText("");
            });
//расчеты----------------------------------------------------
            rashet.setOnAction(event -> {
                if (!zn1.getText().isEmpty()&& !zn2.getText().isEmpty()&& !zn3.getText().isEmpty()) {
                    druk.setDisable(false);
                    air = new Aircraft(Double.parseDouble(zn1.getText()), Double.parseDouble(zn2.getText()), Double.parseDouble(zn3.getText()));


             //   MyInput("save.ser");
          // --------------------------------------------------------------------------------------------------------------------------
               if(r1.isSelected()){
                   d = air.getZn1()/ air.getZn3();
                   e = air.getZn2()/air.getZn3();
                   g = d-e;
                   result_xt.setText("= "+new DecimalFormat("#0.00").format(d).replace(',','.'));
                   result_xf.setText("= "+new DecimalFormat("#0.00").format(e).replace(',','.'));
                   result_prod_ust.setText("= "+new DecimalFormat("#0.00").format(g).replace(',','.'));
                   air.setRez1(Double.parseDouble(new DecimalFormat("#0.00").format(d).replace(',','.')));
                   air.setRez2(Double.parseDouble(new DecimalFormat("#0.00").format(e).replace(',','.')));
                   air.setRez3(Double.parseDouble(new DecimalFormat("#0.00").format(g).replace(',','.')));
                   if (g<0){
                       vivod.setText("Так як ступінь поздовжної стійкості літака менше 0," +
                               "\nто літак є стійким.");}
                   if (g>0){
                       vivod.setText("Так як ступінь поздовжної стійкості літака більше 0," +
                               "\nто літак є нестійким.");}
                   if (g==0){
                       vivod.setText("Так як ступінь поздовжної стійкості літака дорівнює 0," +
                               "\nто літак є нейтральним.");}

                   if (file.exists()){
                       arr  = new ArrayList<>();
                       arr.add(air);
                       MyOutput(arr);}
                   else {  arr  = new ArrayList<>();
                       arr.add(air);
                       MyOutput(arr);}

                   usersData.add(new Aircraft(air.getZn1(),air.getZn2(),air.getZn3(),Double.parseDouble(new DecimalFormat("#0.00").format(d).replace(',', '.')), Double.parseDouble(new DecimalFormat("#0.00").format(e).replace(',', '.')), Double.parseDouble(new DecimalFormat("#0.00").format(g).replace(',', '.'))));
                   z1.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn1"));
                   z2.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn2"));
                   z3.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn3"));
                   z4.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez1"));
                   z5.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez2"));
                   z6.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez3"));
                   // заполняем таблицу данными
                   aircraft_table.setItems(usersData);

               }
               //------------------------------------------------------------------------------------------------------------------------------
                if(r2.isSelected()){
                     d = air.getZn1()/ air.getZn3();
                     e = air.getZn2()/air.getZn3();
                    g = d-e;
                    result_xt.setText("= "+new DecimalFormat("#0.00").format(d).replace(',','.'));
                    result_xf.setText("= "+new DecimalFormat("#0.00").format(e).replace(',','.'));
                    result_prod_ust.setText("= "+new DecimalFormat("#0.00").format(g).replace(',','.'));
                    air.setRez1(Double.parseDouble(new DecimalFormat("#0.00").format(d).replace(',','.')));
                    air.setRez2(Double.parseDouble(new DecimalFormat("#0.00").format(e).replace(',','.')));
                    air.setRez3(Double.parseDouble(new DecimalFormat("#0.00").format(g).replace(',','.')));
                    if (g<0){
                        vivod.setText("Так як ступінь шляхової стійкості літака менше 0," +
                                "\nто літак є стійким.");}
                    if (g>0){
                        vivod.setText("Так як ступінь шляхової стійкості літака більше 0," +
                                "\nто літак є нестійким.");}
                    if (g==0){
                        vivod.setText("Так як ступінь шляхової стійкості літака дорівнює 0," +
                                "\nто літак є нейтральним.");}
                    if (file.exists()){
                        arr  = new ArrayList<>();
                        arr.add(air);
                        MyOutput(arr);}
                    else {  arr  = new ArrayList<>();
                        arr.add(air);
                        MyOutput(arr);}

                    usersData.add(new Aircraft(air.getZn1(),air.getZn2(),air.getZn3(),Double.parseDouble(new DecimalFormat("#0.00").format(d).replace(',', '.')), Double.parseDouble(new DecimalFormat("#0.00").format(e).replace(',', '.')), Double.parseDouble(new DecimalFormat("#0.00").format(g).replace(',', '.'))));
                    z1.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn1"));
                    z2.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn2"));
                    z3.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn3"));
                    z4.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez1"));
                    z5.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez2"));
                    z6.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez3"));
                    // заполняем таблицу данными
                    aircraft_table.setItems(usersData);


                }
                //---------------------------------------------------------------------------------------------------------
                if(r3.isSelected()){
                    d = air.getZn1()/ air.getZn3();
                    e = air.getZn2()/air.getZn3();
                   g = d-e;
                    result_xt.setText("= "+new DecimalFormat("#0.00").format(d).replace(',','.'));
                    result_xf.setText("= "+new DecimalFormat("#0.00").format(e).replace(',','.'));
                    result_prod_ust.setText("= "+new DecimalFormat("#0.00").format(g).replace(',','.'));
                    air.setRez1(Double.parseDouble(new DecimalFormat("#0.00").format(d).replace(',','.')));
                    air.setRez2(Double.parseDouble(new DecimalFormat("#0.00").format(e).replace(',','.')));
                    air.setRez3(Double.parseDouble(new DecimalFormat("#0.00").format(g).replace(',','.')));
                    if (g<0){
                        vivod.setText("Так як ступінь поперечної стійкості літака менше 0," +
                                "\nто літак є стійким.");}
                    if (g>0){
                        vivod.setText("Так як ступінь поперечної стійкості літака більше 0," +
                                "\nто літак є нестійким.");}
                    if (g==0){
                        vivod.setText("Так як ступінь поперечної стійкості літака дорівнює 0," +
                                "\nто літак є нейтральним.");}
                    if (file.exists()){
                        arr  = new ArrayList<>();
                        arr.add(air);
                        MyOutput(arr);}
                    else {  arr  = new ArrayList<>();
                        arr.add(air);
                        MyOutput(arr);}

                    usersData.add(new Aircraft(air.getZn1(),air.getZn2(),air.getZn3(),Double.parseDouble(new DecimalFormat("#0.00").format(d).replace(',', '.')), Double.parseDouble(new DecimalFormat("#0.00").format(e).replace(',', '.')), Double.parseDouble(new DecimalFormat("#0.00").format(g).replace(',', '.'))));
                    z1.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn1"));
                    z2.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn2"));
                    z3.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("zn3"));
                    z4.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez1"));
                    z5.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez2"));
                    z6.setCellValueFactory(new PropertyValueFactory<Aircraft, Double>("rez3"));
                    // заполняем таблицу данными
                    aircraft_table.setItems(usersData);

                }}
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Помилка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Ви не ввели всі дані! Перевірте введення ще раз!");
                    alert.showAndWait();
                    return;}
            });

        //друк------------------------------------------------------------------------------------------------------------------------
        druk.setOnAction(event -> {

            if (r1.isSelected()) {
                 new Druk(air.getZn1(), air.getZn2(), air.getZn3(), air.getRez1(), air.getRez2(), air.getRez3());
                try {
                    Desktop.getDesktop().edit(new File("druk1.txt"));
                } catch (Exception e) {
                    e.getMessage();

                }
            }
            if (r2.isSelected()) {
                 new Druk2(air.getZn1(), air.getZn2(), air.getZn3(), air.getRez1(), air.getRez2(), air.getRez3());
                try {
                    Desktop.getDesktop().edit(new File("druk2.txt"));
                } catch (Exception e) {
                    e.getMessage();
                    System.out.println("ERROR");
                }
            }
            if (r3.isSelected()) {
                new Druk3(air.getZn1(), air.getZn2(), air.getZn3(),air.getRez1(), air.getRez2(), air.getRez3());
                try {
                    Desktop.getDesktop().edit(new File("druk3.txt"));
                } catch (Exception e) {
                    e.getMessage();
                    System.out.println("ERROR");
                }
            }
        });

        //про програму------------------------------------------------------------------------------------------------------------------------
        progr.setOnAction(event -> {
            try {
                Desktop.getDesktop().edit(new File("README.txt"));
            }catch (Exception e){
                e.getMessage();
                System.out.println("ERROR");
            }});
    }



    public ArrayList<Object> MyInput(String name_file) {// повертае колекцію  для порівняння
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
        ArrayList<Object> arr = save.getArr();
        return arr;
    }
    //---------------------------------------------------------------------------------------------
    public void MyOutput (ArrayList<Object> arr){
        //--------------------------------------------
        // здерігаемо данні в файл
        Save save = new Save(arr);

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



