package com.reservation_api.repository;

import com.reservation_api.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM reservation WHERE hotel_id=?1", nativeQuery = true)
    List<Reservation> findByHotelId(Integer hotelId);

    @Query(value = "SELECT * FROM reservation WHERE hotel_id=?1 and cancel=false", nativeQuery = true)
    List<Reservation> findValidByHotelId(Integer hotelId);
    @Query(value = "SELECT * FROM reservation", nativeQuery = true)
    List<Reservation> findAllReservations();

    @Query(value = "SELECT * FROM reservation and cancel=false", nativeQuery = true)
    List<Reservation> findAllValidReservations();
    @Query(value = "SELECT * FROM reservation WHERE user_id = ?1", nativeQuery = true)
    List<Reservation> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM reservation WHERE id=?1 AND user_id = ?2", nativeQuery = true)
    Reservation findByIdUserID(Integer id, Integer userId);

    @Query(value = "SELECT * FROM reservation WHERE hotel_id = ?1 AND room_id = ?2", nativeQuery = true)
    List<Reservation> findByRoomHotelId(Integer hotelId, Integer roomId);

    @Query(value = "SELECT * FROM reservation WHERE hotel_id = ?1 AND room_id = ?2 and cancel=false", nativeQuery = true)
    List<Reservation> findValidByRoomHotelId(Integer hotelId, Integer roomId);

    @Query(value = "SELECT * FROM reservation WHERE hotel_id = ?1 AND user_id = ?2", nativeQuery = true)
    List<Reservation> findByHotelUserId(Integer hotelId, Integer userId);

    @Query(value = "SELECT * FROM reservation WHERE hotel_id = ?1 AND room_id = ?2 AND reservation_start >= ?3 AND reservation_start <= ?4 AND reservation_end >= ?3 AND reservation_end <= ?4 AND cancel=false", nativeQuery = true)
    List<Reservation> verifAlreadyReserved(Integer hotelId, Integer roomId, LocalDateTime reservationStart, LocalDateTime reservationEnd);
}

