package com.pdd.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    /**
     * 处理异常的业务逻辑
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        // 获取到异常信息
        SysException sysException = null;
        if (e instanceof SysException){
            sysException = (SysException) e;
        }else {
            sysException = new SysException("系统正在维护...");
        }
        //创建modelAndView
        ModelAndView view = new ModelAndView();
        view.addObject("errorMsg",e.getMessage());
        view.setViewName("error");
        return view;
    }
}
