package com.ruoyi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.ruoyi.common.enums.DataSourceType;

/**
 * 自定义多数据源切换注解
 *
 * 优先级：先方法，后类，如果方法覆盖了类上的数据源类型，以方法的为准，否则以类上的为准
 *
 * @author ruoyi
 */
// @Target: 这个注解用来描述一个注解可以被放在哪里
@Target({ ElementType.METHOD, ElementType.TYPE })
// @Retention: 这个注解用来描述一个注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
// @Documented: 这个注解用来描述一个注解是否被抽取到文档中
@Documented
// @Inherited: 这个注解用来描述一个注解是否被子类继承
@Inherited
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
