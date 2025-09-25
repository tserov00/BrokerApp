package org.example.brokerapplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.example.brokerapplication.enumeration.OrderStatusEnum;

@Entity
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 18)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatusEnum status;

    public OrderStatus() {}

    public OrderStatus(Integer id, OrderStatusEnum status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
