package com.pdd.controller;

import com.pdd.domain.Account;
import com.pdd.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/param")
public class ParamController {

    @RequestMapping("/testParams")
    public String testParams(String username,String password){
        System.out.println("test执行了:"+username+":"+password);
        return "success";
    }

    @RequestMapping("/saveAccount")
    public String saveAccount(Account account){
        System.out.println(account);
        return "success";
    }

    @RequestMapping("/saveUser")
    public String saveUser(User user){
        System.out.println(user);
        return "success";
    }


    @RequestMapping("/testServlet")
    public String saveUser(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request);
        System.out.println(response);
        return "success";
    }
}
