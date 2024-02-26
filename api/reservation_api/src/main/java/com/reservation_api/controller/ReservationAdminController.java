package com.reservation_api.controller;

import com.reservation_api.model.Reservation;
import com.reservation_api.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class ReservationAdminController {
    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getReservations() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationRepository.findAllReservations());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationById(@PathVariable String id) {
        try {
            Integer intId = Integer.parseInt(id);
            Optional<Reservation> reservation = reservationRepository.findById(intId);

            if (reservation.isEmpty())
                return notExist("This reservation does not exist.");

            return ResponseEntity.status(HttpStatus.OK).body(reservation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/hotel/{hotelId}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationsByHotelId(@PathVariable String hotelId) {
        try {
            Integer intHotelId = Integer.parseInt(hotelId);
            List<Reservation> reservations = reservationRepository.findByHotelId(intHotelId);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/hotel/{hotelId}/{roomId}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationsByHotelId(@PathVariable String hotelId, @PathVariable String roomId) {
        try {
            Integer intHotelId = Integer.parseInt(hotelId);
            Integer intRoomId = Integer.parseInt(roomId);
            List<Reservation> reservations = reservationRepository.findByRoomHotelId(intHotelId, intRoomId);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> cancelReservationById(@PathVariable String id) {
        try {
            Integer intId = Integer.parseInt(id);
            Optional<Reservation> reservation = reservationRepository.findById(intId);
            if (reservation.isEmpty())
                return notExist("This Reservation does not exist.");
            reservation.ifPresent(reservation1 -> {
                reservation1.setCancel(true);
                reservationRepository.save(reservation1);
            });
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public ResponseEntity<?> notExist(String message) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Message", message);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }


}
