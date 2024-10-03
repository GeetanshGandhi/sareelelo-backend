package com.vastrika.backend.productOrdered.repository;

import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.productOrdered.model.ProductOrdered;
import com.vastrika.backend.productOrdered.model.ProductOrderedPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderedRepository extends JpaRepository<ProductOrdered, ProductOrderedPK> {

    List<ProductOrdered> findAllByOrders(Orders orders);
}
