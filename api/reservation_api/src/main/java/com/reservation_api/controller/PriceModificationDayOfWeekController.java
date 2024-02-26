package com.reservation_api.controller;

import com.reservation_api.enums.Days;
import com.reservation_api.model.PriceModificationDayOfWeek;
import com.reservation_api.repository.PriceModificationDayOfWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/priceModificationDayOfWeek")
public class PriceModificationDayOfWeekController {

    @Autowired
    private PriceModificationDayOfWeekRepository priceModificationDayOfWeekRepository;
    @RequestMapping(value = "/addPriceModification", method = RequestMethod.POST)
    public ResponseEntity<?> addPriceModification(@RequestParam("priceReduction") float priceReduction, @RequestParam(value="day", required=false) Days day) {
        try {
            PriceModificationDayOfWeek priceModificationDayOfWeek = new PriceModificationDayOfWeek(priceReduction, day);
            priceModificationDayOfWeekRepository.save(priceModificationDayOfWeek);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(priceModificationDayOfWeek);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }


    @RequestMapping(value = "/deletePriceModification", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePriceModification(@RequestParam(value="id") Integer id) {
        try {
            Optional<PriceModificationDayOfWeek> priceModification = priceModificationDayOfWeekRepository.findById(id);
            if (priceModification.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
            } else {
                priceModificationDayOfWeekRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/getPriceModifications", method = RequestMethod.GET)
    public ResponseEntity<?> getPriceModifications() {
        try {
            Iterable<PriceModificationDayOfWeek> priceModifications = priceModificationDayOfWeekRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(priceModifications);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/getPriceModification/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPriceModificationById(@PathVariable String id) {
        try {
            Integer intId = Integer.parseInt(id);
            Optional<PriceModificationDayOfWeek> priceModification = priceModificationDayOfWeekRepository.findById(intId);
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
