package com.aiyuedu.demo1.controller;

import com.aiyuedu.demo1.entity.User;
import com.aiyuedu.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/*")
public class UserController {
    @Autowired

    private UserService userService;
    @RequestMapping("/")
    public String index(){

        return "index";
    }
    @RequestMapping("/success")
    public String success(){

        return "success";
    }
    /**
     * 去注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {

        return "register";
    }

    /**
     * 执行注册 成功后登录页面 否则调回注册页面
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/doregister")
    public String register(HttpServletRequest request, User user) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (password.equals(password2)) {
            if (registerUser(username) == true) {
                User user1 = new User();
                user1.setUsername(username);
                user1.setPassword(password);
                userService.save(user1);
                return "login";
            } else {
                return "register";
            }
        } else {
            return "register";
        }
    }

    public Boolean registerUser(String username) {
        Boolean a = true;
        if (userService.findByName(username).isEmpty()) {
            return a;
        } else {
            return false;
        }
    }

    /**
     * 去登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    /**
     * 执行登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/dologin")
    public String dologin(HttpServletRequest request, User user,
                          Map<String ,Object> map,
                          HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user = userService.FindNameAndPsw(username, password);
        if (user != null) {
            session.setAttribute("loginUser",username);
            return "redirect:/ok.html";
        } else {
            map.put("msg","用户名密码错误");
            return "login";

        }

    }


}
