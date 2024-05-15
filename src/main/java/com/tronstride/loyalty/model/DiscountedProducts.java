package com.tronstride.loyalty.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DiscountedProducts")
public class DiscountedProducts {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_seq")//type
    private Integer Id;

    private String productName;

    private String price;
}
