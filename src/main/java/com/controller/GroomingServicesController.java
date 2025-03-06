package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.GroomingServices;
import com.service.GroomingServicesService;

@RestController
@RequestMapping("/api/v1/services")
public class GroomingServicesController {

    @Autowired
    private GroomingServicesService groomingServicesService;

    @GetMapping
    public ResponseEntity<List<GroomingServices>> getAllGroomingServices() {
        List<GroomingServices> services = groomingServicesService.getAllGroomingServices();
        if (services == null || services.isEmpty()) {
            throw new RuntimeException("No grooming services found");
        }
        return ResponseEntity.ok(services);
    }
    @GetMapping("/{service_id}")
    public ResponseEntity<GroomingServices> getGroomingService(@PathVariable Long service_id) {
        GroomingServices groomingService = groomingServicesService.getGroomingServiceById(service_id);
        if (groomingService == null) {
            throw new RuntimeException("Grooming service not found");
        }
        return ResponseEntity.ok(groomingService);
    }

    @GetMapping("/available")
    public ResponseEntity<List<GroomingServices>> getAvailableGroomingServices() {
        List<GroomingServices> services = groomingServicesService.getAvailableGroomingServices();
        if (services == null || services.isEmpty()) {
            throw new RuntimeException("No available grooming services found");
        }
        return ResponseEntity.ok(services);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<GroomingServices>> getUnavailableGroomingServices() {
        List<GroomingServices> services = groomingServicesService.getUnavailableGroomingServices();
        if (services == null || services.isEmpty()) {
            throw new RuntimeException("No unavailable grooming services found");
        }
        return ResponseEntity.ok(services);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addGroomingService(@RequestBody GroomingServices groomingService) {
        if (groomingService == null) {
            throw new RuntimeException("Invalid grooming service data");
        }
        groomingServicesService.addGroomingService(groomingService);
        
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Grooming service added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{service_id}")
    public ResponseEntity<Map<String, Object>> updateGroomingService(@PathVariable Long service_id, @RequestBody GroomingServices groomingServiceDetails) {
        if (groomingServiceDetails == null) {
            throw new RuntimeException("Invalid grooming service data");
        }
        groomingServicesService.updateGroomingService(service_id, groomingServiceDetails);
        
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Grooming service updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}