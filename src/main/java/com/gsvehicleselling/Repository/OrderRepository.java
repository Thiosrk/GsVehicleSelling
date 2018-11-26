package com.gsvehicleselling.Repository;

import com.gsvehicleselling.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findAllByUserid(int userID);
}
