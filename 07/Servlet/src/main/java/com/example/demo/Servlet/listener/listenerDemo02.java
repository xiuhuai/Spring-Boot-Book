package com.example.demo.Servlet.listener;
 

 
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
@WebListener

public class listenerDemo02 implements ServletContextListener{
	    @Override
	    public void contextInitialized(ServletContextEvent servletContextEvent) {
	        System.out.println("ServletContex初始化");
	       System.out.println(servletContextEvent.getServletContext().getServerInfo());
	    }

	    @Override
	    public void contextDestroyed(ServletContextEvent servletContextEvent) {
	        System.out.println("ServletContex销毁");
	    }
	}
	