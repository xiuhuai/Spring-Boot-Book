package com.example.demo.Servlet.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//作用范围
@WebFilter(urlPatterns = "/*")
public class FilterDemo01 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            System.out.println("拦截器");
            filterChain.doFilter(servletRequest,servletResponse);
    }
 
    @Override
    public void destroy() {
 
    }
}
