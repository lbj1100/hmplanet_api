package com.ruoyi.framework.aspectj;

import java.util.Arrays;
import java.util.Objects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.datasource.DynamicDataSourceContextHolder;

/**
 * 多数据源处理
 * 
 * @author ruoyi
 */
// @Aspect: 这个注解用来描述一个切面类，定义切面类的时候需要打上这个注解
@Aspect
// @Order: 这个注解用来描述多个切面类的执行顺序，值越小优先级越高
@Order(1)
// @Component: 这个注解用来描述一个组件类，定义组件类的时候需要打上这个注解
@Component
public class DataSourceAspect
{

    protected Logger logger = LoggerFactory.getLogger(getClass());

    // @Pointcut: 这个注解用来描述一个切入点，定义一个方法，这个方法就是一个切入点
    // 这个切入点描述了什么时候执行切面的逻辑
    // 当目标方法上有@DataSource注解或者目标方法所在的类上有@DataSource注解时，切面逻辑会被执行
    @Pointcut("@annotation(com.ruoyi.common.annotation.DataSource)"
            + "|| @within(com.ruoyi.common.annotation.DataSource)")
    public void dsPointCut()
    {

    }

    // @Around: 这个注解用来描述一个环绕通知，环绕通知是在目标方法执行之前和之后都会执行的通知
    // dsPointCut(): 这个是切入点表达式，表示切入点是dsPointCut()方法
    // 切入点表达式的语法: execution([访问修饰符] 返回类型 [声明类型].方法名(参数) [异常])
    // 切入点的作用: 用来描述哪些方法需要被增强
    // ProceedingJoinPoint: 这个是连接点，表示目标方法的连接点
    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        DataSource dataSource = getDataSource(point);

        // TODO 获取参数名为url的参数
//        Object[] args = point.getArgs();
//        Object arg = args[args.length - 1];
        // 判断参数是否是String类型
//        if (arg instanceof String)
//        {
//            String url = (String) arg;
//            // 判断url是否包含"jdbc:mysql://"
//            if (url.contains("jdbc:mysql://"))
//            {
//                // 判断url是否包含"serverTimezone"
//                if (!url.contains("serverTimezone"))
//                {
//                    // 如果不包含"serverTimezone"，则在url后面加上"serverTimezone=Asia/Shanghai"
//                    url += "?serverTimezone=Asia/Shanghai";
//                    args[args.length - 1] = url;
//                }
//            }
//        }


        if (StringUtils.isNotNull(dataSource))
        {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }

        try
        {
            return point.proceed();
        }
        finally
        {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public DataSource getDataSource(ProceedingJoinPoint point)
    {
        //
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataSource dataSource = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
        // 如果方法上的注解为空，则获取类上的DataSource注解
        if (Objects.nonNull(dataSource))
        {
            return dataSource;
        }

        // signature.getDeclaringType(): 获取声明方法的类
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
    }
}
