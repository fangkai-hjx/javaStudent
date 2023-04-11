package com.pdd.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 控制器类
 */
@Controller
public class HelloController {

    @RequestMapping(path = "/hello")
    public String sayHello() {
        System.out.println("Hello SpringMVC");
        return "success";
    }

    @RequestMapping(value = "/getSessionAttribute")
    public String getSessionAttribute(ModelMap mp,@SessionAttribute("msg") String msg) {
        System.out.println(msg);
        System.out.println("getSessionAttribute执行了........");
        return "success";
    }
}
