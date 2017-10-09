package models;

/**
 * Created by ruslan on 07.10.2017.
 */
public class User {
    private String username;
    private String password;

   public User(String username, String password) {
       this.username = username;
       this.password = password;
   }

   public boolean confirmPassword(String password){
       return this.password.equals(password);
   }


    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
