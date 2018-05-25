package com.hao.impl.customer;

import com.base.annotation.Service;
import com.hao.service.customer.CustomerServiceI;
@Service("customerService")
public class CustomerDaoImpl implements CustomerServiceI {
    @Override
    public void sayHello() {
        System.out.println("Hello Anno");
    }
}
