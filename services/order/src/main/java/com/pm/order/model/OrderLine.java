package com.pm.order.model;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderLine {

    @Id
    @GeneratedValue()
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private  Integer productId;
    private double quantity;
}
