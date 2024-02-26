package com.reservation_api;

import com.reservation_api.controller.ReservationController;
import com.reservation_api.controller.ServiceController;
import com.reservation_api.model.Reservation;
import com.reservation_api.model.Service;
import com.reservation_api.repository.PriceModificationDayOfWeekRepository;
import com.reservation_api.repository.PriceModificationNumberPeopleRepository;
import com.reservation_api.repository.ReservationRepository;
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

public class ServiceControllerTests {
    @Mock
    ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceController serviceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetServices() {
        // Arrange
        Service service1 = new Service();
        Service service2 = new Service();
        List<Service> reservations = Arrays.asList(service1, service2);

        when(serviceRepository.findAll()).thenReturn(reservations);

        // Act
        ResponseEntity<?> response = serviceController.getServices();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
        verify(serviceRepository, times(1)).findAll();
    }

    @Test
    public void testGetServiceById() {
        // Arrange

        int id = 1;
        Service service = new Service();
        service.setId((long) id);
        when(serviceRepository.findById(id)).thenReturn(Optional.of(service));

        // Act
        ResponseEntity<?> response = serviceController.getService("1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(service), response.getBody());
        verify(serviceRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteService() {
        // Arrange

        int id = 1;
        Service service = new Service();
        service.setId((long) id);
        when(serviceRepository.findById(id)).thenReturn(Optional.of(service));
        // Act
        ResponseEntity<?> response = serviceController.deleteService(1);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(serviceRepository, times(1)).findById(id);
        verify(serviceRepository, times(1)).deleteById(1);
    }




    @Test
    public void testAddService() {
        // Arrange

        String name = "lit";
        float price = 1;
        long id = 1;
        int day_early = 0;
        int numberPeople = 1;
        Service service = new Service();
        service.setId(id);
        service.setPrice(price);
        service.setName(name);
        service.setDay_early(day_early);


        when(serviceRepository.save(service)).thenReturn(service);
        // Act
        ResponseEntity<?> response = serviceController.addService(name, price, day_early);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


}
