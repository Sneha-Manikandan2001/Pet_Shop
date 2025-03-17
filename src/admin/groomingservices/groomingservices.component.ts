import { Component, OnInit } from '@angular/core';
import { GroomingServicesService } from '../../services/groomingservicesservices';
import { GroomingServices } from '../../models/GroomingServices';
import { HttpErrorResponse } from '@angular/common/http';
import {CommonModule} from '@angular/common'
import { FormsModule } from '@angular/forms'; 
@Component({
  selector: 'app-groomingservices',
  imports:[CommonModule,FormsModule],
  templateUrl: './groomingservices.component.html',
  styleUrls: ['./groomingservices.component.css']
})
export class GroomingServicesComponent implements OnInit {
  groomingServices: GroomingServices[] = [];
  newGroomingService: GroomingServices = {} as GroomingServices;
  showAddGroomingServiceForm: boolean = false;
  editGroomingServiceIndex: number | null = null;
  showEditForm: boolean = false;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';

  constructor(private groomingServiceService: GroomingServicesService) {}

  ngOnInit(): void {
    this.getGroomingServices();
  }

  // Fetch grooming services based on filter type
  getGroomingServices(): void {
    switch (this.filterType) {
      case 'getAll':
        this.groomingServiceService.getAllGroomingServices().subscribe(
          (data) => {
            this.groomingServices = data;
          },
          (error) => {
            console.error('Error fetching grooming services:', error);
            //this.errorMessage = 'Failed to load grooming services. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.groomingServiceService.getGroomingServiceById(Number(this.filterValue)).subscribe(
          (data) => {
            this.groomingServices = [data];
          },
          (error) => {
            console.error('Error fetching grooming service by ID:', error);
            //this.errorMessage = 'Failed to load grooming service. Please try again later.';
          }
        );
        break;
      case 'getAvailable':
        this.groomingServiceService.getAvailableGroomingServices().subscribe(
          (data) => {
            this.groomingServices = data;
          },
          (error) => {
            console.error('Error fetching available grooming services:', error);
            //this.errorMessage = 'Failed to load available grooming services. Please try again later.';
          }
        );
        break;
      case 'getUnavailable':
        this.groomingServiceService.getUnavailableGroomingServices().subscribe(
          (data) => {
            this.groomingServices = data;
          },
          (error) => {
            console.error('Error fetching unavailable grooming services:', error);
           // this.errorMessage = 'Failed to load unavailable grooming services. Please try again later.';
          }
        );
        break;
    }
  }

  // Add a new grooming service
  addGroomingService(): void {
    this.groomingServiceService.addGroomingService(this.newGroomingService).subscribe(
      (data) => {
        console.log('Grooming service added successfully', data);
        this.newGroomingService = {} as GroomingServices;
        this.showAddGroomingServiceForm = false;
        this.getGroomingServices();
      },
      (error) => console.error('Error adding grooming service:', error)
    );
  }

  toggleAddGroomingServiceForm(): void {
    this.showAddGroomingServiceForm = !this.showAddGroomingServiceForm;
  }

  // Enable edit mode for a specific grooming service
  editGroomingService(index: number): void {
    this.editGroomingServiceIndex = index;
  }

  // Save changes to grooming service details
  saveGroomingService(index: number): void {
    const updateGroomingService = this.groomingServices[index];
    if (updateGroomingService.serviceId !== undefined) {
      this.groomingServiceService.updateGroomingService(updateGroomingService.serviceId, updateGroomingService).subscribe(
        () => {
          this.editGroomingServiceIndex = null;
        },
        (error) => console.error('Error updating grooming service:', error)
      );
    } else {
      console.error('Error: Grooming service ID is undefined');
    }
  }

  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editGroomingServiceIndex = null;
  }
}