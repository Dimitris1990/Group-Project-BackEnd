package com.financialservices.repository;

import com.financialservices.models.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    public List<Orders> findByUseraccountId(Long id);

}
