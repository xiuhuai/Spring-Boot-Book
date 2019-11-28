package com.example;

import com.example.demo.MyConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {
    //String为校验的类型
    @Override
    public void initialize(MyConstraint myConstraint) {
        //启动时执行
    }

    /**
     * @Description: 自定义校验逻辑
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext validatorContext) {
        if (!(s.equals("北京") || s.equals("上海"))) {
            return false;
        }

        return true;
    }
}
