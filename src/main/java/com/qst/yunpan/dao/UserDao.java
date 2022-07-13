package com.qst.yunpan.dao;

import com.qst.yunpan.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 用户登录接口
     * @param username  用户名
     * @return
     */
    User Login(String username);

    /**
     * 用户注册接口
     * @param user  用户数据
     * @return
     */
    Integer register(User user);

    /**
     * 
     * @param username
     * @param formatSize
     * @throws Exception
     */
    void reSize(@Param("username") String username, @Param("formatSize") String formatSize) throws Exception;

    String getCountSize(String username);

    User findUserByUserName(String username);

    void addUser(User user);

    User findUser(User user);
}
