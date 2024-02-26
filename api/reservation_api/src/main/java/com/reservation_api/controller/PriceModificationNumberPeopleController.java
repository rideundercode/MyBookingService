package com.reservation_api.controller;

import com.reservation_api.enums.Days;
import com.reservation_api.model.PriceModificationDayOfWeek;
import com.reservation_api.model.PriceModificationNumberPeople;
import com.reservation_api.repository.PriceModificationDayOfWeekRepository;
import com.reservation_api.repository.PriceModificationNumberPeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/priceModificationNumberPeople")
public class PriceModificationNumberPeopleController {

    @Autowired
    private PriceModificationNumberPeopleRepository priceModificationNumberPeopleRepository;
    @RequestMapping(value = "/addPriceModification", method = RequestMethod.POST)
    public ResponseEntity<?> addPriceModification(@RequestParam(value="numberPeople") Integer numberPeople, @RequestParam("priceReduction") float priceReduction) {
        try {
            PriceModificationNumberPeople priceModificationNumberPeople = new PriceModificationNumberPeople(numberPeople, priceReduction);
            priceModificationNumberPeopleRepository.save(priceModificationNumberPeople);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(priceModificationNumberPeople);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/deletePriceModification", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePriceModification(@RequestParam(value="id") Integer id) {
        try {
            Optional<PriceModificationNumberPeople> priceModification = priceModificationNumberPeopleRepository.findById(id);
            if (priceModification.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
            } else {
                priceModificationNumberPeopleRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/getPriceModifications", method = RequestMethod.GET)
    public ResponseEntity<?> getPriceModifications() {
        try {
            Iterable<PriceModificationNumberPeople> priceModifications = priceModificationNumberPeopleRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(priceModifications);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/getPriceModification/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPriceModificationById(@PathVariable String id) {
        try {
            Integer intId = Integer.parseInt(id);
            Optional<PriceModificationNumberPeople> priceModification = priceModificationNumberPeopleRepository.findById(intId);
            if (priceModification.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(priceModification);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
