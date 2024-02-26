package com.billing_api;

import com.billing_api.controller.BillingAdminController;
import com.billing_api.controller.BillingController;
import com.billing_api.model.Billing;
import com.billing_api.repository.BillingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class BillingApiAdminApplicationTests {
        @Mock
        private BillingRepository billingRepository;

        @InjectMocks
        private BillingAdminController billingAdminController;
        @BeforeEach
        public void setup() {
                MockitoAnnotations.openMocks(this);
        }


        @Test
        public void testgetBillings() {
                // Arrange

                int id = 1;
                Billing billing = new Billing();
                billing.setId(id);
                billing.setUserId(id);
                ArrayList<Billing> billings = new ArrayList<Billing>();
                billings.add(billing);
                when(billingRepository.findAllBillings()).thenReturn(billings);

                // Act
                ResponseEntity<?> response = billingAdminController.getBillings();

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(billings, response.getBody());
                verify(billingRepository, times(1)).findAllBillings();
        }

        @Test
        public void testGetBillingById() {
                // Arrange

                int id = 1;
                Billing billing = new Billing();
                billing.setId(id);
                when(billingRepository.findById(id)).thenReturn(Optional.of(billing));

                // Act
                ResponseEntity<?> response = billingAdminController.getBillingById("1");

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(Optional.of(billing), response.getBody());
                verify(billingRepository, times(1)).findById(id);
        }

        @Test
        public void testGetBillingByReservationId() {
                // Arrange

                int id = 1;
                Billing billing = new Billing();
                billing.setId(id);
                when(billingRepository.findByReservationId(id)).thenReturn(billing);

                // Act
                ResponseEntity<?> response = billingAdminController.getBillingByReservationId("1");

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(billing, response.getBody());
                verify(billingRepository, times(1)).findByReservationId(id);
        }

        @Test
        public void testCancelBillingById() {
                // Arrange

                int id = 1;
                Billing billing = new Billing();
                billing.setId(id);
                when(billingRepository.findById(id)).thenReturn(Optional.of(billing));
                // Act
                ResponseEntity<?> response = billingAdminController.cancelBillingById("1");

                // Assert
                assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
                verify(billingRepository, times(1)).findById(id);
        }

        @Test
        public void testRefundBillingById() {
                // Arrange

                int id = 1;
                Billing billing = new Billing();
                billing.setId(id);
                when(billingRepository.findById(id)).thenReturn(Optional.of(billing));
                // Act
                ResponseEntity<?> response = billingAdminController.refundBillingById("1");

                // Assert
                assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
                verify(billingRepository, times(1)).findById(id);
        }



}
