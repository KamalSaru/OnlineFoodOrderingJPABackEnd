package com.restaurent.onlinefoodorderingJPABackend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlaceFoodOrderRequest {

    private List<LineItemRequest> lineItems;
}
