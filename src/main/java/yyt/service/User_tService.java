package yyt.service;

import yyt.model.User_t;

/**
 * Created by hadoop on 16-8-2.
 */
public interface User_tService {
    public int insert(User_t user_t);
    public User_t getUserById(int userId);
    public int delete(int id);
}



