package yyt.dao;

import yyt.model.User_t;

public interface User_tMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User_t record);

    int insertSelective(User_t record);

    User_t selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User_t record);

    int updateByPrimaryKey(User_t record);
}