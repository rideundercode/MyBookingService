package com.hotelerie_api.controller;

import com.hotelerie_api.model.Chambre;
import com.hotelerie_api.model.Hotel;
import com.hotelerie_api.repository.ChambreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chambre")
public class ChambreController {
    private final ChambreRepository chambreRepository;

    public ChambreController(ChambreRepository chambreRepository) {
        this.chambreRepository = chambreRepository;
    }

    @PostMapping
    public Chambre createChambre(@RequestBody Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @GetMapping("/hotel/{id}")
    public List<Chambre> getChambreByHotelId(@PathVariable("id") Integer hotelId) {
        return chambreRepository.findByHotelId(hotelId);
    }

    @RequestMapping(value = "/deleteChambre", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteChambre(@RequestParam(value="id") Integer id) {
        try {
            Optional<Chambre> chambre = chambreRepository.findById(id);
            if (chambre.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
            } else {
                chambre.ifPresent(chambre1 -> {
                    chambre1.setDeleted(true);
                    chambreRepository.save(chambre1);
                });
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping("/{id}")
    public Optional<Chambre> getById(@PathVariable("id") Integer id) {
        return chambreRepository.findById(id);
    }

}
