package com.billing_api;

import com.billing_api.controller.BillingController;
import com.billing_api.model.Billing;
import com.billing_api.repository.BillingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


class BillingApiApplicationTests {
        @Mock
        private BillingRepository billingRepository;

        @InjectMocks
        private BillingController billingController;
        @BeforeEach
        public void setup() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testGetBillingByReservationId() {
                // Arrange

                int id = 1;
                Billing billing = new Billing();
                billing.setId(id);
                when(billingRepository.findByReservationId(id)).thenReturn(billing);

                // Act
                ResponseEntity<?> response = billingController.getBillingByReservationId("1");

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(billing, response.getBody());
                verify(billingRepository, times(1)).findByReservationId(id);
        }

        @Test
        public void testGetBillingByUserId() {
                // Arrange

                int id = 1;
                Billing billing = new Billing();
                billing.setId(id);
                billing.setUserId(id);
                ArrayList<Billing> billings = new ArrayList<Billing>();
                billings.add(billing);
                when(billingRepository.findByUserId(id)).thenReturn(billings);

                // Act
                ResponseEntity<?> response = billingController.getBillingsByUserId("1");

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(billings, response.getBody());
                verify(billingRepository, times(1)).findByUserId(id);
        }

        @Test
        public void testGetBillingByUserReservationId() {
                // Arrange

                int id = 1;
                Billing billing = new Billing();
                billing.setId(id);
                ArrayList<Billing> billings = new ArrayList<Billing>();
                billings.add(billing);
                when(billingRepository.findByHotelUserId(id, id)).thenReturn(billings);

                // Act
                ResponseEntity<?> response = billingController.getBillingsByUserReservationId("1", "1");

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(billings, response.getBody());
                verify(billingRepository, times(1)).findByHotelUserId(id, id);
        }

        @Test
        public void testCancelBillingById() {
                // Arrange

                int id = 1;
                Billing billing = new Billing();
                billing.setId(id);
                when(billingRepository.findByIdUserID(id, id)).thenReturn(billing);
                // Act
                ResponseEntity<?> response = billingController.cancelBillingById("1", id);

                // Assert
                assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
                verify(billingRepository, times(1)).findByIdUserID(id, id);
        }




        @Test
        public void testAddBilling() {
                // Arrange

                String roomType = "double lit";
                int reservationId = 1;
                int userId = 1;
                Billing billing = new Billing();
                billing.setId(1);
                billing.setReservationId(1);
                billing.setUserId(1);


                when(billingRepository.save(billing)).thenReturn(billing);
                // Act
                ResponseEntity<?> response = billingController.addBilling(roomType, reservationId, userId);

                // Assert
                assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }

}
