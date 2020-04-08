package com.example.demo.util.result;

/**
 * @author longzhonghua
 * @data 2019/01/14 15:30
 */


public class ResultUtil {


    //传参的成功
    public static Result success(Integer code,String msg,Object object){
        Result result=new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(object);
        return result;

    }

    //不传参的成功
    public static Result success(){
        return success(200,"操作成功",null);
    }
    //传参的失败
    public static Result error(Integer code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}

