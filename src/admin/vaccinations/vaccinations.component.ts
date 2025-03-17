import { Component, OnInit } from '@angular/core';
import { VaccinationService } from '../../services/vaccationservice';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Vaccinations } from '../../models/Vaccinations';

@Component({
  selector: 'app-vaccination',
  imports: [CommonModule, FormsModule],
  templateUrl: './vaccinations.component.html',
  styleUrls: ['./vaccinations.component.css']
})
export class VaccinationsComponent implements OnInit {
  vaccinations: Vaccinations[] = [];
  newVaccination: Vaccinations = {} as Vaccinations;
  showAddVaccinationForm: boolean = false;
  editVaccinationIndex: number | null = null;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';

  constructor(private vaccinationService: VaccinationService) {}

  ngOnInit(): void {
    this.getVaccinations();
  }

  // Fetch vaccinations based on filter type
  getVaccinations(): void {
    switch (this.filterType) {
      case 'getAll':
        this.vaccinationService.getAllVaccinations().subscribe(
          (data) => {
            this.vaccinations = data;
          },
          (error) => {
            console.error('Error fetching vaccinations:', error);
           // this.errorMessage = 'Failed to load vaccinations. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.vaccinationService.getVaccinationById(Number(this.filterValue)).subscribe(
          (data) => {
            this.vaccinations = [data];
          },
          (error) => {
            console.error('Error fetching vaccination by ID:', error);
           // this.errorMessage = 'Failed to load vaccination. Please try again later.';
          }
        );
        break;
      case 'getAvailable':
        this.vaccinationService.getAvailableVaccinations().subscribe(
          (data) => {
            this.vaccinations = data;
          },
          (error) => {
            console.error('Error fetching available vaccinations:', error);
            //this.errorMessage = 'Failed to load vaccinations. Please try again later.';
          }
        );
        break;
      case 'getUnavailable':
        this.vaccinationService.getUnavailableVaccinations().subscribe(
          (data) => {
            this.vaccinations = data;
          },
          (error) => {
            console.error('Error fetching unavailable vaccinations:', error);
            //this.errorMessage = 'Failed to load vaccinations. Please try again later.';
          }
        );
        break;
    }
  }

  // Add a new vaccination
  addVaccination(): void {
    this.vaccinationService.addVaccination(this.newVaccination).subscribe(
      (data) => {
        console.log('Vaccination added successfully', data);
        this.newVaccination = {} as Vaccinations;
        this.showAddVaccinationForm = false;
        this.getVaccinations();
      },
      (error) => console.error('Error adding vaccination:', error)
    );
  }

  toggleAddVaccinationForm(): void {
    this.showAddVaccinationForm = !this.showAddVaccinationForm;
  }

  // Enable edit mode for a specific vaccination
  editVaccination(index: number): void {
    this.editVaccinationIndex = index;
  }

  // Save changes to vaccination details
  saveVaccination(index: number): void {
    const updateVaccination = this.vaccinations[index];
    this.vaccinationService.updateVaccination(updateVaccination.vaccinationId, updateVaccination).subscribe(
      () => {
        this.editVaccinationIndex = null;
      },
      (error) => console.error('Error updating vaccination:', error)
    );
  }

  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editVaccinationIndex = null;
  }
}

