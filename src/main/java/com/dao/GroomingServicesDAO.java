package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.GroomingServices;
import java.util.List;

@Repository
public interface GroomingServicesDAO extends JpaRepository<GroomingServices, Long> {
    List<GroomingServices> findByAvailable(Boolean available);
}