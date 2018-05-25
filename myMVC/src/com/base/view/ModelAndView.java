package com.base.view;

import java.util.Map;

public class ModelAndView {
    private Object view;
    private Map<String, Object> map;

    public ModelAndView(Object view, Map<String, Object> map) {
        this.view = view;
        this.map = map;
    }

    public ModelAndView(Object view) {
        this.view = view;
    }

    public ModelAndView() {
        super();
    }

    public Object getView() {
        return view;
    }

    public void setView(Object view) {
        this.view = view;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String redirectView() {
        String realpath = "";
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {

            }
        } else {
            UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
            realpath = urlBasedViewResolver.createView(view.toString());
        }
        return realpath;
    }
}
