package com.reservation_api.repository;

import com.reservation_api.model.PriceModificationDayOfWeek;
import com.reservation_api.model.PriceModificationNumberPeople;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PriceModificationNumberPeopleRepository extends CrudRepository<PriceModificationNumberPeople, Integer> {

    @Query(value = "SELECT * FROM price_modification_number_people WHERE number_people=?1", nativeQuery = true)
    PriceModificationNumberPeople getByNumberPeople(Integer number_people);

}
