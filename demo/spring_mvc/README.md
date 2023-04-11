第一天：MyBatis入门：

- mybatis的概述
- mybatis的环境搭建
- mybatis入门案例
- 自定义mybatis框架(主要目的是为了让大家了解mybatis中执行细节)

第二天：Mybatis基本使用

- mybatis的单表crud操作
- mybatis的参数和返回值
- mybatis的dao编写
- mybatis配置的细节，几个标签的使用

第三天：mybatis的深入和多表

- mybatis的连接池
- mybatis的事务控制及设计的方法
- mybatis的多表查询，一对多。

第四天： mybatis的缓存和注解开发

- mybatis中的加载时机
- mybatis中的一级缓存和二级缓存
- mybatis的注解开发，单表crud，多表查询。

---

持久层技术解决方案：

JDBC技术：Connection PreparedStatement ResultSet

Spring多JdbcTemplate：Spring对jdbc的简单封装。

Apache的DBUtils：同上

以上都不是框架：JDBC是规范，其他两个是工具类。

Mybatis概述：是一个持久层框架，java语言编写，封装了jdbc操作的许多细节，使开发者只关注sql语句本身，而无需关注注册驱动，创建连接等复杂过程。它使用了orm思想实现了结果集的封装。

propertes标签，typeAliases标签，maappers标签





----

spring中ioc的常用注解

---



Spring MVC注解：

```java
@RequestParams注解：把请求中指定名称的参数传递给控制器中的形参。

@RequestBody注解：用于获取请求体的内容，直接使用得到的是key=value&key=value...结构的数据 。注get请求不适用。

@PathVariable:绑定url中的占位符 /delete/{id} REST风格
  
@RequestHeader：用于获取请求消息头
  
@CookieValue：

@ModelAttribute：1.作用在方法上，当前方法回在控制器的方法执行之前，先执。它可以修饰没有放回值的方法，也可以修饰具体放回值的方法。2.作用在参数上，获取指定的数据给参数赋值。应用场景：当表单提交数据不是完整的实体类数据时，保证没有提交数据的字段使用数据库对象原来的数据。
  
@SessionAtribute:用于多次执行控制器方法间的参数共享
  
```

放回值

1.返回字符串：会被视图解析器跳转到页面

2 放回void：默认会跳转到路径的文件/a/b --- a/b.jsp

```java
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
```

3放回值是ModelAndView：Spring提供的一个对象，可以调整jsp视图。

```java
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
```

4 用关键字进行转发和重定向

无法使用视图解析器

```java
 @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        // 请求的转发
//        return "forward:/WEB-INF/pages/success.jsp";
//        return "redirect:/WEB-INF/pages/success.jsp";//注意重定向请求不到web-inf下的文件
        return "redirect:/index.jsp"; // 注意关键字的方式这里框架帮你加好了虚拟路径
    }
```

5 响应json数据

1 DispatcherServlet会拦截到所有的资源，导致一个问题就是静态资源(img，css，js)也会被拦截到，从而不能被使用，解决问题就是需要配置静态资源都进行拦截，在spring.xml配置文件添加如下配置。

​		1.mvc:resource标签配置不过滤

```java
<!--前端控制器：哪些静态资源不拦截 location:请求路径-->
    <mvc:resources location="/js/" mapping="/js/**"></mvc:resources>
    <mvc:resources location="/images/" mapping="/images/**"></mvc:resources>
    <mvc:resources location="/css/" mapping="/css/**"></mvc:resources>
```



----

文件上传
