package com.vastrika.backend.product.model;

import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.category.model.Category;
import com.vastrika.backend.city.model.City;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;
    private String description;
    private double price;
    private double discount;
    private int quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "ownerEmail")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Business business;

    @ManyToOne
    @JoinColumn(name="categoryName")
    @OnDelete(action = OnDeleteAction.SET_DEFAULT)
    private Category category;

    @ManyToOne
    @JoinColumn(name="pinCode")
    @OnDelete(action = OnDeleteAction.SET_DEFAULT)
    private City city;

//    @OneToMany(fetch=FetchType.LAZY, mappedBy = "product")
//    private Set<CartItem> cartItems;

    public Product(){}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
