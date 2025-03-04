package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SupplierDAO;
import com.model.Suppliers;

@Service
public class SupplierService {

    @Autowired
    private SupplierDAO supplierDAO;

    public List<Suppliers> getAllSuppliers() {
        return supplierDAO.findAll();
    }

    public Suppliers getSupplierById(Long supplierId) {
        return supplierDAO.findById(supplierId).orElse(null);
    }

    public List<Suppliers> getSuppliersByName(String name) {
        return supplierDAO.findByName(name);
    }

    public List<Suppliers> getSuppliersByCity(String cityName) {
        return supplierDAO.findByCity(cityName);
    }

    public List<Suppliers> getSuppliersByState(String stateName) {
        return supplierDAO.findByState(stateName);
    }

    public Suppliers addSupplier(Suppliers supplier) {
        return supplierDAO.save(supplier);
    }

    public Suppliers updateSupplier(Long supplierId, Suppliers supplier) {
        Suppliers existingSupplier = supplierDAO.findById(supplierId).orElse(null);
        if (existingSupplier != null) {
            existingSupplier.setName(supplier.getName());
            existingSupplier.setContactPerson(supplier.getContactPerson());
            existingSupplier.setPhoneNumber(supplier.getPhoneNumber());
            existingSupplier.setEmail(supplier.getEmail());
            existingSupplier.setAddress(supplier.getAddress());
            return supplierDAO.save(existingSupplier);
        }
        return null;
    }
}