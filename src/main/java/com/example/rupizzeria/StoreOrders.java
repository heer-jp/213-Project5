package com.example.rupizzeria;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Keeps list of orders placed by the user.
 * Allows user to export list into a local file. 
 * @author Sai Maduri, Heer Patel
 */
public class StoreOrders {

    private ArrayList<Order> orders;
    
    /**
     * Creates a new array list to hold pizza orders.
     */
    public StoreOrders() {
        orders = new ArrayList<>();
    }

    /**
     * Gets pizza orders of user.
     * @return orders created by user.
     */
    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    /**
     * Add pizza order to array list.
     * @param order of pizza made by user.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Removes pizza order from array list.
     * @param order of pizza chosen by user.
     */
    public void removeOrder(Order order) {
        orders.remove(order);
    }

    /**
     * Return pizza order at specified index in the orders array list.
     * @param index of pizza order in array list.
     * @return index of order.
     */
    public Order getOrder(int index) {
        return orders.get(index);
    }

}
