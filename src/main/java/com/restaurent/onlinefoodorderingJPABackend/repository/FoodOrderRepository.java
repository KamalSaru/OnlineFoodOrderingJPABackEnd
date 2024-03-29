package com.restaurent.onlinefoodorderingJPABackend.repository;


import com.restaurent.onlinefoodorderingJPABackend.dto.FoodOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrderDTO, Long> {

    FoodOrderDTO findByOrderId(String orderId);

    List<FoodOrderDTO> findByOrderStatus(String orderStatus);
}
