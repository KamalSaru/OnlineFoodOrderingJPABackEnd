package com.restaurent.onlinefoodorderingJPABackend.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LineItemRequest {

    private String itemTitle;
    private Float price;
}
