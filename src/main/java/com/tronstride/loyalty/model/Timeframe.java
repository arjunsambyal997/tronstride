package com.tronstride.loyalty.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "Timeframe")
@AllArgsConstructor
@NoArgsConstructor
@Data


public class Timeframe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_seq")//type
    private Integer id;

    @Column(name = "start_on")
    private LocalDateTime startOn;

    @Column(name = "expires_on")
    private LocalDateTime expiresOn;

    @Column(name = "ValidFor")
    private Boolean validFor;

    @Column(name = "Span")
    private Integer spanValue;

    @Column(name = "DateCreated", updatable = false)
    @CreatedDate
    private LocalDateTime dateCreated;

    @Column(name = "LastUpdated")
    @LastModifiedDate
    private LocalDateTime lastUpdated;
    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timeSpan", referencedColumnName = "Id")
    private TimeSpanEntity timeSpanEntity;

}
