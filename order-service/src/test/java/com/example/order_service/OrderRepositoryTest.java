package com.example.order_service;

import com.example.order_service.order.Order;
import com.example.order_service.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveAndFind() {
        Order order = new Order();
        order.setProductId(1L);
        order.setQuantity(2);
        order.setTotalPrice(20);
        order.setStatus("CREATED");

        Order savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder.getId());
        assertTrue(orderRepository.findById(savedOrder.getId()).isPresent());
    }
}
