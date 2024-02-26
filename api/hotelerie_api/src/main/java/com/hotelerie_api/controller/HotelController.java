package com.hotelerie_api.controller;

import com.hotelerie_api.model.Hotel;
import com.hotelerie_api.repository.HotelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }



    @GetMapping
    public List<Hotel> findAllHotels() {
        Iterable<Hotel> iterable = hotelRepository.findAll();
        List<Hotel> hotels = new ArrayList<>();
        iterable.forEach(hotels::add);
        return hotels;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") Integer id, @RequestBody Hotel hotelDetails) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setNom(hotelDetails.getNom());
            hotel.setNombrePlacesGarage(hotelDetails.getNombrePlacesGarage());
            hotel.setTelephone(hotelDetails.getTelephone());
            hotel.setNombreLitsBebe(hotelDetails.getNombreLitsBebe());
            // Mettre à jour d'autres attributs de l'hôtel si nécessaire
            return ResponseEntity.ok(hotelRepository.save(hotel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable("id") Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            hotelRepository.delete(optionalHotel.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            return ResponseEntity.ok(optionalHotel.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-nom/{nom}")
    public Hotel getHotelByNom(@PathVariable("nom") String nom) {
        return hotelRepository.findByNom(nom);
    }


    @GetMapping("/by-min-rooms/{minRooms}")
    public List<Hotel> getHotelsWithMinRooms(@PathVariable("minRooms") Integer minRooms) {
        return hotelRepository.findHotelsWithMinRooms(minRooms);
    }

    @GetMapping("/by-min-garage-places/{minGaragePlaces}")
    public List<Hotel> getHotelsWithMinGaragePlaces(@PathVariable("minGaragePlaces") Integer minGaragePlaces) {
        return hotelRepository.findHotelsWithMinGaragePlaces(minGaragePlaces);
    }

    @GetMapping("/by-min-baby-beds/{minBabyBeds}")
    public List<Hotel> getHotelsWithMinBabyBeds(@PathVariable("minBabyBeds") Integer minBabyBeds) {
        return hotelRepository.findHotelsWithMinBabyBeds(minBabyBeds);
    }

    @GetMapping("/by-name-containing/{name}")
    public List<Hotel> getHotelsByNameContaining(@PathVariable("name") String name) {
        return hotelRepository.findHotelsByNameContaining(name);
    }

    @GetMapping("/by-min-rooms-and-garage-places/{minRooms}/{minGaragePlaces}")
    public List<Hotel> getHotelsWithMinRoomsAndGaragePlaces(
            @PathVariable("minRooms") Integer minRooms,
            @PathVariable("minGaragePlaces") Integer minGaragePlaces) {
        return hotelRepository.findHotelsWithMinRoomsAndGaragePlaces(minRooms, minGaragePlaces);
    }

    @GetMapping("/by-min-rooms-and-baby-beds/{minRooms}/{minBabyBeds}")
    public List<Hotel> getHotelsWithMinRoomsAndBabyBeds(
            @PathVariable("minRooms") Integer minRooms,
            @PathVariable("minBabyBeds") Integer minBabyBeds) {
        return hotelRepository.findHotelsWithMinRoomsAndBabyBeds(minRooms, minBabyBeds);
    }

    @GetMapping("/by-min-garage-places-and-baby-beds/{minGaragePlaces}/{minBabyBeds}")
    public List<Hotel> getHotelsWithMinGaragePlacesAndBabyBeds(
            @PathVariable("minGaragePlaces") Integer minGaragePlaces,
            @PathVariable("minBabyBeds") Integer minBabyBeds) {
        return hotelRepository.findHotelsWithMinGaragePlacesAndBabyBeds(minGaragePlaces, minBabyBeds);
    }

    @GetMapping("/by-min-rooms-garage-places-and-baby-beds/{minRooms}/{minGaragePlaces}/{minBabyBeds}")
    public List<Hotel> getHotelsWithMinRoomsGaragePlacesAndBabyBeds(
            @PathVariable("minRooms") Integer minRooms,
            @PathVariable("minGaragePlaces") Integer minGaragePlaces,
            @PathVariable("minBabyBeds") Integer minBabyBeds) {
        return hotelRepository.findHotelsWithMinRoomsGaragePlacesAndBabyBeds(minRooms, minGaragePlaces, minBabyBeds);
    }

}
