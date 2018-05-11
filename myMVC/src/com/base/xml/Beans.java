package com.base.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "scanPackage",
        "view"
})
@XmlRootElement(name = "beans")
public class Beans {
    @XmlElement(name = "scan-package", required = true)
    protected String scanPackage;
    @XmlElement(required = true)
    protected Beans.View view;

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public Beans.View getView() {
        return view;
    }

    public void setView(Beans.View view) {
        this.view = view;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "contentType",
            "prefix",
            "suffix"
    })
    public static class View {
        @XmlElement(name = "content-type", required = true)
        protected String contentType;
        @XmlElement(required = true)
        protected String prefix;
        @XmlElement(required = true)
        protected String suffix;

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
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
    }
}
