package com.hotelerie_api.controller;

import com.hotelerie_api.model.Hotel;
import com.hotelerie_api.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HotelControllerTest {

    @Mock
    private HotelRepository hotelRepository;

    private HotelController hotelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        hotelController = new HotelController(hotelRepository);
    }

    @Test
    void testFindAllHotels() {
        // Mock the hotel repository's response
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findAll()).thenReturn(expectedHotels);

        // Call the controller method
        List<Hotel> actualHotels = hotelController.findAllHotels();

        // Verify the response
        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository).findAll();
    }

    @Test
    void testUpdateHotel() {
        // Mock the hotel repository's response
        Integer hotelId = 1;
        Hotel existingHotel = new Hotel();
        existingHotel.setId(hotelId);
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(existingHotel));
        when(hotelRepository.save(existingHotel)).thenReturn(existingHotel);

        // Create a hotel with updated details
        Hotel updatedHotel = new Hotel();
        updatedHotel.setNom("Updated Hotel");
        updatedHotel.setNombrePlacesGarage(10);
        updatedHotel.setTelephone("1234567890");
        updatedHotel.setNombreLitsBebe(5);

        // Call the controller method
        ResponseEntity<Hotel> response = hotelController.updateHotel(hotelId, updatedHotel);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingHotel, response.getBody());
        assertEquals(updatedHotel.getNom(), existingHotel.getNom());
        assertEquals(updatedHotel.getNombrePlacesGarage(), existingHotel.getNombrePlacesGarage());
        assertEquals(updatedHotel.getTelephone(), existingHotel.getTelephone());
        assertEquals(updatedHotel.getNombreLitsBebe(), existingHotel.getNombreLitsBebe());
        verify(hotelRepository).findById(hotelId);
        verify(hotelRepository).save(existingHotel);
    }

    @Test
    void testDeleteHotel() {
        // Mock the hotel repository's response
        Integer hotelId = 1;
        Hotel existingHotel = new Hotel();
        existingHotel.setId(hotelId);
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(existingHotel));

        // Call the controller method
        ResponseEntity<Void> response = hotelController.deleteHotel(hotelId);

        // Verify the response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(hotelRepository).findById(hotelId);
        verify(hotelRepository).delete(existingHotel);
    }

    @Test
    void testCreateHotel() {
        // Mock the hotel repository's response
        Hotel newHotel = new Hotel();
        when(hotelRepository.save(newHotel)).thenReturn(newHotel);

        // Call the controller method
        Hotel createdHotel = hotelController.createHotel(newHotel);

        // Verify the response
        assertEquals(newHotel, createdHotel);
        verify(hotelRepository).save(newHotel);
    }

    @Test
    void testGetHotelById() {
        // Mock the hotel repository's response
        Integer hotelId = 1;
        Hotel expectedHotel = new Hotel();
        expectedHotel.setId(hotelId);
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(expectedHotel));

        // Call the controller method
        ResponseEntity<Hotel> response = hotelController.getHotelById(hotelId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedHotel, response.getBody());
        verify(hotelRepository).findById(hotelId);
    }

    @Test
    void testGetHotelByNom() {
        // Mock the hotel repository's response
        String hotelNom = "Hotel ABC";
        Hotel expectedHotel = new Hotel();
        expectedHotel.setId(1);
        expectedHotel.setNom(hotelNom);
        when(hotelRepository.findByNom(hotelNom)).thenReturn(expectedHotel);

        // Call the controller method
        Hotel actualHotel = hotelController.getHotelByNom(hotelNom);

        // Verify the response
        assertEquals(expectedHotel, actualHotel);
        verify(hotelRepository).findByNom(hotelNom);
    }

    @Test
    void testGetHotelsWithMinRooms() {
        // Mock the hotel repository's response
        Integer minRooms = 10;
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findHotelsWithMinRooms(minRooms)).thenReturn(expectedHotels);

        // Call the controller method
        List<Hotel> actualHotels = hotelController.getHotelsWithMinRooms(minRooms);

        // Verify the response
        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository).findHotelsWithMinRooms(minRooms);
    }

    @Test
    void testGetHotelsWithMinGaragePlaces() {
        // Mock the hotel repository's response
        Integer minGaragePlaces = 5;
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findHotelsWithMinGaragePlaces(minGaragePlaces)).thenReturn(expectedHotels);

        // Call the controller method
        List<Hotel> actualHotels = hotelController.getHotelsWithMinGaragePlaces(minGaragePlaces);

        // Verify the response
        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository).findHotelsWithMinGaragePlaces(minGaragePlaces);
    }

    @Test
    void testGetHotelsWithMinBabyBeds() {
        // Mock the hotel repository's response
        Integer minBabyBeds = 2;
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findHotelsWithMinBabyBeds(minBabyBeds)).thenReturn(expectedHotels);

        // Call the controller method
        List<Hotel> actualHotels = hotelController.getHotelsWithMinBabyBeds(minBabyBeds);

        // Verify the response
        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository).findHotelsWithMinBabyBeds(minBabyBeds);
    }

    @Test
    void testGetHotelsByNameContaining() {
        // Mock the hotel repository's response
        String name = "ABC";
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findHotelsByNameContaining(name)).thenReturn(expectedHotels);

        // Call the controller method
        List<Hotel> actualHotels = hotelController.getHotelsByNameContaining(name);

        // Verify the response
        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository).findHotelsByNameContaining(name);
    }

    @Test
    void testGetHotelsWithMinRoomsAndGaragePlaces() {
        // Mock the hotel repository's response
        Integer minRooms = 10;
        Integer minGaragePlaces = 5;
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findHotelsWithMinRoomsAndGaragePlaces(minRooms, minGaragePlaces)).thenReturn(expectedHotels);

        // Call the controller method
        List<Hotel> actualHotels = hotelController.getHotelsWithMinRoomsAndGaragePlaces(minRooms, minGaragePlaces);

        // Verify the response
        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository).findHotelsWithMinRoomsAndGaragePlaces(minRooms, minGaragePlaces);
    }

    @Test
    void testGetHotelsWithMinRoomsAndBabyBeds() {
        // Mock the hotel repository's response
        Integer minRooms = 10;
        Integer minBabyBeds = 2;
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findHotelsWithMinRoomsAndBabyBeds(minRooms, minBabyBeds)).thenReturn(expectedHotels);

        // Call the controller method
        List<Hotel> actualHotels = hotelController.getHotelsWithMinRoomsAndBabyBeds(minRooms, minBabyBeds);

        // Verify the response
        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository).findHotelsWithMinRoomsAndBabyBeds(minRooms, minBabyBeds);
    }

    @Test
    void testGetHotelsWithMinGaragePlacesAndBabyBeds() {
        // Mock the hotel repository's response
        Integer minGaragePlaces = 5;
        Integer minBabyBeds = 2;
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findHotelsWithMinGaragePlacesAndBabyBeds(minGaragePlaces, minBabyBeds)).thenReturn(expectedHotels);

        // Call the controller method
        List<Hotel> actualHotels = hotelController.getHotelsWithMinGaragePlacesAndBabyBeds(minGaragePlaces, minBabyBeds);

        // Verify the response
        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository).findHotelsWithMinGaragePlacesAndBabyBeds(minGaragePlaces, minBabyBeds);
    }

    @Test
    void testGetHotelsWithMinRoomsGaragePlacesAndBabyBeds() {
        // Mock  la réponse du référentiel d'hôtels
        Integer minRooms = 10;
        Integer minGaragePlaces = 5;
        Integer minBabyBeds = 2;
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findHotelsWithMinRoomsGaragePlacesAndBabyBeds(minRooms, minGaragePlaces, minBabyBeds)).thenReturn(expectedHotels);

        // Appelle de la methode du controller
        List<Hotel> actualHotels = hotelController.getHotelsWithMinRoomsGaragePlacesAndBabyBeds(minRooms, minGaragePlaces, minBabyBeds);

        // Verifier la reponse
        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository).findHotelsWithMinRoomsGaragePlacesAndBabyBeds(minRooms, minGaragePlaces, minBabyBeds);
    }

}
