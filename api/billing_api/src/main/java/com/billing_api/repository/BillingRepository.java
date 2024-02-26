package com.billing_api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.billing_api.model.Billing;

import java.util.List;

public interface BillingRepository extends CrudRepository<Billing, Integer> {

    @Query(value = "SELECT * FROM billing WHERE reservation_id=?1", nativeQuery = true)
    Billing findByReservationId(Integer reservationId);

    @Query(value = "SELECT * FROM billing", nativeQuery = true)
    List<Billing> findAllBillings();

    @Query(value = "SELECT * FROM billing WHERE user_id = ?1", nativeQuery = true)
    List<Billing> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM billing WHERE id=?1 AND user_id = ?2", nativeQuery = true)
    Billing findByIdUserID(Integer id, Integer userId);

    @Query(value = "SELECT * FROM billing WHERE reservation_id = ?1 AND user_id = ?2", nativeQuery = true)
    List<Billing> findByHotelUserId(Integer reservationId, Integer userId);
}
