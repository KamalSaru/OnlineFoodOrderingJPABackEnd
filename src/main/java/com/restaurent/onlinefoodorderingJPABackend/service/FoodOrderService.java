package com.restaurent.onlinefoodorderingJPABackend.service;


import com.restaurent.onlinefoodorderingJPABackend.dto.FoodOrderDTO;
import com.restaurent.onlinefoodorderingJPABackend.dto.LineItemDTO;
import com.restaurent.onlinefoodorderingJPABackend.model.FoodOrder;
import com.restaurent.onlinefoodorderingJPABackend.model.LineItem;
import com.restaurent.onlinefoodorderingJPABackend.model.LineItemRequest;
import com.restaurent.onlinefoodorderingJPABackend.model.PlaceFoodOrderRequest;
import com.restaurent.onlinefoodorderingJPABackend.repository.FoodOrderRepository;
import com.restaurent.onlinefoodorderingJPABackend.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FoodOrderService {

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private LineItemRepository lineItemRepository;

    public FoodOrder placeOrder(PlaceFoodOrderRequest placeFoodOrderRequest) {
        /*
        Step 1: Create and save food order DTO.
        Step 2: Create and save line items DTO.
        Step 3: Create an object of FoodOrder and fill this with needed info.
         */

        //Step 1 - START
        FoodOrderDTO foodOrderDTO = createAndSaveFoodOrderDTO();
        //Step 1 - END

        //Step 2 - START
        List<LineItem> lineItems = createAndSaveLineItemDTOs(placeFoodOrderRequest.getLineItems(), foodOrderDTO.getOrderId());
        //Step 2 - END

        //Step 3 - START
        FoodOrder foodOrder = createFoodOrder(foodOrderDTO, lineItems);
        //Step 3 - END

        return foodOrder;
    }


    public FoodOrder fetchOrderStatus(String orderId) {
        FoodOrderDTO foodOrderDTO = foodOrderRepository.findByOrderId(orderId);
        List<LineItemDTO> lineItemDTOs = lineItemRepository.findByOrderId(orderId);

        List<LineItem> lineItems = new ArrayList<>();
        for (LineItemDTO lineItemDTO : lineItemDTOs) {
            LineItem lineItem = new LineItem();
            lineItem.setItemId(lineItemDTO.getItemId());
            lineItem.setItemTitle(lineItemDTO.getItemTitle());
            lineItem.setPrice(lineItemDTO.getPrice());
            lineItem.setOrderId(lineItemDTO.getOrderId());
            lineItems.add(lineItem);
        }

        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setOrderId(foodOrderDTO.getOrderId());
        foodOrder.setOrderStatus(foodOrderDTO.getOrderStatus());
        foodOrder.setLineItems(lineItems);
        return foodOrder;
    }

    public List<FoodOrder> listFoodOrders(String orderStatus) {
        List<FoodOrderDTO> foodOrderDTOS = foodOrderRepository.findByOrderStatus(orderStatus);

        List<FoodOrder> foodOrders = new ArrayList<>();
        for (FoodOrderDTO foodOrderDTO : foodOrderDTOS) {
            FoodOrder foodOrder = fetchOrderStatus(foodOrderDTO.getOrderId());
            foodOrders.add(foodOrder);
        }
        return foodOrders;
    }


    //Step 1
    private FoodOrderDTO createAndSaveFoodOrderDTO() {
        FoodOrderDTO foodOrderDTO = new FoodOrderDTO();
        //Create the random Universal unique identifier(UUID)
        foodOrderDTO.setOrderId(UUID.randomUUID().toString());
        foodOrderDTO.setOrderStatus("ACCEPTED");
        foodOrderRepository.save(foodOrderDTO);
        return foodOrderDTO;
    }

    //Step 2
    private List<LineItem> createAndSaveLineItemDTOs(List<LineItemRequest> lineItemRequests, String orderId) {
        List<LineItem> lineItems = new ArrayList<>();
        for (LineItemRequest lineItemRequest : lineItemRequests) {
            LineItemDTO lineItemDTO = new LineItemDTO();
            //Create the random Universal unique identifier(UUID)
            lineItemDTO.setItemId(UUID.randomUUID().toString());
            lineItemDTO.setItemTitle(lineItemRequest.getItemTitle());
            lineItemDTO.setPrice(lineItemRequest.getPrice());
            lineItemDTO.setOrderId(orderId);

            LineItem lineItem = new LineItem();
            lineItem.setItemId(lineItemDTO.getItemId());
            lineItem.setItemTitle(lineItemDTO.getItemTitle());
            lineItem.setPrice(lineItemDTO.getPrice());
            lineItem.setOrderId(lineItemDTO.getOrderId());
            lineItems.add(lineItem);

            lineItemRepository.save(lineItemDTO);
        }
        return lineItems;
    }

    //Step 3
    private FoodOrder createFoodOrder(FoodOrderDTO foodOrderDTO, List<LineItem> lineItems) {
        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setOrderId(foodOrderDTO.getOrderId());
        foodOrder.setOrderStatus(foodOrderDTO.getOrderStatus());
        foodOrder.setLineItems(lineItems);
        return foodOrder;
    }
}
