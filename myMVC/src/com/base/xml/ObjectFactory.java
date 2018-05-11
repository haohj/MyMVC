package com.base.xml;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    public Beans createBeans() {
        return new Beans();
    }

    public Beans.View createBeansView() {
        return new Beans.View();
    }
}
