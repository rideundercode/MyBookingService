package com.reservation_api;

import com.reservation_api.model.Reservation;
import com.reservation_api.repository.PriceModificationDayOfWeekRepository;
import com.reservation_api.repository.PriceModificationNumberPeopleRepository;
import com.reservation_api.repository.ReservationRepository;
import com.reservation_api.controller.ReservationController;
import com.reservation_api.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationControllerTests {
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    PriceModificationNumberPeopleRepository priceModificationNumberPeopleRepository;

    @Mock
    ServiceRepository serviceRepository;

    @Mock
    PriceModificationDayOfWeekRepository priceModificationDayOfWeekRepository;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReservation_Success() {
        // Arrange
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);

        when(reservationRepository.findAllValidReservations()).thenReturn(reservations);

        // Act
        ResponseEntity<?> response = reservationController.getReservation();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
        verify(reservationRepository, times(1)).findAllValidReservations();
    }

    @Test
    public void testGetReservationByIdByUserId() {
        // Arrange

        int id = 1;
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setUserId(id);
        when(reservationRepository.findByIdUserID(id,id)).thenReturn(reservation);

        // Act
        ResponseEntity<?> response = reservationController.getReservationByIdByUserId("1","1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservation, response.getBody());
        verify(reservationRepository, times(1)).findByIdUserID(id, id);
    }

    @Test
    public void testCancelReservationById() {
        // Arrange

        int id = 1;
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setUserId(id);
        when(reservationRepository.findByIdUserID(id,id)).thenReturn(reservation);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        // Act
        ResponseEntity<?> response = reservationController.cancelReservationById("1",1);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(reservation, response.getBody());
        verify(reservationRepository, times(1)).findByIdUserID(id, id);
        verify(reservationRepository, times(1)).save(reservation);
    }


    @Test
    public void testGetReservationsByHotelId() {
        // Arrange

        int id = 1;
        Reservation reservation = new Reservation();
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        reservation.setId(id);
        reservation.setUserId(id);
        reservations.add(reservation);
        when(reservationRepository.findValidByHotelId(id)).thenReturn(reservations);

        // Act
        ResponseEntity<?> response = reservationController.getReservationsByHotelId("1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
        verify(reservationRepository, times(1)).findValidByHotelId(id);
    }

    @Test
    public void testGetReservationsByUserId() {
        // Arrange

        int id = 1;
        Reservation reservation = new Reservation();
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        reservation.setId(id);
        reservation.setUserId(id);
        reservations.add(reservation);
        when(reservationRepository.findByUserId(id)).thenReturn(reservations);

        // Act
        ResponseEntity<?> response = reservationController.getReservationsByUserId("1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
        verify(reservationRepository, times(1)).findByUserId(id);
    }

    @Test
    public void testGetReservationsByHotelsId() {
        // Arrange

        int id = 1;
        Reservation reservation = new Reservation();
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        reservation.setId(id);
        reservation.setUserId(id);
        reservations.add(reservation);
        when(reservationRepository.findValidByRoomHotelId(id, id)).thenReturn(reservations);

        // Act
        ResponseEntity<?> response = reservationController.getReservationsByHotelId("1", "1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
        verify(reservationRepository, times(1)).findValidByRoomHotelId(id, id);
    }

    @Test
    public void testGetReservationsByUserHotelId() {
        // Arrange

        int id = 1;
        Reservation reservation = new Reservation();
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        reservation.setId(id);
        reservation.setUserId(id);
        reservations.add(reservation);
        when(reservationRepository.findByHotelUserId(id, id)).thenReturn(reservations);

        // Act
        ResponseEntity<?> response = reservationController.getReservationsByUserHotelId("1", "1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
        verify(reservationRepository, times(1)).findByHotelUserId(id, id);
    }

    @Test
    public void testAddReservation() {
        // Arrange

        int roomId = 1;
        int hotelId = 1;
        int userId = 1;
        int numberPeople = 1;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(2);
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setRoomId(roomId);
        reservation.setHotelId(hotelId);
        reservation.setUserId(userId);
        reservation.setNumberPeople(numberPeople);
        reservation.setReservationStart(start);
        reservation.setReservationEnd(end);

        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationRepository.verifAlreadyReserved(hotelId, roomId, start, end)).thenReturn(new ArrayList<Reservation>());
        when(priceModificationDayOfWeekRepository.getByDayWeek(1)).thenReturn(null);
        when(priceModificationDayOfWeekRepository.getByDayWeek(2)).thenReturn(null);
        when(priceModificationDayOfWeekRepository.getByDayWeek(3)).thenReturn(null);
        when(priceModificationDayOfWeekRepository.getByDayWeek(4)).thenReturn(null);
        when(priceModificationDayOfWeekRepository.getByDayWeek(5)).thenReturn(null);
        when(priceModificationDayOfWeekRepository.getByDayWeek(6)).thenReturn(null);
        when(priceModificationDayOfWeekRepository.getByDayWeek(7)).thenReturn(null);
        when(priceModificationDayOfWeekRepository.getByDayWeek(0)).thenReturn(null);
        // Act
        ResponseEntity<?> response = reservationController.addReservation(roomId, hotelId, userId, numberPeople, start, end, "1,2");

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


}
