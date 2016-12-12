package ru.itis.kpfu.Novikov_Ruslan.DAO;

import ru.itis.kpfu.Novikov_Ruslan.models.User;

import java.sql.SQLException;


public interface UserDao {
    void saveUser(User user) throws SQLException;
    User getUser(int userId);
    User getUser(String userName);
    User[] getAll();
    void deleteUser(int userId);
    void printAll();
}
