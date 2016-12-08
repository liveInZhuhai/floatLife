package yyt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yyt.dao.UserDAO;
import yyt.model.User;
import yyt.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }

}