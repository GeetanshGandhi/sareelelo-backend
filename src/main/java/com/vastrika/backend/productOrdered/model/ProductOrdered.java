package com.vastrika.backend.productOrdered.model;

import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.product.model.Product;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@IdClass(ProductOrderedPK.class)
public class ProductOrdered {

    @Id
    @ManyToOne
    @JoinColumn(name = "orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "productId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    private int quantity;

    public ProductOrdered(){}

    public ProductOrdered(Product product, Orders orders, int quantity){
        this.product = product;
        this.orders = orders;
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
