package com.carlos.bcnctest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "PRICES")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer priceId;

    @Column
    Integer productId;

    @ManyToOne
    Brand brand;

    @Column
    OffsetDateTime startDate;

    @Column
    OffsetDateTime endDate;

    @Column
    Integer priceList;

    @Column
    Integer priority;

    @Column
    BigDecimal price;

    @Column
    String curr;

}
