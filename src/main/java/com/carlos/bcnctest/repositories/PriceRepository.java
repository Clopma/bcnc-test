package com.carlos.bcnctest.repositories;


import com.carlos.bcnctest.entities.Brand;
import com.carlos.bcnctest.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Optional<Price> findTopByBrandEqualsAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdEqualsOrderByPriorityDesc(Brand brand, OffsetDateTime applicationDate, OffsetDateTime applicationDateCopy, Integer productId);

}