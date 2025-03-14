import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Vaccinations } from '../../../models/Vaccinations';
import { VaccinationsService } from '../../uservices/vaccinations.service';

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
    this.getVaccinations();
  }

  getVaccinations(): void {
    this.vaccinationsService.getAllVaccinations().subscribe({
      next: (data) => {
        this.vaccinations = data;
      },
      error: (err) => {
        console.error('Error fetching vaccinations:', err);
      }
    });
  }
}