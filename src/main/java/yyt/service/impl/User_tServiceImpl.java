package yyt.service.impl;

import org.springframework.stereotype.Service;
import yyt.dao.User_tMapper;
import yyt.model.User_t;
import yyt.service.User_tService;

import javax.annotation.Resource;

/**
 * Created by hadoop on 16-8-2.
 */

@Service
public class User_tServiceImpl implements User_tService {
    @Resource
    private User_tMapper user_tMapper;

    public int insert(User_t user_t) {
        return user_tMapper.insert(user_t);
    }
    public int delete(int id) {
        return user_tMapper.deleteByPrimaryKey(id);
    }
    public User_t getUserById(int userId) {
        return this.user_tMapper.selectByPrimaryKey(userId);
    }

}
