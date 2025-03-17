import { Component, OnInit } from '@angular/core';
import { GroomingServicesService } from '../../uservices/groomingservices.service';
import { GroomingServices } from '../../../models/GroomingServices';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ImageDetails } from '../../../models/imagedetails';
 
@Component({
  selector: 'app-groomingservice',
  imports: [CommonModule, FormsModule],
  templateUrl: './groomingservices.component.html',
  styleUrls: ['./groomingservices.component.css']
})
export class UGroomingServicesComponent implements OnInit {
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
       // this.errorMessage = 'Failed to load grooming services. Please try again later.';
      }
    );
  }
 
  imageDetail:ImageDetails[]=[{
    imageUrl:"assets/services/g1.jpg",
    imageName:"Basic Grooming",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/services/g11.jpg",
    imageName:"Full Grooming",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/services/g2.jpg",
    imageName:"Bath Only",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/services/g8.jpg",
    imageName:"Nail Trim",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/services/g9.jpg",
    imageName:"Ear Cleaning",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/services/g3.jpg",
    imageName:"Teeth Cleaning",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/services/g5.jpg",
    imageName:"Flea Treatment",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/services/g4.jpg",
    imageName:"De-Shedding",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/services/g12.jpg",
    imageName:"Pawdicure",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/services/g7.jpg",
    imageName:"Spa Package",
    width:"300",
    height:"300"
  }

]
 
}