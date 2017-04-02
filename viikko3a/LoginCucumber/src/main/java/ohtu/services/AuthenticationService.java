package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }
        // käyttäjä luotiin ok 
        userDao.add(new User(username, password));
        return true;
    }

    private boolean invalid(String username, String password) {
        // username length min. 3 characters
        if (username.length() < 3) {
            return true;
        }
        if (password.length() < 8) { // pw lenght min. 3 characters
            return true;
        }

        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).{8,}");
        Matcher matcher = pattern.matcher(password);
        boolean found = matcher.matches();
        if (!found) {
            return true;
        } 
        if (found)  { // salasana ok -> invalid ei ole
            return false;
        }

        // password should consist of one special char and one number and 
        // be min 8 chars long
        // user exists, but it has different password than the new one
//        if (userDao.findByName(username).getPassword().equalsIgnoreCase(password)){
//            return true;
//        }
        // validity check of username and password
        return false;
    }
}
