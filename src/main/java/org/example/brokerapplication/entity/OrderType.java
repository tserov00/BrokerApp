package org.example.brokerapplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.example.brokerapplication.enumeration.OrderTypeEnum;

@Entity
@Table(name = "order_type")
public class OrderType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 4)
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OrderTypeEnum type;

    public OrderType() {}

    public OrderType(Integer id, OrderTypeEnum type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderTypeEnum getType() {
        return type;
    }

    public void setType(OrderTypeEnum type) {
        this.type = type;
    }
}
