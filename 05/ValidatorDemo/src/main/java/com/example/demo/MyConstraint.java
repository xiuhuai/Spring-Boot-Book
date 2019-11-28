package com.example.demo;

import com.example.MyConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//用于指定使用范围，该处限定只能在字段上使用
@Target({ElementType.FIELD})
//表示注解在运行时可以通过反射获取到
@Retention(RetentionPolicy.RUNTIME)
//@Constraint注解，里面传入了一个validatedBy的字段，指定该注解校验逻辑
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
    /**
     * @Description: 错误提示
     */
    String message() default "请输入中国政治或者经济中心的城市名";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
