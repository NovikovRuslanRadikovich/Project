package DAO;

import models.User;

import java.sql.SQLException;

/**
 * Created by ruslan on 07.10.2017.
 */
public interface UserDao {

    boolean isAuthorized(String username, String password);

    void register(User user);

    boolean isExists(String username);
}
