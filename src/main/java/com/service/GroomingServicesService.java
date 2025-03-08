package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.GroomingServices;
import com.dao.GroomingServicesDAO;
import com.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class GroomingServicesService {

    @Autowired
    private GroomingServicesDAO groomingServicesDAO;

    public List<GroomingServices> getAllGroomingServices() {
        return groomingServicesDAO.findAll();
    }

    public GroomingServices getGroomingServiceById(Long serviceId) {
        return groomingServicesDAO.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Grooming service not found"));
    }

    public List<GroomingServices> getAvailableGroomingServices() {
        return groomingServicesDAO.findByAvailable(true);
    }

    public List<GroomingServices> getUnavailableGroomingServices() {
        return groomingServicesDAO.findByAvailable(false);
    }

    public GroomingServices addGroomingService(GroomingServices groomingService) {
        return groomingServicesDAO.save(groomingService);
    }

    public GroomingServices updateGroomingService(Long serviceId, GroomingServices groomingServiceDetails) {
        GroomingServices groomingService = getGroomingServiceById(serviceId);
        groomingService.setName(groomingServiceDetails.getName());
        groomingService.setDescription(groomingServiceDetails.getDescription());
        groomingService.setPrice(groomingServiceDetails.getPrice());
        groomingService.setAvailable(groomingServiceDetails.getAvailable());
        return groomingServicesDAO.save(groomingService);
    }
}

