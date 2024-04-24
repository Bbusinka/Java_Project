package sample.Users;

import java.io.Serializable;

public class User implements Serializable {
    String surname;
    String name;
    String patronymic;
    String tel;
    Integer N_karti;
    Integer pin;
    String password;
    double summ;
    public User(String surname, String name, String patronymic, String tel, Integer n_karti, Integer pin, String password) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.tel = tel;
        this.N_karti = n_karti;
        this.pin = pin;
        this.password = password;
        this.summ = 100+Math.random()*570;

    }

    public User(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public double getSumm() {return summ; }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getTel() {
        return tel;
    }

    public Integer getN_karti() {
        return N_karti;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) { this.name = name; }

    public void setTel(String tel) { this.tel = tel; }

    public void setN_karti(Integer n_karti) { N_karti = n_karti; }

    public void setPassword(String password) { this.password = password; }}
