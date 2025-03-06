package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.Vaccinations;
import com.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/vaccinations")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    @GetMapping
    public List<Vaccinations> getAllVaccinations() {
        List<Vaccinations> vaccinations = vaccinationService.getAllVaccinations();
        if (vaccinations == null || vaccinations.isEmpty()) {
            throw new ResourceNotFoundException("No vaccinations found");
        }
        return vaccinations;
    }

    @GetMapping("/{vaccination_Id}")
    public Vaccinations getVaccinationById(@PathVariable int vaccination_Id) {
        Vaccinations vaccination = vaccinationService.getVaccinationById(vaccination_Id);
        if (vaccination == null) {
            throw new ResourceNotFoundException("Vaccination not found");
        }
        return vaccination;
    }

    @GetMapping("/available")
    public List<Vaccinations> getAvailableVaccinations() {
        List<Vaccinations> vaccinations = vaccinationService.getAvailableVaccinations();
        if (vaccinations == null || vaccinations.isEmpty()) {
            throw new ResourceNotFoundException("No available vaccinations found");
        }
        return vaccinations;
    }

    @GetMapping("/unavailable")
    public List<Vaccinations> getUnavailableVaccinations() {
        List<Vaccinations> vaccinations = vaccinationService.getUnavailableVaccinations();
        if (vaccinations == null || vaccinations.isEmpty()) {
            throw new ResourceNotFoundException("No unavailable vaccinations found");
        }
        return vaccinations;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addVaccination(@RequestBody Vaccinations vaccination) {
        if (vaccination == null|| vaccination.getName() == null || vaccination.getName().isEmpty() ||
                vaccination.getDescription() == null || vaccination.getDescription().isEmpty() ||
                vaccination.getPrice() <= 0) {
            throw new RuntimeException("Invalid");
        }
        vaccinationService.addVaccination(vaccination);
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Vaccination added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{vaccination_id}")
    public ResponseEntity<Map<String, Object>> updateVaccination(@PathVariable("vaccination_id") int vaccinationId,
                                                                 @RequestBody Vaccinations vaccination) {
        if (vaccination == null || vaccination.getName() == null || vaccination.getName().isEmpty() ||
            vaccination.getDescription() == null || vaccination.getDescription().isEmpty() ||
            vaccination.getPrice() <= 0) {
            throw new RuntimeException("Invalids");
        }
        vaccinationService.updateVaccination(vaccinationId, vaccination);
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Vaccination updated successfully");
        return ResponseEntity.ok(response);
    }
    
}