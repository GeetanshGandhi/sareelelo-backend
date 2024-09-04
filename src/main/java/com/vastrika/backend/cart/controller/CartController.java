package com.vastrika.backend.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.cart.service.CartService;
import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("customer") String customerStr,
                            @RequestParam("product") String productStr,
                            @RequestParam("quantity") String quantity){

        ObjectMapper mapper = new ObjectMapper();
        Customer customer = null;
        Product product = null;
        try{
            customer = mapper.readValue(customerStr, Customer.class);
            product = mapper.readValue(productStr, Product.class);
        } catch(JsonProcessingException e){
            return "request not processed";
        }

        return cartService.addToCart(customer, product, Integer.parseInt(quantity));
    }

    @PostMapping("/getByCustomer")
    public List<CartItem> getCartByCustomer(@RequestBody Customer customer){
        return cartService.getCartItemsByCustomer(customer);
    }
}
