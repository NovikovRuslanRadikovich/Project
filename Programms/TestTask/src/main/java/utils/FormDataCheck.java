package utils;

import DAO.UserDao;
import DAO.UserDaoImpl;
import services.UserService;
import services.UserServiceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ruslan on 07.10.2017.
 */
public class FormDataCheck {

    static UserDao userDao = UserDaoImpl.getInstance();
    static UserService userService = new UserServiceImpl(userDao);

    private FormDataCheck() {
    }

    public static boolean isValidName(String username) {

         Pattern p = Pattern.compile("^[a-zA-Z0-9]{4,}$");
         if(username != null) {
             Matcher m = p.matcher(username);
             return m.matches();
         }
         return false;

    }

    public static boolean isValidPassword(String password) {

        if(password == null) {
            return false;
        }

        Pattern p_length = Pattern.compile("^.{8,}$");
        Pattern p_numeral = Pattern.compile("^(.*)\\d(.*)$");
        Pattern p_lowwercase_letter = Pattern.compile("^(.*)[а-яa-z](.*)$");
        Pattern p_uppercase_letter = Pattern.compile("^(.*)[A-ЯA-Z](.*)$");

        Matcher m_length = p_length.matcher(password);
        if(!m_length.matches()) {
            return false;
        }

        Matcher m_numeral = p_numeral.matcher(password);
        if(!m_numeral.matches()) {
            return false;
        }

        Matcher m_lowwercase_letters = p_lowwercase_letter.matcher(password);
        if(!m_lowwercase_letters.matches()) {
            return false;
        }

        Matcher m_uppercase_letter = p_uppercase_letter.matcher(password);
        if(!m_uppercase_letter.matches()) {
            return false;
        }

        return true;
    }

    public static boolean passwordsEquality(String password, String confirmPassword) {
        if(password == null) {
            return false;
        }

        return password.equals(confirmPassword);
    }

    public static boolean isExists(String username) {

        return userService.isExists(username);
    }

}
