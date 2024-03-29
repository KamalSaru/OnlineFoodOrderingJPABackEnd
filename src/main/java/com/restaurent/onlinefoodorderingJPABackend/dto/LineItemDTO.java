package com.restaurent.onlinefoodorderingJPABackend.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(schema = "restaurant_food_ordering", name = "food_details")
@Setter
@Getter
public class LineItemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemId;
    private String itemTitle;
    private Float price;

    private String orderId;
}
