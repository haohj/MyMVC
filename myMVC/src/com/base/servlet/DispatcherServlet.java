package com.base.servlet;

import java.net.URL;

public class DispatcherServlet {
    public String replacePackageName(String packageName) {
        return packageName.replaceAll("\\.", "/");
    }

    public void scanPackage(String packageName){
        String packageName1 = replacePackageName(packageName);
        URL url = this.getClass().getClassLoader().getResource(packageName1);
        String pathFile = url.getFile();
    }
}
