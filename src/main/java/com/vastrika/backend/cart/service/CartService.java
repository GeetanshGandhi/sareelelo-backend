package com.vastrika.backend.cart.service;

import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.cart.repository.CartItemRepository;
import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartItemRepository cartItemRepository;

    public String addToCart(Customer customer, Product product, int quantity){
        cartItemRepository.save(new CartItem(customer, product, quantity));
        return "Success";
    }

    public List<CartItem> getCartItemsByCustomer(Customer customer){
        return cartItemRepository.findAllByCustomer(customer);
    }
}
