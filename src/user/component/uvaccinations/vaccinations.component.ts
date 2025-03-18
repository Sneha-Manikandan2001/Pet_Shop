import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Vaccinations } from '../../../models/Vaccinations';
import { VaccinationsService } from '../../uservices/vaccinations.service';
import { ImageDetails } from '../../../models/imagedetails';

@Component({
  selector: 'app-vaccinations',
  imports: [CommonModule],
  templateUrl: './vaccinations.component.html',
  styleUrls: ['./vaccinations.component.css']
})
export class UVaccinationsComponent implements OnInit {
  vaccinations: Vaccinations[] = [];

  constructor(private vaccinationsService: VaccinationsService) {}

  ngOnInit(): void {
    try{
    this.getVaccinations();
    }
    catch(err)
     { 
      
     }
  }

  getVaccinations(): void {
    this.vaccinationsService.getAllVaccinations().subscribe({
      next: (data) => {
        console.log(data);
        this.vaccinations = data;
      },
      error: (err) => {
        
        console.error('Error fetching vaccinations:', err);
      }
    });
  }
  imageDetail:ImageDetails[]=[{
    imageUrl:"assets/vaccine/v1.jpg",
    imageName:"Distemper",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/vaccine/v2.jpg",
    imageName:"Parvovirus",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/vaccine/v3.jpg",
    imageName:"Hepatitis",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/vaccine/v4.jpg",
    imageName:"Leptospirosis",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/vaccine/v5.jpg",
    imageName:"Bortadella",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/vaccine/v6.jpg",
    imageName:"Lyme Disease",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/vaccine/v7.jpg",
    imageName:"Canine influenza",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/vaccine/v9.jpg",
    imageName:"Feline Leukemia",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/vaccine/v1.jpg",
    imageName:"Feline Immunedeficiency virus",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/vaccine/v9.jpg",
    imageName:"Leptospirosis",
    width:"300",
    height:"300"
  }

]
}