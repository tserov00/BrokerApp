package org.example.brokerapplication.controller;

import jakarta.validation.Valid;
import org.example.brokerapplication.dto.request.OrderDto;
import org.example.brokerapplication.dto.response.OrderResponseDto;
import org.example.brokerapplication.security.CustomerAccountDetails;
import org.example.brokerapplication.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/buy")
    public ResponseEntity<Void> createBuyOrder(@Valid @RequestBody OrderDto orderDto, @AuthenticationPrincipal CustomerAccountDetails customerAccountDetails) {
        orderService.createBuyOrder(orderDto, customerAccountDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sell")
    public ResponseEntity<Void> createSellOrder(@Valid @RequestBody OrderDto orderDto, @AuthenticationPrincipal CustomerAccountDetails customerAccountDetails) {
        orderService.createSellOrder(orderDto, customerAccountDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderResponseDto>> getHistory(@AuthenticationPrincipal CustomerAccountDetails customerAccountDetails) {
        List responseBody = orderService.getHistory(customerAccountDetails);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
