package com.ruoyi.common.xss;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义xss校验注解
 * 
 * @author ruoyi
 */
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时保留
@Target(value = { ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER }) // 注解可以应用于方法、字段、构造函数和参数
/* @Constraint注解用于指定一个注解的实现类，该实现类必须实现javax.validation.ConstraintValidator接口 */
@Constraint(validatedBy = { XssValidator.class }) // 指定用于验证的类，当运行时，会调用XssValidator的isValid方法，进行校验
public @interface Xss
{
    String message() // 定义错误消息的方法

            default "不允许任何脚本运行"; // 默认错误消息

    Class<?>[] groups() default {}; // 分组信息

    Class<? extends Payload>[] payload() default {}; // 负载信息
}
