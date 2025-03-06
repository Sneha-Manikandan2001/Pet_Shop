package com.service;

import com.dao.VaccinationsDAO;
import com.model.Vaccinations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VaccinationServiceTest {

    @Mock
    private VaccinationsDAO vaccinationDAO;

    @InjectMocks
    private VaccinationService vaccinationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVaccinations() {
        Vaccinations vaccination1 = new Vaccinations(1L, "Vaccine1", "Description1", 100.0, false);
        Vaccinations vaccination2 = new Vaccinations(2L, "Vaccine2", "Description2", 200.0, false);
        when(vaccinationDAO.findAll()).thenReturn(Arrays.asList(vaccination1, vaccination2));

        List<Vaccinations> vaccinations = vaccinationService.getAllVaccinations();
        assertEquals(2, vaccinations.size());
        verify(vaccinationDAO, times(1)).findAll();
    }

    @Test
    void testGetVaccinationById() {
        Vaccinations vaccination = new Vaccinations(1L, "Vaccine1", "Description1", 100.0, false);
        when(vaccinationDAO.findById(1)).thenReturn(Optional.of(vaccination));

        Vaccinations result = vaccinationService.getVaccinationById(1);
        assertNotNull(result);
        assertEquals("Vaccine1", result.getName());
        verify(vaccinationDAO, times(1)).findById(1);
    }

    @Test
    void testGetAvailableVaccinations() {
        Vaccinations vaccination = new Vaccinations(1L, "Vaccine1", "Description1", 100.0, false);
        when(vaccinationDAO.findByAvailable(true)).thenReturn(Arrays.asList(vaccination));

        List<Vaccinations> vaccinations = vaccinationService.getAvailableVaccinations();
        assertEquals(1, vaccinations.size());
        assertEquals("Vaccine1", vaccinations.get(0).getName());
        verify(vaccinationDAO, times(1)).findByAvailable(true);
    }

    @Test
    void testGetUnavailableVaccinations() {
        Vaccinations vaccination = new Vaccinations(1L, "Vaccine1", "Description1", 100.0, false);
        when(vaccinationDAO.findByAvailable(false)).thenReturn(Arrays.asList(vaccination));

        List<Vaccinations> vaccinations = vaccinationService.getUnavailableVaccinations();
        assertEquals(1, vaccinations.size());
        assertEquals("Vaccine1", vaccinations.get(0).getName());
        verify(vaccinationDAO, times(1)).findByAvailable(false);
    }

    @Test
    void testAddVaccination() {
        Vaccinations vaccination = new Vaccinations(1L, "Vaccine1", "Description1", 100.0, false);
        when(vaccinationDAO.save(vaccination)).thenReturn(vaccination);

        Vaccinations result = vaccinationService.addVaccination(vaccination);
        assertNotNull(result);
        assertEquals("Vaccine1", result.getName());
        verify(vaccinationDAO, times(1)).save(vaccination);
    }

    @Test
    void testUpdateVaccination() {
        Vaccinations vaccination = new Vaccinations(1L, "Vaccine1", "Description1", 100.0, false);
        when(vaccinationDAO.existsById(1)).thenReturn(true);
        when(vaccinationDAO.save(vaccination)).thenReturn(vaccination);

        Vaccinations result = vaccinationService.updateVaccination(1, vaccination);
        assertNotNull(result);
        assertEquals("Vaccine1", result.getName());
        verify(vaccinationDAO, times(1)).existsById(1);
        verify(vaccinationDAO, times(1)).save(vaccination);
    }
}
