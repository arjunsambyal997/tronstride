package com.tronstride.loyalty.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ProgramDetail")
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDetail {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_seq")//type
    private Integer Id;

    @Column(name = "ProgramName", nullable = false)
    private String programName;

    @Column(name = "WillJoinOnce", nullable = false)
    private Boolean willJoinOnce;

    @Column(name = "Category", nullable = false)
    private String category;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "CodeRedemptionLimit", nullable = false)
    private String codeRedemptionLimit;

    @Column(name = "CodeType", nullable = false)
    private String codeType;

    @Column(name = "CodeCount", nullable = false)
    private String codeCount;


    @Column(name = "CodeLength", nullable = false)
    private Integer codeLength;

    @Column(name = "Pattern", nullable = false)
    private String pattern;

    @Column(name = "Prefix", nullable = false)
    private String prefix;

    @Column(name = "Postfix", nullable = false)
    private String postfix;

    @Column(name = "AdditionalInformation", nullable = false)
    private String additionalInformation;

    @Column(name = "CustomCode", nullable = false)
    private String customCode;
    @Column(name = "DiscountAmount", nullable = false)
    private Long discountAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discountTypeId", referencedColumnName = "Id")
    private DiscountTypeEntity discountType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDiscountType", referencedColumnName = "Id")
    private OrderDiscountTypeEntity orderDiscountType;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productDiscountType", referencedColumnName = "Id")
    private ProductDiscountTypeEntity productDiscountType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Timeframe", referencedColumnName = "Id")
    private Timeframe productTimeframe;

    @Column(name = "DateCreated", updatable = false)
    @CreatedDate
    private String dateCreated;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id_discounted", referencedColumnName = "ID")
    private List<DiscountedProducts> discountedProductsList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "collection_id_discounted", referencedColumnName = "ID")
    private List<DiscountedCollections> discountedCollectionsList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "coupon_id", referencedColumnName = "ID")
    private List<Coupon> coupons = new ArrayList<>();
    @Column(name = "LastUpdated")
    @LastModifiedDate
    private String lastUpdated;

    private boolean expired;
}
