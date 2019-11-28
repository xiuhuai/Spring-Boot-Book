package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longzhonghua
 * @createdata 3/18/2019 9:49 PM
 * @description
 */
@ControllerAdvice
public class ExceptionHandlerDemo {
    protected Logger logger =  LoggerFactory.getLogger(this.getClass());
    public static final String viewName = "/error/error";
    /**
     * 404 - Not Found
     */

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object error(){
        Map<String,String> map = new HashMap<>();
        map.put("error", "出错了");
        return map;
    }

}
