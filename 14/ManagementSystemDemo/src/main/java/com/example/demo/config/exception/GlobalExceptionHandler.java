package com.example.demo.config.exception;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    // 日志记录工具
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //错误显示页面,即 /public/error/error.html 文件
    public static final String viewName = "/error/error";
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException e, Model model) {
        logger.error("缺少请求参数", e);
        String message = "【缺少请求参数】" + e.getMessage();
        model.addAttribute("message", message);
        model.addAttribute("code", 400);
        return viewName;
    }
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e, Model model) {
        logger.error("参数解析失败", e);
        String message = "【参数解析失败】" + e.getMessage();
        model.addAttribute("message", message);
        model.addAttribute("code", 400);
        return viewName;
    }
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e, Model model) {
        logger.error("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = "【参数验证失败】" + String.format("%s:%s", field, code);
        model.addAttribute("message", message);
        model.addAttribute("code", 400);
        return viewName;
    }
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException e, Model model) {
        logger.error("参数绑定失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = "【参数绑定失败】" + String.format("%s:%s", field, code);
        model.addAttribute("message", message);
        model.addAttribute("code", 400);
        return viewName;
    }
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleServiceException(ConstraintViolationException e, Model model) {
        logger.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = "【参数验证失败】" + violation.getMessage();
        model.addAttribute("message", message);
        model.addAttribute("code", 400);
        return viewName;
    }
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException e, Model model) {
        logger.error("参数验证失败", e);
        String message = "【参数验证失败】" + e.getMessage();
        model.addAttribute("message", message);
        model.addAttribute("code", 400);
        return viewName;
    }
    /**
     * 403 - FORBIDDEN
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessException(AccessDeniedException e, Model model) {
        logger.error("禁止访问", e);
        String message = "【禁止访问】" + e.getMessage();
        model.addAttribute("message", message);
        model.addAttribute("code", 403);
        return viewName;
    }
    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandlerFoundException(NoHandlerFoundException e, Model model) {
        logger.error("Not Found", e);
        String message = "【页面不存在】" + e.getMessage();
        model.addAttribute("message", message);
        model.addAttribute("code", 404);
        System.out.println("404404错误");
        return viewName;
    }
    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, Model model) {
        logger.error("不支持当前请求方法", e);
        String message = "【不支持当前请求方法】" + e.getMessage();
        model.addAttribute("message", message);
        model.addAttribute("code", 405);
        return viewName;
    }
    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, Model model) {
        logger.error("不支持当前媒体类型", e);
        String message = "【不支持当前媒体类型】" + e.getMessage();
        model.addAttribute("message", message);
        model.addAttribute("code", 415);
        return viewName;
    }
    /**
     * 业务层需要自己声明异常的情况
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(ServiceException e, Model model) {
        logger.error("业务逻辑异常", e);
        String message = "【业务逻辑异常】" + e.getMessage();
        model.addAttribute("message", message);
        return viewName;
    }
    /**
     * 获取其它异常。包括500
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Exception e, Model model) {
        logger.error("Exception", e);
        String message = e.getMessage();
        model.addAttribute("message", message);
        model.addAttribute("code", 500);
        return viewName;
    }
}