package com.vastrika.backend.cart.service;

import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.cart.repository.CartItemRepository;
import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class CartService {

    @Autowired
    CartItemRepository cartItemRepository;

    public String addToCart(Customer customer, Product product){
        cartItemRepository.save(new CartItem(customer,product));
    }
}
