package ru.itis.kpfu.Novikov_Ruslan.models;



public class User {
    private String login;
    public String password;
    private int id;
    private boolean isMale;
    private String phoneNumber;
    private String dob;
    private String city;


    public User(String login, String password, boolean isMale, String phoneNumber, String dob, String city){
        this.login = login;
        this.password = password;
        this.isMale = isMale;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.city = city;
    }


    public boolean confirmPassword(String password){
        return this.password == password;
    }

    public String getName() {
        return login;
    }

    public boolean isMale() {
        return isMale;
    }

    public String getPasswordHash(){
        return password;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getDOB(){
        return dob;
    }

    public String getCity(){
        return city;
    }

    public int getId() {
        return id;
    }

    public void setName(String login) {
        this.login = login;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDOB(String DOB) {
        this.dob = DOB;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    @Override
    public String toString() {
        return "Имя: " + login + "; id: " + Integer.toString(id) + "; Пол: " + Boolean.toString(isMale) +
                "; number: " + getPhoneNumber() + "; город: " + city + "; Дата рождения: " + dob;
    }
}
