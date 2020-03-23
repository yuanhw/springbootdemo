package com.example.demo.resource;

import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CustomerController
 *
 * @author Wang Yuanhang
 * @date 2020/3/21 1:05 下午
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @GetMapping("getCustomerInfo")
    public Customer getCustomerInfo(@RequestParam Long id) {
        return customerMapper.selectById(id);
    }

    @PostMapping("addCustomerInfo")
    public Customer addCustomerInfo(@ModelAttribute Customer customer) {
        this.customerMapper.insert(customer);
        return this.customerMapper.selectById(customer.getId());
    }

    @PostMapping("updateCustomerInfoById")
    public Customer updateCustomerInfoById(@ModelAttribute Customer customer, @RequestParam Long id) {
        customer.setId(id);
        this.customerMapper.updateById(customer);
        return this.customerMapper.selectById(id);
    }
}
