package com.vastrika.backend.orders.controller;

import com.vastrika.backend.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/add")
    public String addNewOrder(@RequestBody NewOrderData newOrderData){

        return ordersService.addOrder(newOrderData);

    }
}
