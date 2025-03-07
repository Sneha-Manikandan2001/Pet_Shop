package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.Vaccinations;
import com.service.VaccinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VaccinationControllerTest {

    @Mock
    private VaccinationService vaccinationService;

    @InjectMocks
    private VaccinationController vaccinationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllVaccinations() {
        List<Vaccinations> vaccinations = Arrays.asList(new Vaccinations(1, "Vaccine1", "Description1", 100.0, true));
        when(vaccinationService.getAllVaccinations()).thenReturn(vaccinations);

        List<Vaccinations> result = vaccinationController.getAllVaccinations();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(vaccinationService, times(1)).getAllVaccinations();
    }

    @Test
    public void testGetVaccinationById() {
        Vaccinations vaccination = new Vaccinations(1, "Vaccine1", "Description1", 100.0, true);
        when(vaccinationService.getVaccinationById(1)).thenReturn(vaccination);

        Vaccinations result = vaccinationController.getVaccinationById(1);
        assertNotNull(result);
        assertEquals("Vaccine1", result.getName());
        verify(vaccinationService, times(1)).getVaccinationById(1);
    }

    @Test
    public void testGetAvailableVaccinations() {
        List<Vaccinations> vaccinations = Arrays.asList(new Vaccinations(1, "Vaccine1", "Description1", 100.0, true));
        when(vaccinationService.getAvailableVaccinations()).thenReturn(vaccinations);

        List<Vaccinations> result = vaccinationController.getAvailableVaccinations();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(vaccinationService, times(1)).getAvailableVaccinations();
    }

    @Test
    public void testGetUnavailableVaccinations() {
        List<Vaccinations> vaccinations = Arrays.asList(new Vaccinations(2, "Vaccine2", "Description2", 200.0, false));
        when(vaccinationService.getUnavailableVaccinations()).thenReturn(vaccinations);

        List<Vaccinations> result = vaccinationController.getUnavailableVaccinations();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(vaccinationService, times(1)).getUnavailableVaccinations();
    }
    @Test
    public void testAddVaccination() {
        Vaccinations vaccination = new Vaccinations(1, "Vaccine1", "Description1", 100.0, true);
        doAnswer(invocation -> {
            return null;
        }).when(vaccinationService).addVaccination(vaccination);

        ResponseEntity<Map<String, Object>> response = vaccinationController.addVaccination(vaccination);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Vaccination added successfully", response.getBody().get("message"));
        verify(vaccinationService, times(1)).addVaccination(vaccination);
    }

    @Test
    public void testUpdateVaccination() {
        Vaccinations vaccination = new Vaccinations(1, "Vaccine1", "Description1", 100.0, true);
        doAnswer(invocation -> {
            return null;
        }).when(vaccinationService).updateVaccination(1, vaccination);

        ResponseEntity<Map<String, Object>> response = vaccinationController.updateVaccination(1, vaccination);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Vaccination updated successfully", response.getBody().get("message"));
        verify(vaccinationService, times(1)).updateVaccination(1, vaccination);
    }}