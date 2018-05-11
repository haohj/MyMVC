package com.base.annotation;

import java.lang.annotation.*;

@Documented//会将该注解回写到javadoc文件中
@Target(ElementType.TYPE)//限制注解使用的位置，里面的参数表示只能在类或接口上使用
@Inherited//表示本注解可以被子类继承
@Retention(RetentionPolicy.RUNTIME)//表示本注解会在什么时候被抛弃，参数表示在VM中将保存该注解，可以通过反射获取注解信息
public @interface Controller {
    String value() default "";
}
