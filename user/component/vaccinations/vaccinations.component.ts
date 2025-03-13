import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Vaccinations } from '../../../models/vaccinations';
import { VaccinationsService } from '../../services/vaccinations.service';

@Component({
  selector: 'app-vaccinations',
  imports: [CommonModule],
  templateUrl: './vaccinations.component.html',
  styleUrls: ['./vaccinations.component.css']
})
export class VaccinationsComponent implements OnInit {
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