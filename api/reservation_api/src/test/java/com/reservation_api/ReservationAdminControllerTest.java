package com.reservation_api;

import com.reservation_api.model.Reservation;
import com.reservation_api.controller.ReservationAdminController;
import com.reservation_api.repository.ReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationAdminControllerTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationAdminController reservationAdminController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReservations_Success() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(reservationRepository.findAllReservations()).thenReturn(reservations);

        // Act
        ResponseEntity<?> response = reservationAdminController.getReservations();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
        verify(reservationRepository, times(1)).findAllReservations();
    }

    @Test
    public void testGetReservationById_ExistingId_Success() {
        // Arrange
        Integer id = 1;
        Reservation reservation = new Reservation();
        Optional<Reservation> optionalReservation = Optional.of(reservation);
        when(reservationRepository.findById(id)).thenReturn(optionalReservation);

        // Act
        ResponseEntity<?> response = reservationAdminController.getReservationById(id.toString());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalReservation, response.getBody());
        verify(reservationRepository, times(1)).findById(id);
    }

    @Test
    public void testGetReservationById_NonExistingId_ReturnsNotFound() {
        // Arrange
        Integer id = 1;
        when(reservationRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = reservationAdminController.getReservationById(id.toString());

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(reservationRepository, times(1)).findById(id);
    }
}
