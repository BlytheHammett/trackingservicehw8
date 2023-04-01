package com.example.trackingservice.repository;

import com.example.trackingservice.model.Item;
import com.example.trackingservice.model.ItemDetails;
import com.example.trackingservice.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    List<Order> orders = new ArrayList<>();

    public List<Order> findAll() {
        return orders;
    }

    public int create(Order order) {
        int id = orders.size() + 1;
        order.setOrderId(id);
        orders.add(order);
        return id;
    }

    private Order getById(int id) {
        Order order = orders.stream().filter(x -> x.getOrderId() == id).findAny().orElse(null);
        return order;
    }

    public Item getOrderStatus(int orderId, int itemId) {
        Order order = getById(orderId);
        Item item = new Item();
        boolean foundItem = false;
        if (order != null) {
            for (Item tempItem : order.getItems()) {
                if (tempItem.getItemId() == itemId) {
                    item = tempItem;
                    foundItem = true;
                }
            }

        } else {
            throw new IllegalStateException("order with this id was not found.");
        }

        if (foundItem) {
            return item;
        } else {
            throw new IllegalStateException("item with this id was not found.");
        }
    }

    public void update(ItemDetails itemDetails, int id) {
        Order order = getById(id);
        for (Item tempItem : order.getItems()) {
            if (tempItem.getItemId() == itemDetails.getItemId()) {
                System.out.println("setting shipping status");
                tempItem.setShippingStatus(itemDetails.getStatus());
            }
        }
    }

}
