package ru.itis.kpfu.Novikov_Ruslan.DAO;

import ru.itis.kpfu.Novikov_Ruslan.models.User;


public class UserServiceImpl implements UserService {

    UserDao myDAO;

    public UserServiceImpl(UserDao userDao){
        myDAO = userDao;
    }

    public boolean isRegistered(String userName) {
        User[] allUsers = myDAO.getAll();

        for(User user : allUsers) {
            if (user.getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }
}
