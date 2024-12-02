package com.example.ProjectBE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjectBE.entities.Order;
import com.example.ProjectBE.entities.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findTopByOrderByIdOrderDesc();

    Order findTopByUserOrderByIdOrderDesc(User user);

    List<Order> findOrderByUser_IdUser(int idUser);

}
