import { Component, OnInit } from '@angular/core';
import { PetFoodService } from '../../services/petfoodservice';
import { CommonModule } from '@angular/common';
import { PetFood } from '../../models/PetFood';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-petfood',
  imports: [CommonModule,FormsModule],
  templateUrl: './petfood.component.html',
  styleUrl: './petfood.component.css'
})
export class PetFoodComponent implements OnInit {
  petFoods: PetFood[] = [];
  newPetFood: PetFood = {} as PetFood;
  showAddPetFoodForm: boolean = false;
  editPetFoodIndex: number | null = null;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';

  constructor(private petFoodService: PetFoodService) {}

  ngOnInit(): void {
    this.getPetFoods();
  }

  // Fetch pet foods based on filter type
  getPetFoods(): void {
    switch (this.filterType) {
      case 'getAll':
        this.petFoodService.getAllPetFoods().subscribe(
          (data) => {
            this.petFoods = data;
          },
          (error) => {
            console.error('Error fetching pet foods:', error);
            //this.errorMessage = 'Failed to load pet foods. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.petFoodService.getPetFoodById(Number(this.filterValue)).subscribe(
          (data) => {
            this.petFoods = [data];
          },
          (error) => {
            console.error('Error fetching pet food by ID:', error);
           // this.errorMessage = 'Failed to load pet food. Please try again later.';
          }
        );
        break;
      case 'getByName':
        this.petFoodService.searchPetFoodsByName(this.filterValue).subscribe(
          (data) => {
            this.petFoods = data;
          },
          (error) => {
            console.error('Error fetching pet foods by name:', error);
           // this.errorMessage = 'Failed to load pet foods. Please try again later.';
          }
        );
        break;
      case 'getByType':
        this.petFoodService.getPetFoodsByType(this.filterValue).subscribe(
          (data) => {
            this.petFoods = data;
          },
          (error) => {
            console.error('Error fetching pet foods by type:', error);
            //this.errorMessage = 'Failed to load pet foods. Please try again later.';
          }
        );
        break;
      case 'getByBrand':
        this.petFoodService.getPetFoodsByBrand(this.filterValue).subscribe(
          (data) => {
            this.petFoods = data;
          },
          (error) => {
            console.error('Error fetching pet foods by brand:', error);
            //this.errorMessage = 'Failed to load pet foods. Please try again later.';
          }
        );
        break;
    }
  }

  // Add a new pet food
  addPetFood(): void {
    this.petFoodService.addPetFood(this.newPetFood).subscribe(
      (data) => {
        console.log('Pet food added successfully', data);
        this.newPetFood = {} as PetFood;
        this.showAddPetFoodForm = false;
        this.getPetFoods();
      },
      (error) => console.error('Error adding pet food:', error)
    );
  }

  toggleAddPetFoodForm(): void {
    this.showAddPetFoodForm = !this.showAddPetFoodForm;
  }

  // Enable edit mode for a specific pet food
  editPetFood(index: number): void {
    this.editPetFoodIndex = index;
  }

  // Save changes to pet food details
  savePetFood(index: number): void {
    const updatePetFood = this.petFoods[index];
    this.petFoodService.updatePetFood(updatePetFood.foodId, updatePetFood).subscribe(
      () => {
        this.editPetFoodIndex = null;
        this.getPetFoods();
      },
      (error) => console.error('Error updating pet food:', error)
    );
  }

  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editPetFoodIndex = null;
  }
}
