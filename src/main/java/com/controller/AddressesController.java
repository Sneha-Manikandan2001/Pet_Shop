package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dao.AddressesDAO;
import com.model.Addresses;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address")
public class AddressesController {

    @Autowired
    private AddressesDAO addressesDAO;

    @GetMapping
    public ResponseEntity<List<Addresses>> getAllAddresses() {
        List<Addresses> addresses = addressesDAO.findAll();
        if (addresses == null || addresses.isEmpty()) {
            throw new RuntimeException("Validation failed");
        }
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{address_id}")
    public ResponseEntity<Addresses> getAddressById(@PathVariable Long address_id) {
        Optional<Addresses> address = addressesDAO.findById(address_id);
        if (address.isPresent()) {
            return ResponseEntity.ok(address.get());
        } else {
            throw new RuntimeException("Validation failed");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addAddress(@RequestBody Addresses address) {
        if (address == null) {
            throw new RuntimeException("Validation failed");
        }
        addressesDAO.save(address);
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Address added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{address_id}")
    public ResponseEntity<Map<String, Object>> updateAddress(@PathVariable Long address_id, @RequestBody Addresses addressDetails) {
        if (addressDetails == null) {
            throw new RuntimeException("Validation failed");
        }
        Optional<Addresses> addressOptional = addressesDAO.findById(address_id);
        if (addressOptional.isPresent()) {
            Addresses address = addressOptional.get();
            address.setStreet(addressDetails.getStreet());
            address.setCity(addressDetails.getCity());
            address.setState(addressDetails.getState());
            address.setZipCode(addressDetails.getZipCode());
            addressesDAO.save(address);
            Map<String, Object> response = new HashMap<>();
            response.put("timeStamp", LocalDate.now().toString());
            response.put("message", "Address updated successfully");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new RuntimeException("Validation failed");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        Map<String, String> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Validation failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}