package com.base.servlet;

import java.awt.peer.ChoicePeer;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet {

    private List<String> packageNames = new ArrayList<String>();

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
}
