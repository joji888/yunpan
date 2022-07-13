package com.qst.yunpan.service.impl;

import com.qst.yunpan.dao.UserDao;
import com.qst.yunpan.pojo.User;
import com.qst.yunpan.service.FileService;
import com.qst.yunpan.utils.UserUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl {
    
    private final UserDao userDao;
    private final FileService fileService;

    public UserServiceImpl(UserDao userDao,FileService fileService) {
        this.userDao = userDao;
        this.fileService = fileService;
    }

    /**
     * 用户登录实现
     * @param username  用户名
     * @param password  用户密码
     * @return
     */
    public String Login(String username, String password, HttpServletRequest request) {
        User user = userDao.Login(username);
        if (user==null){
            request.setAttribute("msg","账号或者密码不正确");
            return "login";
        }
        if (user.getPassword().equals(UserUtils.MD5(password))){
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            request.setAttribute("msg","登录成功");
            fileService.reSize(request);//初始化一下用户的空间大小
            return "redirect:/index.action";
        }
        request.setAttribute("msg","账号或者密码不正确");
        return "login";
    }

    /**
     * 用户注册实现
     * @param user  用户数据
     * @param request
     * @return
     */
    public String register(User user, HttpServletRequest request) {
        if (user.getUsername()==null||user.getUsername().equals("")){
            return "regist";
        }
        User login = userDao.Login(user.getUsername());
        if (login!=null){
            request.setAttribute("msg","注册失败，该用户已经存在");
            return "regist";
        }
        user.setPassword(UserUtils.MD5(user.getPassword()));
        user.setCountSize("0.0M");
        user.setTotalSize("1.0GB");
        Integer register = userDao.register(user);
        if (register>0){
            fileService.addNewNameSpace(request, user.getUsername());
            return "login";
        }else {
            request.setAttribute("msg","注册失败，数据库操作有误");
            return "regist";
        }
    }

    /**
     * 用户登出实现
     * @return
     */
    public String logout() {
        return null;
    }


    public User findUser(User user) {
        try {
            user.setPassword(UserUtils.MD5(user.getPassword()));
            User exsitUser = userDao.findUser(user);
            return exsitUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user){
        try {
            user.setPassword(UserUtils.MD5(user.getPassword()));
            userDao.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User findUser(String username) {
        User user = null;
        try {
            user = userDao.findUserByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
            return user;
        }
        return user;
    }

    public String getCountSize(String username){
        String countSize = null;
        try {
            countSize = userDao.getCountSize(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return countSize;
    }
}
