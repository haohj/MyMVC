package com.base.servlet;

import com.base.annotation.Controller;
import com.base.annotation.RequestMapping;
import com.base.annotation.Service;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherServlet {

    private List<String> packageNames = new ArrayList<String>();
    private Map<String, Object> classMap = new HashMap<String, Object>();
    private Map<String,Object> methodMap = new HashMap<String, Object>();

    public String replacePackageName(String packageName) {
        return packageName.replaceAll("\\.", "/");
    }

    public void scanPackage(String packageName) {
        //替换包名中的 .
        String packageName1 = replacePackageName(packageName);
        //获取当前的统一资源定位符
        URL url = this.getClass().getClassLoader().getResource(packageName1);
        //获取此URL的文件名
        String pathFile = url.getFile();
        //通过将给定路径名字符串转换成抽象路径名来创建一个新 File 实例
        File file = new File(pathFile);
        //返回由此抽象路径名所表示的目录中的文件和目录的名称所组成字符串数组
        String[] files = file.list();

        for (String filePath : files) {
            //根据目录名称和文件名称创建新的File对象
            File eachFile = new File(pathFile + "/" + filePath);
            if (eachFile.isDirectory()) {
                //如果为文件夹，则将新的名称作为包名，再次调用该方法
                scanPackage(packageName + "." + eachFile.getName());
            } else {
                //判断文件的扩展名是否为class，如果是，则将该文件的包名和文件名一起保存到集合中
                if (isClassFile(eachFile.getName())) {
                    packageNames.add(packageName + "." + filePath);
                }
                continue;
            }
        }
    }

    private boolean isClassFile(String name) {
        if (name == null || name.length() <= 0) {
            return false;
        } else {
            int lastIndex = name.lastIndexOf(".");

            if (lastIndex > -1 && lastIndex < (name.length() - 1)) {
                return name.substring(lastIndex + 1).equals("class");
            }
            return false;
        }
    }

    public void saveClass() throws Exception {
        if (packageNames.isEmpty()) {
            return;
        }

        for (String pkn : packageNames) {
            //根据类的全限定名通过反射获取指定类的class对象
            Class<?> object = Class.forName(pkn.replace(".class", "").trim());
            //判断该类是否有controller注解
            if (object.isAnnotationPresent(Controller.class)) {
                //实例化该class的类对象
                Object instance = object.newInstance();
                //获取controller注解的对象
                Controller controller = object.getAnnotation(Controller.class);
                //获取该注解的参数的值
                String key = controller.value();
                //判断注解的值是否为空
                if (key.equals("")) {
                    //获取该类的类名首字母小写作为值。
                    key = object.getSimpleName().substring(0, 1).toLowerCase() + object.getSimpleName().substring(1);
                }
                //将类的名称和类的实例放到map对象中
                classMap.put(key, instance);
            } else if (object.isAnnotationPresent(Service.class)) {
                Object instance = object.newInstance();
                Service service = object.getAnnotation(Service.class);
                String key = service.value();
                if (key.equals("")) {
                    key = object.getSimpleName().substring(0, 1).toLowerCase() + object.getSimpleName().substring(1);
                }
                classMap.put(key, instance);
            } else {
                continue;
            }
        }
    }

    public void handlerMap() {
        //判断类的集合是否为空
        if (classMap.size() <= 0) {
            return;
        }

        for (Map.Entry<String, Object> entry : classMap.entrySet()) {
            //获取controller注解对象
            if (entry.getValue().getClass().isAnnotationPresent(Controller.class)) {
                Controller controller = entry.getValue().getClass().getAnnotation(Controller.class);
                //获取controller注解的值
                String value = controller.value();
                //判断注解的值是否为空
                if (value.equals("")) {
                    //获取该类的类名首字母小写作为值
                    value = entry.getValue().getClass().getSimpleName().substring(0, 1).toLowerCase() + entry.getValue().getClass().getSimpleName().substring(1);
                }
                //反射获取该类下面的所有方法
                Method[] methods = entry.getValue().getClass().getMethods();
                for (Method method : methods) {
                    //判断该方法是否有RequestMapping注解
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        //获取RequestMapping对象
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        //获取注解的值
                        String rv = requestMapping.value();
                        //判断注解的值是否为空
                        if (rv.equals("")) {
                            //获取该类的类名首字母小写作为值
                            rv = method.getClass().getSimpleName().substring(0, 1).toLowerCase() + method.getClass().getSimpleName().substring(1);
                        }
                        //将controller的名称和方法的名称保存到集合中
                        methodMap.put("/" + value + rv, method);
                    }else {
                        continue;
                    }
                }
            }else {
                continue;
            }
        }
    }

    public void ioc(){
        if (classMap.size() <= 0) {
            return;
        }

    }
}
