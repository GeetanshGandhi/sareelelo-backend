package com.vastrika.backend.orders.service;

import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.cart.repository.CartItemRepository;
import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.orders.controller.NewOrderData;
import com.vastrika.backend.orders.controller.PlacedOrderData;
import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.orders.repository.OrdersRepository;
import com.vastrika.backend.product.model.Product;
import com.vastrika.backend.product.repository.ProductRepository;
import com.vastrika.backend.productOrdered.model.ProductOrdered;
import com.vastrika.backend.productOrdered.repository.ProductOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductOrderedRepository productOrderedRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public String addOrder(NewOrderData newOrderData){
        List<CartItem> cartItems = newOrderData.getCartItems();
        Customer customer = cartItems.get(0).getCustomer();
        int subTotal = newOrderData.getSubTotal();
        double tax = newOrderData.getTax();
        double grandTotal = newOrderData.getGrandTotal();
        String paymentMethod = newOrderData.getPaymentMethod();

        Orders orders = new Orders(customer, subTotal, tax, grandTotal,
                                    LocalDateTime.now(), "Placed", paymentMethod);

        Orders addedData = ordersRepository.save(orders);
        List<ProductOrdered> productOrderedList = new ArrayList<>();
        for(CartItem curr: cartItems){
            productOrderedList.add(new ProductOrdered(curr.getProduct(),
                                                      addedData,
                                                      curr.getQuantity()));
            //removing product from customer cart
            cartItemRepository.delete(curr);
            //updating quantityAvailable
            Product currProd = productRepository.findById(curr.getProduct().getProductId()).get();
            currProd.setQuantityAvailable(currProd.getQuantityAvailable()-curr.getQuantity());
            productRepository.save(currProd);
        }
        productOrderedRepository.saveAll(productOrderedList);
        return "Success";
    }

    public List<PlacedOrderData> getOrdersByCustomer(Customer customer){
        List<Orders> orders = ordersRepository.findAllByCustomer(customer);
        List<PlacedOrderData> output = new ArrayList<>();
        for(Orders curr: orders){
            List<ProductOrdered> currProdOrdered = productOrderedRepository.findAllByOrders(curr);
            output.add(new PlacedOrderData(curr, currProdOrdered));
        }
        return output;
    }

//    public List<PlacedOrderData> getPendingOrdersForBusiness(Business business){

//    }
}
