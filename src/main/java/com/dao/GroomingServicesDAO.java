package com.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.GroomingServices;
import java.util.List;

public interface GroomingServicesDAO extends JpaRepository<GroomingServices, Long> {
    List<GroomingServices> findByAvailable(Boolean available);
}