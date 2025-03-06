package com.service;

import com.model.Vaccinations;
import com.dao.VaccinationsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccinationService {

    @Autowired
    private VaccinationsDAO vaccinationDAO;

    public List<Vaccinations> getAllVaccinations() {
        return vaccinationDAO.findAll();
    }

    public Vaccinations getVaccinationById(Integer vaccinationId) {
        Optional<Vaccinations> vaccination = vaccinationDAO.findById(vaccinationId);
        return vaccination.orElse(null);
    }

    public List<Vaccinations> getAvailableVaccinations() {
        return vaccinationDAO.findByAvailable(true);
    }

    public List<Vaccinations> getUnavailableVaccinations() {
        return vaccinationDAO.findByAvailable(false);
    }

    public Vaccinations addVaccination(Vaccinations vaccination) {
        return vaccinationDAO.save(vaccination);
    }

    public Vaccinations updateVaccination(int vaccinationId, Vaccinations vaccination) {
        if (vaccinationDAO.existsById(vaccinationId)) {
            vaccination.setVaccinationId(vaccinationId);
            return vaccinationDAO.save(vaccination);
        }
        return null;
    }
}