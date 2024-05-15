package com.tronstride.loyalty.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class DiscountedCollections {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String collectionName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "collection_id")
    private List<DiscountedProducts> discountedProductsList = new ArrayList<>();
}
