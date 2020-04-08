package com.example.demo.controller;


import com.example.demo.util.result.ExceptionMsg;
import com.example.demo.util.result.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
@Controller
public class BaseController {

  /*  ERROR 为严重错误 主要是程序的错误
    WARN 为一般警告，比如session丢失
    INFO 为一般要显示的信息，比如登录登出
    DEBUG 为程序的调试信息
     //logger 	logger.error("异常：",e); logger.info("standard end :"+ getUserId());
    //logger.debug("这是个测试时间{}"+new Date());*/
    protected Logger logger =  LoggerFactory.getLogger(this.getClass());

    protected Response result(ExceptionMsg msg){
        return new Response(msg);
    }
    protected Response result(){
        return new Response();
    }
}
