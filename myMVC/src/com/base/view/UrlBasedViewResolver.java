package com.base.view;

import com.base.xml.XmlParse;

public class UrlBasedViewResolver {
    //redirect方式跳转的URL的前缀
    private static final String REDIRECT_URL_PREFIX = "redirect:";
    //forward方式跳转的URL的前缀
    private static final String FORWARD_URL_PREFIX = "forward:";
    //视图文件的类型
    private String contentTypeString;
    //视图文件的前缀
    private String prefix = "";
    //视图文件的后缀
    private String suffix = "";

    public String getContentTypeString() {
        return contentTypeString;
    }

    public void setContentTypeString(String contentTypeString) {
        this.contentTypeString = contentTypeString;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String createView(String viewName) {
        XmlParse xmlParse = new XmlParse();
        String realPath = "";
        if (viewName != null && !viewName.equals("")) {
            realPath = REDIRECT_URL_PREFIX + prefix + viewName + suffix;
        }
        return realPath;

    }
}
