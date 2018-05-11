package com.base.util;

import com.base.xml.Beans;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MapAndJavaBeanCovertUtils {
    /**
     * javabean对象转换为map对象
     *
     * @param bean 要转换的对象
     * @return 转换后的map
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Beans bean) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (bean == null) {
            return null;
        }
        //反射获取该类的所有属性
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            //设置私有属性可以操作
            field.setAccessible(true);
            //获取属性名称
            String name = field.getName();
            //获取属性的值
            Object value = field.get(bean);
            map.put(name, value);
        }
        return map;
    }

    /**
     * 将map对象装换为指定的Java类对象
     *
     * @param map   要转行的map
     * @param clazz 要转为的Java对象的class
     * @return 转换完成的对象
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> clazz) throws Exception {
        if (map.size() <= 0) {
            return null;
        }
        //获取对象
        Object object = clazz.newInstance();
        //获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //获取属性的限定符
            int mod = field.getModifiers();
            //判断是否为static 和 final
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            //设置field可操作
            field.setAccessible(true);
            //给属性赋值
            field.set(object, map.get(field.getName()));
        }

        return object;
    }
}
