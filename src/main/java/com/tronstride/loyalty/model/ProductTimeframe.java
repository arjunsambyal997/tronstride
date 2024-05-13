package com.tronstride.loyalty.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "Timeframe")
@AllArgsConstructor
@NoArgsConstructor
@Data


public class ProductTimeframe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_on")
    private LocalDate startOn;

    @Column(name = "expires_on")
    private LocalDate expiresOn;

    @Column(name = "ValidFor")
    private Boolean validFor;

    @Column(name = "Span")
    private Integer spanValue;

    @Column(name = "DateCreated", updatable = false)
    @CreatedDate
    private LocalDate dateCreated;

    @Column(name = "LastUpdated")
    @LastModifiedDate
    private LocalDate lastUpdated;
    @Column(name="published_on")
    private LocalDate publishedOn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timeSpan", referencedColumnName = "Id")
    private TimeSpanEntity timeSpanEntity;

}
