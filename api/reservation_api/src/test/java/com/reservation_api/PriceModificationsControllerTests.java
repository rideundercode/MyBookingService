package com.reservation_api;

import com.reservation_api.controller.PriceModificationDayOfWeekController;
import com.reservation_api.controller.PriceModificationNumberPeopleController;
import com.reservation_api.controller.ServiceController;
import com.reservation_api.model.PriceModificationDayOfWeek;
import com.reservation_api.model.PriceModificationNumberPeople;
import com.reservation_api.model.Service;
import com.reservation_api.repository.PriceModificationDayOfWeekRepository;
import com.reservation_api.repository.PriceModificationNumberPeopleRepository;
import com.reservation_api.repository.ServiceRepository;
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

public class PriceModificationsControllerTests {
    @Mock
    PriceModificationDayOfWeekRepository priceModificationDayOfWeekRepository;

    @Mock
    PriceModificationNumberPeopleRepository priceModificationNumberPeopleRepository;

    @InjectMocks
    private PriceModificationDayOfWeekController priceModificationDayOfWeekController;

    @InjectMocks
    private PriceModificationNumberPeopleController priceModificationNumberPeopleController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPriceModificationsNumberPeople() {
        // Arrange
        PriceModificationNumberPeople modif1 = new PriceModificationNumberPeople();
        PriceModificationNumberPeople modif2 = new PriceModificationNumberPeople();
        List<PriceModificationNumberPeople> modifs = Arrays.asList(modif1, modif2);

        when(priceModificationNumberPeopleRepository.findAll()).thenReturn(modifs);

        // Act
        ResponseEntity<?> response = priceModificationNumberPeopleController.getPriceModifications();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(modifs, response.getBody());
        verify(priceModificationNumberPeopleRepository, times(1)).findAll();
    }

    @Test
    public void testGetPriceModificationsWeekDay() {
        // Arrange
        PriceModificationDayOfWeek modif1 = new PriceModificationDayOfWeek();
        PriceModificationDayOfWeek modif2 = new PriceModificationDayOfWeek();
        List<PriceModificationDayOfWeek> modifs = Arrays.asList(modif1, modif2);

        when(priceModificationDayOfWeekRepository.findAll()).thenReturn(modifs);

        // Act
        ResponseEntity<?> response = priceModificationDayOfWeekController.getPriceModifications();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(modifs, response.getBody());
        verify(priceModificationDayOfWeekRepository, times(1)).findAll();
    }

    @Test
    public void testPriceModificationsNumberPeopleById() {
        // Arrange

        int id = 1;
        PriceModificationNumberPeople modif = new PriceModificationNumberPeople();
        modif.setId((long) id);
        when(priceModificationNumberPeopleRepository.findById(id)).thenReturn(Optional.of(modif));

        // Act
        ResponseEntity<?> response = priceModificationNumberPeopleController.getPriceModificationById("1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(modif), response.getBody());
        verify(priceModificationNumberPeopleRepository, times(1)).findById(id);
    }

    @Test
    public void testPriceModificationsDayWeekById() {
        // Arrange

        int id = 1;
        PriceModificationDayOfWeek modif = new PriceModificationDayOfWeek();
        modif.setId((long) id);
        when(priceModificationDayOfWeekRepository.findById(id)).thenReturn(Optional.of(modif));

        // Act
        ResponseEntity<?> response = priceModificationDayOfWeekController.getPriceModificationById("1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(modif), response.getBody());
        verify(priceModificationDayOfWeekRepository, times(1)).findById(id);
    }

    @Test
    public void testDeletePriceModificationNumberPeople() {
        // Arrange

        int id = 1;
        PriceModificationNumberPeople modif = new PriceModificationNumberPeople();
        modif.setId((long) id);
        when(priceModificationNumberPeopleRepository.findById(id)).thenReturn(Optional.of(modif));
        // Act
        ResponseEntity<?> response = priceModificationNumberPeopleController.deletePriceModification(1);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(priceModificationNumberPeopleRepository, times(1)).findById(id);
        verify(priceModificationNumberPeopleRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeletePriceModificationWeekDay() {
        // Arrange

        int id = 1;
        PriceModificationDayOfWeek modif = new PriceModificationDayOfWeek();
        modif.setId((long) id);
        when(priceModificationDayOfWeekRepository.findById(id)).thenReturn(Optional.of(modif));
        // Act
        ResponseEntity<?> response = priceModificationDayOfWeekController.deletePriceModification(1);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(priceModificationDayOfWeekRepository, times(1)).findById(id);
        verify(priceModificationDayOfWeekRepository, times(1)).deleteById(1);
    }


}
