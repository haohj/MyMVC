package com.hao.controller.customer;

import com.base.annotation.Autowired;
import com.base.annotation.Controller;
import com.hao.service.customer.CustomerServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("customerController")
public class CustomerController {
    @Autowired
    private CustomerServiceI customerServiceI;

    public void sayHello(HttpServletRequest request, HttpServletResponse response) {

    }
}
