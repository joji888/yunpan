package com.qst.yunpan.controller;

import com.qst.yunpan.pojo.User;
import com.qst.yunpan.service.impl.UserServiceImpl;
import com.qst.yunpan.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserServiceImpl userService;
    /**
     * 主页面页面
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        String username = UserUtils.getUsername(request);
        User user = userService.findUser(username);
        String countSize = user.getCountSize();
        String totalSize = user.getTotalSize();
        request.setAttribute("countSize", countSize);
        request.setAttribute("totalSize", totalSize);
        return "index";
    }
}
