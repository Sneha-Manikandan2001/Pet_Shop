package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AddressesDAO;
import com.model.Addresses;

import java.util.List;
import java.util.Optional;

@Service
public class AddressesService {

    @Autowired
    private AddressesDAO addressesDAO;

    public List<Addresses> getAllAddresses() {
        return addressesDAO.findAll();
    }

    public Optional<Addresses> getAddressById(Long addressId) {
        return addressesDAO.findById(addressId);
    }

    public Addresses addAddress(Addresses address) {
        return addressesDAO.save(address);
    }

    public Addresses updateAddress(Long addressId, Addresses addressDetails) {
        Optional<Addresses> addressOptional = addressesDAO.findById(addressId);
        if (addressOptional.isPresent()) {
            Addresses address = addressOptional.get();
            address.setStreet(addressDetails.getStreet());
            address.setCity(addressDetails.getCity());
            address.setState(addressDetails.getState());
            address.setZipCode(addressDetails.getZipCode());
            return addressesDAO.save(address);
        } else {
            return null; // or throw an exception
        }
    }
}