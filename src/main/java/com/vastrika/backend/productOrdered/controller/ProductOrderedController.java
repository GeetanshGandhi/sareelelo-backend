package com.vastrika.backend.productOrdered.controller;

import com.vastrika.backend.productOrdered.model.ProductOrdered;
import com.vastrika.backend.productOrdered.service.ProductOrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/prodOrd")
public class ProductOrderedController {

    @Autowired
    ProductOrderedService productOrderedService;

    @PostMapping("/getByBusAndStat")
    public List<ProductOrdered> getByBusAndStat(@RequestParam("ownerEmail") String ownerEmail,
                                                @RequestParam("status") String status){
        return productOrderedService.getByBusinessAndStatus(ownerEmail, status);
    }

    @PostMapping("/updateForBusiness")
    public String dispatchOrderForDelivery(@RequestParam("productId") int productId,
                                           @RequestParam("orderId") int orderId){
        return productOrderedService.dispatchOrderForDelivery(orderId, productId);
    }

    @PostMapping("cancel")
    public String cancelOrder(@RequestParam("orderId") int orderId,
                              @RequestParam("productId") int productId,
                              @RequestParam("reason") String reason){
        return productOrderedService.cancelOrder(orderId, productId, reason);
    }
}
