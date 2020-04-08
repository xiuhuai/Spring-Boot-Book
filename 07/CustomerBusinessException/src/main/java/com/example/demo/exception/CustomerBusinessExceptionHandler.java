package com.example.demo.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;
/**
 * 自定义业务处理业务异常类
 */
@ControllerAdvice
public class CustomerBusinessExceptionHandler {
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> businessExceptionHandler(BusinessException e) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", e.getCode());
        map.put("message", e.getMessage());
        //发生异常进行日志记录，此处省略
        return map;
    }
}