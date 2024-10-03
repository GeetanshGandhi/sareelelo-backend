package com.vastrika.backend.orders.repository;

import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findAllByCustomer(Customer customer);
}
