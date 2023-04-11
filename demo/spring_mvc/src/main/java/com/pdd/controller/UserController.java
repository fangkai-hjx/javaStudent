package com.pdd.controller;

import com.pdd.domain.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString执行了");
        User user = new User();
        user.setUsername("买了");
        user.setPassword("123");
        user.setAge(12);
        model.addAttribute("user",user);
        return "success";
    }

    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1
//        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
        //2
//        response.sendRedirect(request.getContextPath()+"/index.jsp");
        // 3
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("hello你好");
        System.out.println("testVoid方法执行完了。。。");
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        System.out.println("testModelAndView...");
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setUsername("买了");
        user.setPassword("123");
        user.setAge(12);
        mv.addObject("user",user); //底层也是ModelMap，也可以把user对象存入到request对象
        // 你想跳转到哪个页面
        mv.setViewName("success");//其实直接放回字符串到原理也是ModelAndView到方式。
        return mv;
    }

    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        // 请求的转发
//        return "forward:/WEB-INF/pages/success.jsp";
//        return "redirect:/WEB-INF/pages/success.jsp";//注意重定向请求不到web-inf下的文件
        return "redirect:/index.jsp";
    }

    @RequestMapping(value = "/testAjax",method = RequestMethod.POST)
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println(user);
        System.out.println("testAjax执行了");
        // 响应
        user.setUsername("fuck");
        user.setAge(100);
        user.setPassword("12345678");
        return user;
    }

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public String fileUpload(HttpServletRequest req) throws Exception {
        String realPath = req.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(realPath);
        if(!file.exists()){
            //创建文件夹
            file.mkdir();
        }
        //解析1request对象，获取上传的文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload();
        List<FileItem> items = upload.parseRequest(req);
        for (FileItem item : items) {
            // 进行判断，当前item对象是否是上传文件项
            if(item.isFormField()){

            }else{
                String name = item.getName();
                item.write(new File(realPath,name));
                item.delete();
            }
        }
        System.out.println("testAjax执行了");
        return "success";
    }
}
