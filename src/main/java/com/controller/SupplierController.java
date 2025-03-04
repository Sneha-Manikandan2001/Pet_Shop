package com.controller;

import com.model.Suppliers;
import com.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List<Suppliers> getAllSuppliers() {
        List<Suppliers> suppliers = supplierService.getAllSuppliers();
        if (suppliers == null || suppliers.isEmpty()) {
            throw new RuntimeException("No suppliers found");
        }
        return suppliers;
    }

    @GetMapping("/{supplierId}")
    public Suppliers getSupplierById(@PathVariable Long supplierId) {
        Suppliers supplier = supplierService.getSupplierById(supplierId);
        if (supplier == null) {
            throw new RuntimeException("Supplier not found");
        }
        return supplier;
    }

    @GetMapping("/name/{name}")
    public List<Suppliers> getSuppliersByName(@PathVariable String name) {
        List<Suppliers> suppliers = supplierService.getSuppliersByName(name);
        if (suppliers == null || suppliers.isEmpty()) {
            throw new RuntimeException("No suppliers found with the given name");
        }
        return suppliers;
    }

    @GetMapping("/city/{cityName}")
    public List<Suppliers> getSuppliersByCity(@PathVariable String cityName) {
        List<Suppliers> suppliers = supplierService.getSuppliersByCity(cityName);
        if (suppliers == null || suppliers.isEmpty()) {
            throw new RuntimeException("No suppliers found in the given city");
        }
        return suppliers;
    }

    @GetMapping("/state/{stateName}")
    public List<Suppliers> getSuppliersByState(@PathVariable String stateName) {
        List<Suppliers> suppliers = supplierService.getSuppliersByState(stateName);
        if (suppliers == null || suppliers.isEmpty()) {
            throw new RuntimeException("No suppliers found in the given state");
        }
        return suppliers;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addSupplier(@RequestBody Suppliers supplier) {
        if (supplier == null) {
            throw new RuntimeException("Invalid supplier data");
        }
        supplierService.addSupplier(supplier);
        
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Supplier added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{supplierId}")
    public ResponseEntity<Map<String, Object>> updateSupplier(@PathVariable Long supplierId, @RequestBody Suppliers supplier) {
        if (supplier == null) {
            throw new RuntimeException("Invalid supplier data");
        }
        supplierService.updateSupplier(supplierId, supplier);
        
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Supplier updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}