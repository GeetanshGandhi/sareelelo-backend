package com.vastrika.backend.productOrdered.service;

import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.orders.repository.OrdersRepository;
import com.vastrika.backend.product.model.Product;
import com.vastrika.backend.product.repository.ProductRepository;
import com.vastrika.backend.productOrdered.model.ProductOrdered;
import com.vastrika.backend.productOrdered.repository.ProductOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderedService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductOrderedRepository productOrderedRepository;

    @Autowired
    OrdersRepository ordersRepository;
    public List<ProductOrdered> getByBusinessAndStatus(String ownerEmail, String status){
        return productOrderedRepository.findAllByBusinessAndStatus(ownerEmail, status);
    }

    public String dispatchOrderForDelivery(int orderId, int productId){
        ProductOrdered oldItem = productOrderedRepository.findByProductAndOrder(productId, orderId);
        oldItem.setStatus("Packed");
        oldItem.setRemark("Order is Packed by the Retailer");
        productOrderedRepository.save(oldItem);
        return "Success";
    }

    public String cancelOrder(int orderId, int productId, String reason){
        ProductOrdered item = productOrderedRepository.findByProductAndOrder(productId, orderId);
        if(item.getStatus().equals("Placed") || item.getStatus().equals("Packed")){
            //updating quantity available of product
            Product product = productRepository.findById(productId).get();
            product.setQuantityAvailable(product.getQuantityAvailable()+item.getQuantity());
            //changes in productOrdered record
            item.setStatus("Cancelled");
            String newRemark = "Order is cancelled by customer. Reason: "+reason;
            item.setRemark(newRemark);
            productOrderedRepository.save(item);
            //changing grand total of orders;
            Orders curr = ordersRepository.findById(orderId).get();
            List<ProductOrdered> allProds = productOrderedRepository.findAllByOrders(curr);
            int newtotal = 0;
            for(ProductOrdered i: allProds){
                if(!i.getStatus().equals("Cancelled")){
                    newtotal += (i.getQuantity()*i.getRate());
                }
            }
            curr.setSubTotal(newtotal);
            curr.setGrandTotal(curr.getGrandTotal()+(0.05*curr.getGrandTotal()));
            ordersRepository.save(curr);
            return "Success";
        }
        return "Failure";
    }
}
