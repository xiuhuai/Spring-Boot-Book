package com.example.demo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author longzhonghua
 * @createdata 3/18/2019 7:59 AM
 * @description
 */

/**
 * Description: 添加注解进行修饰
 */
@WebServlet(urlPatterns = "/ServletDemo02/*")
public class ServletDemo02 extends HttpServlet{
    /**
     * Description:
     * 重写doGet方法,父类的HttpServlet的doGet方法是空的，没有实现任何代码，子类需要重写此方法。
     *客户使用GET方式请求Servlet时，Web容器调用doGet方法处理请求。
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        resp.getWriter().print("Servlet ServletDemo02");
    }
}
