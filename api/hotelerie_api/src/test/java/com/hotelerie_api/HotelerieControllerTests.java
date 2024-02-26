package com.hotelerie_api;

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

public class HotelerieControllerTests {
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReservation_Success() {

    }

    @Test
    public void testGetReservationById_ExistingId_Success() {
        // Arrange

    }

    @Test
    public void testGetReservationById_NonExistingId_ReturnNotFound() {
        // Arrange

    }
}
