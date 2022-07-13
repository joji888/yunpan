package com.qst.yunpan.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // 执行完毕，返回前拦截
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // 在处理过程中，执行拦截
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // 在拦截点执行前拦截，如果返回true则不执行拦截点后的操作（拦截成功）
        // 返回false则不执行拦截
        String url = request.getRequestURI(); // 获取登录的uri，这个是不进行拦截的
        System.out.println(url);
        if ("/yunpan/user/login.action".equals(url)){
            return true;
        }
        if ("/yunpan/user/regist.action".equals(url)){
            return true;
        }
        if ("/yunpan/share.action".equals(url)){
            return true;
        }
        if ("/yunpan/file/download.action".equals(url)){
            return true;
        }
        
        HttpSession session = request.getSession();
        if(session.getAttribute("username")!=null && session.getAttribute("username") != "") {
            // 登录成功不拦截
            return true;
        }else {
            // 拦截后进入登录页面
            response.sendRedirect(request.getContextPath()+"/");
            return false;
        }
    }
}
