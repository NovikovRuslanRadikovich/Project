package services;

import java.sql.SQLException;

/**
 * Created by ruslan on 07.10.2017.
 */
public interface UserService {


    void register(String username, String password);

    boolean isRegistered(String username, String password);

    boolean isExists(String username);
}
