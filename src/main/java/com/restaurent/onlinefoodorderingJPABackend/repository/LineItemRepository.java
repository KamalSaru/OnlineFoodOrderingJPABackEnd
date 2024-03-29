package com.restaurent.onlinefoodorderingJPABackend.repository;


import com.restaurent.onlinefoodorderingJPABackend.dto.LineItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemRepository extends JpaRepository<LineItemDTO, Long> {

    List<LineItemDTO> findByOrderId(String orderId);
}
