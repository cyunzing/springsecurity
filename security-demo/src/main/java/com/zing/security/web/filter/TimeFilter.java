package com.zing.security.web.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

//无法拿到controller相关信息
//@Component //不加@Component注解需要自己写配置类
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        System.out.println("time filter 耗时: " + (new Date().getTime() - start) + "ms");
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
