package com.hotelerie_api.repository;

import com.hotelerie_api.model.Chambre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends CrudRepository<Chambre, Integer> {

    @Query(value = "SELECT * FROM chambre WHERE hotel_id=?1", nativeQuery = true)
    List<Chambre> findByHotelId(Integer hotelId);
}