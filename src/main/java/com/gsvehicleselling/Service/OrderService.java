package com.gsvehicleselling.Service;

import com.gsvehicleselling.Model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    List<Order> getByuserID(int userID);

    Order getOrder(int id);

    void saveOrder(Order order);

}
