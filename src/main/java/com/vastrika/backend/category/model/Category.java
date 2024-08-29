package com.vastrika.backend.category.model;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    private String categoryName;

    public Category(){}
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "{" +
                "\"categoryName\":\"" + categoryName + '\"' +
                '}';
    }
}
