package com.vastrika.backend.DeliveryEmployee.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vastrika.backend.DeliveryEmployee.model.DeliveryEmployee;

@Repository

public interface DeliveryEmployeeRepository extends JpaRepository < DeliveryEmployee,String>{
   //repo
}
