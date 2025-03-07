package com.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Addresses;

@Repository
public interface AddressesDAO extends JpaRepository<Addresses, Long> {
    // You can define custom query methods here if needed
}