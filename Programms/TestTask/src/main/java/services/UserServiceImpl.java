package services;

import DAO.UserDao;
import models.User;



/**
 * Created by ruslan on 07.10.2017.
 */
public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl(UserDao userDao){
       this.userDao = userDao;
    }


    @Override
    public boolean isRegistered(String username, String password) {
        return userDao.isAuthorized(username,password);
    }

    @Override
    public boolean isExists(String username) {
        return userDao.isExists(username);
    }

    @Override
    public void register(String username, String password) {
        userDao.register(new User(username,password));
    }
}
