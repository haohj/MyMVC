package com.hao.controller.customer;

import com.base.annotation.Autowired;
import com.base.annotation.Controller;
import com.base.annotation.RequestMapping;
import com.hao.service.customer.CustomerServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller("customerController")
public class CustomerController {
    @Autowired
    private CustomerServiceI customerServiceI;

    @RequestMapping("/sayHello")
    public void sayHello(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        request.setAttribute("name", "张三");
        try {
            response.sendRedirect("/MiniMvc/test/welcome.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
