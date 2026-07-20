package com.example.orderservice.service;

import com.example.orderservice.client.UserClient;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderDetails;
import com.example.orderservice.model.UserDto;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    // OpenFeign is used by default; swap in UserWebClientService to try the WebClient path.
    private final UserClient userClient;
    private final OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public OrderDetails getOrderDetails(Long id) {
        Order order = getOrderById(id);
        UserDto user = userClient.getUserById(order.getUserId());
        return new OrderDetails(order, user);
    }

    public void deleteOrder(Long id) {
        orderRepository.delete(getOrderById(id));
    }
}
