package com.restaurent.onlinefoodorderingJPABackend.controller;

import com.restaurent.onlinefoodorderingJPABackend.model.FoodOrder;
import com.restaurent.onlinefoodorderingJPABackend.model.PlaceFoodOrderRequest;
import com.restaurent.onlinefoodorderingJPABackend.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @PostMapping(value = "/order/action/place")
    //@Postmapping---http://localhost:8080/order/action/place
    public ResponseEntity<?> placeOrder(@RequestBody PlaceFoodOrderRequest placeFoodOrderRequest) {
        FoodOrder foodOrder = foodOrderService.placeOrder(placeFoodOrderRequest);
        return ResponseEntity.accepted().body(foodOrder);
    }

    @GetMapping(value = "/order/action/get")
    public ResponseEntity<?> fetchOrderStatus(@RequestParam String orderId) {
        FoodOrder foodOrder = foodOrderService.fetchOrderStatus(orderId);
        return ResponseEntity.ok(foodOrder);
    }

    @GetMapping(value = "/order/action/list")
    public ResponseEntity<?> listFoodOrders(@RequestParam String orderStatus) {
        List<FoodOrder> foodOrderList = foodOrderService.listFoodOrders(orderStatus);
        return ResponseEntity.ok(foodOrderList);
    }
}
