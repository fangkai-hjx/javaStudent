package com.pdd.controller;

import com.pdd.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器类
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value={"msg"}) // 把msg=妹妹存放到session域
public class AnnoController {

    @RequestMapping(path = "/testRequestParam")
    public String testRequestParam(@RequestParam(name = "username", required = false) String name) {
        System.out.println("Hello SpringMVC:" + name);
        return "success";
    }

    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("Hello SpringMVC:" + body);
        return "success";
    }

    @RequestMapping(value = "/testPathVariable/{sid}/test", method = RequestMethod.POST)
    public String testPathVariable(@PathVariable(name = "sid") String id) {
        System.out.println("Hello SpringMVC:" + id);
        return "success";
    }

    @RequestMapping(value = "/testPathVariable/{sid}", method = RequestMethod.GET)
    public String testRequestHeader(@PathVariable(name = "sid") String id, @RequestHeader(value = "Accept") String header) {
        System.out.println("Hello SpringMVC:" + id + ",header:" + header);
        return "success";
    }

    /**
     * 获取cookie的值
     *
     */
    @RequestMapping(value = "/testCookieValue/{sid}", method = RequestMethod.GET)
    public String testCookieValue(@PathVariable(name = "sid") String id, @CookieValue("JSESSIONID") String cookieValue) {
        System.out.println("Hello SpringMVC:" + id + ",cookieValue:" + cookieValue);
        return "success";
    }

    @RequestMapping(value = "/testModelAttribute")
    public String testModelAttribute(User user) {
        System.out.println("Hello SpringMVC：" + user);
        return "success";
    }

    /**
     * 该方法回先执行
     * 有放回值
     */
//    @ModelAttribute
//    public User showUser(String username) {
//        System.out.println("showUser执行了");
//        // 这里模拟从数据库查询用户信息
//        User user = new User();
//        user.setUsername(username);
//        user.setAge(12);
//        user.setPassword("123");
//        return user;
//    }


    @RequestMapping(value = "/testModelAttribute2", method = RequestMethod.POST)
    public String testModelAttribute2(@ModelAttribute("abc") User user) {
        System.out.println("Hello SpringMVC：" + user);
        return "success";
    }

    @ModelAttribute
    public void showUser(String username, Map<String,User> map) {
        System.out.println("showUser执行了");
        // 这里模拟从数据库查询用户信息
        User user = new User();
        user.setUsername(username);
        user.setAge(12);
        user.setPassword("123");
        map.put("abc",user);
    }


    @RequestMapping(value = "/testSessionAttribute")
    public String testSessionAttribute(Model model){
        model.addAttribute("msg","妹妹");
        System.out.println("testSessionAttribute执行了........");
        return "success";
    }

    @RequestMapping(value = "/getSessionAttribute")
    public String getSessionAttribute(ModelMap mp){
        System.out.println(mp.get("msg"));
        System.out.println("getSessionAttribute执行了........");
        return "success";
    }
}
