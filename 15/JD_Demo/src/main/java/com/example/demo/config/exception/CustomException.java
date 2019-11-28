package com.example.demo.config.exception;

public class CustomException extends RuntimeException {

    public CustomException() {
        super();
    }
    public CustomException(String message) {
        super(message);
    }
    public CustomException(Throwable cause) {
        super(cause);
    }
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
/*

使用
@Transactional(rollbackFor = Exception.class)

    throw new CustomException("密码长度小于6位");
            throw new CustomException("用户名 " + username + " 已被使用");*/
