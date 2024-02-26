package com.reservation_api.controller;

import com.reservation_api.enums.Days;
import com.reservation_api.model.*;
import com.reservation_api.repository.PriceModificationDayOfWeekRepository;
import com.reservation_api.repository.PriceModificationNumberPeopleRepository;
import com.reservation_api.repository.ReservationRepository;
import com.reservation_api.repository.ServiceRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PriceModificationDayOfWeekRepository priceModificationDayOfWeekRepository;

    @Autowired
    private PriceModificationNumberPeopleRepository priceModificationNumberPeopleRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getReservation() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationRepository.findAllValidReservations());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/user/{userId}/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationByIdByUserId(@PathVariable String userId, @PathVariable String id) {
        try {
            Integer intUserId = Integer.parseInt(userId);
            Integer intId = Integer.parseInt(id);
            Reservation reservation = reservationRepository.findByIdUserID(intId, intUserId);
            return ResponseEntity.status(HttpStatus.OK).body(reservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> cancelReservationById(@PathVariable String id, @RequestParam("userId") Integer userID) {
        try {
            Integer intId = Integer.parseInt(id);
            Reservation reservation = reservationRepository.findByIdUserID(intId, userID);

            if (reservation == null)
                return notExist("This Reservation does not exist.");

            reservation.setCancel(true);
            reservationRepository.save(reservation);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/hotel/{hotelId}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationsByHotelId(@PathVariable String hotelId) {
        try {
            Integer intHotelId = Integer.parseInt(hotelId);
            List<Reservation> reservations = reservationRepository.findValidByHotelId(intHotelId);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationsByUserId(@PathVariable String userId) {
        try {
            Integer intUserId = Integer.parseInt(userId);
            List<Reservation> reservations = reservationRepository.findByUserId(intUserId);
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
            List<Reservation> reservations = reservationRepository.findValidByRoomHotelId(intHotelId, intRoomId);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/user/{userId}/{hotelId}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationsByUserHotelId(@PathVariable String userId, @PathVariable String hotelId) {
        try {
            Integer intHotelId = Integer.parseInt(hotelId);
            Integer intUserId= Integer.parseInt(userId);
            List<Reservation> reservations = reservationRepository.findByHotelUserId(intUserId, intHotelId);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/addReservation", method = RequestMethod.POST)
    public ResponseEntity<?> addReservation(@RequestParam("roomId") Integer roomId, @RequestParam("hotelId") Integer hotelId,@RequestParam("userId") Integer userId,
                                        @RequestParam("numberPeople") Integer numberPeople,
                                        @RequestParam("reservationStart") LocalDateTime reservationStart,
                                        @RequestParam("reservationEnd") LocalDateTime reservationEnd,
                                            @RequestParam("services") String services) {
        try {
            List<Reservation> conflictsReservation = reservationRepository.verifAlreadyReserved(hotelId, roomId, reservationStart, reservationEnd);

            if (conflictsReservation.isEmpty()) {

                String reduction = getReduction(numberPeople, services, reservationStart, reservationEnd);
                Reservation reservation = new Reservation(hotelId, roomId, numberPeople, userId, reservationStart, reservationEnd, reduction);
                reservationRepository.save(reservation);
                return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
            } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("Message", "Your reservation is in conflict with another one");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
                                                                                    

    public String getReduction(int numberPeople, String services, LocalDateTime reservationStart, LocalDateTime reservationEnd) {
        float global = 1;
        ArrayList<Float> day_reduction = new ArrayList<>();
        PriceModificationNumberPeople peopleReduction = priceModificationNumberPeopleRepository.getByNumberPeople(numberPeople);
        if (peopleReduction != null) {
            global = peopleReduction.getPriceReduction();
        }

        String[] servicesArray = services.split(",");

        Service[] service = new Service[servicesArray.length];

        for (int i = 0; i < servicesArray.length; i++) {
            try {
                Optional<Service> optionnalService = serviceRepository.findById(Integer.valueOf(servicesArray[i]));
                int finalI = i;
                optionnalService.ifPresent(reservation1 -> {
                    service[finalI] = reservation1;
                });
            } catch (NumberFormatException nfe) {
                System.out.println(nfe);
            };
        }
        for (LocalDateTime date = reservationStart; date.isBefore(reservationEnd); date = date.plusDays(1))
        {
            int intday = Days.valueOf(String.valueOf(date.getDayOfWeek())).ordinal();
            PriceModificationDayOfWeek dayReduction = priceModificationDayOfWeekRepository.getByDayWeek(intday);
            if (dayReduction != null) {
                day_reduction.add(dayReduction.getPriceReduction());
            }
        }
        Reduction reduction = new Reduction(service, global, day_reduction);
        return reduction.toString();
    }


    public ResponseEntity<?> notExist(String message) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Message", message);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }


}
