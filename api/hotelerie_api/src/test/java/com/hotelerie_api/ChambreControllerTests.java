package com.hotelerie_api;

import com.hotelerie_api.controller.ChambreController;
import com.hotelerie_api.model.Chambre;
import com.hotelerie_api.repository.ChambreRepository;
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

public class ChambreControllerTests {

    @Mock
    ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreController chambreController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetChambreByHotelId() {
        // Arrange
        Chambre chambre1 = new Chambre();
        Chambre chambre2 = new Chambre();
        List<Chambre> chambres = Arrays.asList(chambre1, chambre2);

        when(chambreRepository.findByHotelId(1)).thenReturn(chambres);

        // Act
        List<Chambre> response = chambreController.getChambreByHotelId(1);

        // Assert
        assertEquals(chambres, response);
        verify(chambreRepository, times(1)).findByHotelId(1);
    }

    @Test
    public void testGetById() {
        // Arrange
        Chambre chambre1 = new Chambre();
        chambre1.setId(1L);

        when(chambreRepository.findById(1)).thenReturn(Optional.of(chambre1));

        // Act
        Optional<Chambre> response = chambreController.getById(1);

        // Assert
        assertEquals(Optional.of(chambre1), response);
        verify(chambreRepository, times(1)).findById(1);
    }

    @Test
    public void testDeleteChambre() {
        // Arrange
        Chambre chambre1 = new Chambre();
        chambre1.setId(1L);

        when(chambreRepository.findById(1)).thenReturn(Optional.of(chambre1));

        // Act
        ResponseEntity<?> response = chambreController.deleteChambre(1);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(chambre1.getId().intValue(), response.getBody());
        verify(chambreRepository, times(1)).findById(1);
    }

    @Test
    public void testCreateChambre() {
        // Arrange
        Chambre chambre1 = new Chambre();
        chambre1.setId(1L);

        when(chambreRepository.save(chambre1)).thenReturn(chambre1);

        // Act
        Chambre response = chambreController.createChambre(chambre1);

        // Assert
        assertEquals(chambre1.getId(), response.getId());
        verify(chambreRepository, times(1)).save(chambre1);
    }
}
