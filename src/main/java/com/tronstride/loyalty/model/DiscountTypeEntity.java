package com.tronstride.loyalty.model;

import com.tronstride.loyalty.enums.DiscountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DiscountType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountTypeEntity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
}
