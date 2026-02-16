package service;

import dao.*;
import model.*;
import util.*;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    public User login(String email, String password) throws Exception {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            String hashed = Passwordhash.hashPassword(password);
            if (hashed.equals(user.getpassword())) {
                return user;
            }
        }
        return null;
    }

    public void register(User user) throws Exception {
        user.setpassword(Passwordhash.hashPassword(user.getpassword()));
        userDAO.save(user);
    }
}
