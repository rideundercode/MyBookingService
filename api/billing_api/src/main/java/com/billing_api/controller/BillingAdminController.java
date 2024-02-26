package com.billing_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.billing_api.model.Billing;
import com.billing_api.repository.BillingRepository;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class BillingAdminController {
    @Autowired
    private BillingRepository billingRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getBillings() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(billingRepository.findAllBillings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBillingById(@PathVariable String id) {
        try {
            Integer intId = Integer.parseInt(id);
            Optional<Billing> billing = billingRepository.findById(intId);

            if (billing.isEmpty())
                return notExist("This billing does not exist.");

            return ResponseEntity.status(HttpStatus.OK).body(billing);

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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> cancelBillingById(@PathVariable String id) {
        try {
            Integer intId = Integer.parseInt(id);
            Optional<Billing> billing = billingRepository.findById(intId);
            if (billing.isEmpty())
                return notExist("This Billing does not exist.");
            billing.ifPresent(billing1 -> {
                billing1.setCancelled(true);
                billingRepository.save(billing1);
            });
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(billing);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/refund/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> refundBillingById(@PathVariable String id) {
        try {
            Integer intId = Integer.parseInt(id);
            Optional<Billing> billing = billingRepository.findById(intId);
            if (billing.isEmpty())
                return notExist("This Billing does not exist.");
            billing.ifPresent(billing1 -> {
                billing1.setRefunded(true);
                billingRepository.save(billing1);
            });
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(billing);

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
