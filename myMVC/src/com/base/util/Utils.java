package com.base.util;

public class Utils {
    public static String replacePackageName(String packageName){
        return packageName.replaceAll("\\.", "/");
    }
}
