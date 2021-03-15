package com.yolo.stock.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestInterceptor extends HandlerInterceptorAdapter {
    // 在controller调用之前调用，通过返回true或者false决定是否进入Controller层
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("preHandlessssss");
//        request.setAttribute("startTime", new Date().getTime());
        return true;
    }

//    // 在请求进入控制层之后调用，但是在处理请求抛出异常时不会调用
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle");
//        Long start = (Long)request.getAttribute("startTime");
//        System.out.println("time interceptor 耗时:"+ (new Date().getTime() - start));
//    }

    // 在请求处理完成之后，也就是在DispatherServlet渲染了视图之后执行，也就是说这个方法必定是执行，包含异常信息，它的主要作用就是清理资源
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println("afterCompletionsssss");
//        Long start = (Long) request.getAttribute("startTime");
//        System.out.println("time interceptor 耗时:"+ (new Date().getTime() - start));
//        System.out.println("ex is "+ex);
    }
}
