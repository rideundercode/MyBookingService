package com.billing_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.billing_api.model.Billing;
import com.billing_api.repository.BillingRepository;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillingController {
    @Autowired
    private BillingRepository billingRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> cancelBillingById(@PathVariable String id, @RequestParam("userId") Integer userID) {
        try {
            Integer intId = Integer.parseInt(id);
            Billing billing = billingRepository.findByIdUserID(intId, userID);

            if (billing == null)
                return notExist("This Billing does not exist.");

            billing.setCancelled(true);
            billingRepository.save(billing);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(billing);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBillingByReservationId(@PathVariable String reservationId) {
        try {
            Integer intReservationId = Integer.parseInt(reservationId);
            Billing billing = billingRepository.findByReservationId(intReservationId);
            return ResponseEntity.status(HttpStatus.OK).body(billing);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBillingsByUserId(@PathVariable String userId) {
        try {
            Integer intUserId = Integer.parseInt(userId);
            List<Billing> billings = billingRepository.findByUserId(intUserId);
            return ResponseEntity.status(HttpStatus.OK).body(billings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/user/{userId}/{reservationId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBillingsByUserReservationId(@PathVariable String userId, @PathVariable String reservationId) {
        try {
            Integer intReservationId = Integer.parseInt(reservationId);
            Integer intUserId = Integer.parseInt(userId);
            List<Billing> billings = billingRepository.findByHotelUserId(intUserId, intReservationId);
            return ResponseEntity.status(HttpStatus.OK).body(billings);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/addBilling", method = RequestMethod.POST)
    public ResponseEntity<?> addBilling(@RequestParam("roomType") String roomType,
            @RequestParam("reservationId") Integer reservationId, @RequestParam("userId") Integer userId) {
        try {
            Billing billing = new Billing(reservationId, userId);
            billingRepository.save(billing);
            return ResponseEntity.status(HttpStatus.CREATED).body(billing);
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
