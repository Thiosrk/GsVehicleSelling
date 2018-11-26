package com.gsvehicleselling.Service.Impl;

import com.gsvehicleselling.Model.Order;
import com.gsvehicleselling.Repository.OrderRepository;
import com.gsvehicleselling.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getByuserID(int userID) {
        return orderRepository.findAllByUserid(userID);
    }

    @Override
    public Order getOrder(int id) {
        return orderRepository.getOne(id);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
