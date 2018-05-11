package com.hao.test;

import com.base.servlet.DispatcherServlet;
import org.junit.Test;

import java.net.URL;

public class DispatcherServletTest {
    @Test
    public void test1() {
        // 当前类(class)所在的包目录
        System.out.println(this.getClass().getResource(""));
        // class path根目录
        System.out.println(this.getClass().getResource("/"));

        // this.class在<bin>/testpackage包中
        // 3.properties  在<bin>/testpackage.subpackage包中
        System.out.println(this.getClass().getResource("../../base/servlet/DispatcherServlet.class"));

        URL url = this.getClass().getClassLoader().getResource("com/base/base/servlet/");
        System.out.println(url);
        //String pathFile = url.getFile();
        //System.out.println(pathFile);
    }
}
