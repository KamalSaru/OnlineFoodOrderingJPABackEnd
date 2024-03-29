package com.restaurent.onlinefoodorderingJPABackend.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "restaurant_food_ordering", name = "orders")
@Setter
@Getter
public class FoodOrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String orderStatus;
}
