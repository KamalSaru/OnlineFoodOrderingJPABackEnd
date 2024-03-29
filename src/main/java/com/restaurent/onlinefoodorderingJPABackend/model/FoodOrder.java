package com.restaurent.onlinefoodorderingJPABackend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FoodOrder {

    private String orderId;
    private String orderStatus;

    private List<LineItem> lineItems;
}

