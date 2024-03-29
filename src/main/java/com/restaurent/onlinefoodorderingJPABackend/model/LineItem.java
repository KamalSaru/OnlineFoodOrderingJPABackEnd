package com.restaurent.onlinefoodorderingJPABackend.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LineItem {

    private String itemId;
    private String itemTitle;
    private Float price;

    private String orderId;
}
