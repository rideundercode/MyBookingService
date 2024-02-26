package com.reservation_api.controller;

import com.reservation_api.model.Service;
import com.reservation_api.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @RequestMapping(value = "/addService", method = RequestMethod.POST)
    public ResponseEntity<?> addService(@RequestParam("name") String name, @RequestParam("price") float price, @RequestParam(value="day_early", required=false) int day_early) {
        try {
            Service service = new Service(name, price, day_early);
            serviceRepository.save(service);
            return ResponseEntity.status(HttpStatus.CREATED).body(service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/deleteService", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteService(@RequestParam(value="id") Integer id) {
        try {
            Optional<Service> service = serviceRepository.findById(id);
            if (service.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
            } else {
                serviceRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/getServices", method = RequestMethod.GET)
    public ResponseEntity<?> getServices() {
        try {
            Iterable<Service> services = serviceRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(services);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @RequestMapping(value = "/getService/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getService(@PathVariable String id) {
        try {
            Integer intId = Integer.parseInt(id);
            Optional<Service> service = serviceRepository.findById(intId);
            if (service.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(service);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

}
