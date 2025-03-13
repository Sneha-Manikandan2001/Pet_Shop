import { Component, OnInit } from '@angular/core';
import { GroomingServicesService } from '../../services/groomingservices.service';
import { GroomingServices } from '../../../models/groomingservice';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 
@Component({
  selector: 'app-groomingservice',
  imports: [CommonModule, FormsModule],
  templateUrl: './groomingservices.component.html',
  styleUrls: ['./groomingservices.component.css']
})
export class GroomingServicesComponent implements OnInit {
  groomingServices: GroomingServices[] = [];
  newGroomingService: GroomingServices = {} as GroomingServices;
  showAddGroomingServiceForm: boolean = false;
  errorMessage: string | null = null;
 
  constructor(private groomingServiceService: GroomingServicesService) {}
 
  ngOnInit(): void {
    this.getGroomingServices();
  }
 
  // Fetch all grooming services
  getGroomingServices(): void {
    this.groomingServiceService.getAllGroomingServices().subscribe(
      (data) => {
        this.groomingServices = data;
      },
      (error: HttpErrorResponse) => {
        console.error('Error fetching grooming services:', error);
        this.errorMessage = 'Failed to load grooming services. Please try again later.';
      }
    );
  }
 
 
 
}