
package com.example.demo.util.result;

public enum ExceptionMsg {
	SUCCESS("200", "操作成功"),
	FAILED("999999","操作失败"),
    ParamError("000001", "参数错误！"),
    LoginNameOrPassWordError("000100", "用户名或者密码错误！"),
    EmailUsed("000101","该邮箱已被注册"),
    UserNameUsed("000102","该登录名称已存在"),
    EmailNotRegister("000103","该邮箱地址未注册"),
    PassWordError("000105","密码输入错误"),
    LoginNameNotExists("000107","该用户未注册"),
    UserNameSame("000108","新用户名与原用户名一致"),
    MobileUsed("000109","该手机号已被注册"),
    FileEmpty("000500","上传文件为空"),
    LimitPictureSize("000501","图片大小必须小于2M"),
    LimitPictureType("000502","图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'")
    ;
   private ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;
    
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

    
}

