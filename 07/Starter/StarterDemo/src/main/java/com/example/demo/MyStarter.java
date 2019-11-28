package com.example.demo;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: MyStarterService
 * Author:   longzhonghua
 * Date:     5/20/2019 3:21 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MyStarter {
    private MyStarterProperties myproperties;
    public MyStarter() {
    }
    public MyStarter (MyStarterProperties  myproperties) {
        this. myproperties = myproperties;
    }
    public String print(){
        System.out.println("参数: " + myproperties.getParameter());
        String s=myproperties.getParameter();
        return s;
    }
}
