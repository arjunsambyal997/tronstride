package com.tronstride.loyalty.model;

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

    private String discountType;
}
