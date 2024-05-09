package com.tronstride.loyalty.model;

import com.tronstride.loyalty.enums.OrderDiscountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderDiscountType")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDiscountTypeEntity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private OrderDiscountType orderDiscountType;
}
