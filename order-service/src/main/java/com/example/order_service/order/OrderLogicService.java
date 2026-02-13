package com.example.order_service.order;

import com.example.order_service.product.ProductClient;
import com.example.order_service.product.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderLogicService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderLogicService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public ResponseEntity<Order> create(CreateOrderRequest orderRequest) {
        var productOpt = productClient.getProductById(orderRequest.getProductId());
        if (productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ProductDto productDto = productOpt.get();

        Order order = new Order();
        order.setProductId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());

        if (orderRequest.getQuantity() > productDto.getQuantity()) {
            order.setStatus("REJECTED_OUT_OF_STOCK");
            order.setTotalPrice(0);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(orderRepository.save(order));
        }

        order.setTotalPrice(productDto.getPrice() * orderRequest.getQuantity());
        order.setStatus("CREATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.save(order));
    }
}
