package com.reservation_api.repository;

import com.reservation_api.enums.Days;
import com.reservation_api.model.PriceModificationDayOfWeek;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PriceModificationDayOfWeekRepository extends CrudRepository<PriceModificationDayOfWeek, Integer> {

    @Query(value = "SELECT * FROM price_modification_day_of_week WHERE day=?1", nativeQuery = true)
    PriceModificationDayOfWeek getByDayWeek(Integer day);

}
