package com.hotelerie_api.repository;

import com.hotelerie_api.model.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {

    @Query(value = "SELECT * FROM hotel WHERE nom = ?1", nativeQuery = true)
    Hotel findByNom(String nom);

    @Query(value = "SELECT * FROM hotel", nativeQuery = true)
    List<Hotel> findAllHotels();

    @Query(value = "SELECT * FROM hotel WHERE nombre_chambres >= ?1", nativeQuery = true)
    List<Hotel> findHotelsWithMinRooms(Integer minRooms);

    @Query(value = "SELECT * FROM hotel WHERE nombre_places_garage >= ?1", nativeQuery = true)
    List<Hotel> findHotelsWithMinGaragePlaces(Integer minGaragePlaces);

    @Query(value = "SELECT * FROM hotel WHERE nombre_lits_bebe >= ?1", nativeQuery = true)
    List<Hotel> findHotelsWithMinBabyBeds(Integer minBabyBeds);

    @Query(value = "SELECT * FROM hotel WHERE nom LIKE %?1%", nativeQuery = true)
    List<Hotel> findHotelsByNameContaining(String name);

    @Query(value = "SELECT * FROM hotel WHERE nombre_chambres >= ?1 AND nombre_places_garage >= ?2", nativeQuery = true)
    List<Hotel> findHotelsWithMinRoomsAndGaragePlaces(Integer minRooms, Integer minGaragePlaces);

    @Query(value = "SELECT * FROM hotel WHERE nombre_chambres >= ?1 AND nombre_lits_bebe >= ?2", nativeQuery = true)
    List<Hotel> findHotelsWithMinRoomsAndBabyBeds(Integer minRooms, Integer minBabyBeds);

    @Query(value = "SELECT * FROM hotel WHERE nombre_places_garage >= ?1 AND nombre_lits_bebe >= ?2", nativeQuery = true)
    List<Hotel> findHotelsWithMinGaragePlacesAndBabyBeds(Integer minGaragePlaces, Integer minBabyBeds);

    @Query(value = "SELECT * FROM hotel WHERE nombre_chambres >= ?1 AND nombre_places_garage >= ?2 AND nombre_lits_bebe >= ?3", nativeQuery = true)
    List<Hotel> findHotelsWithMinRoomsGaragePlacesAndBabyBeds(Integer minRooms, Integer minGaragePlaces, Integer minBabyBeds);
}
