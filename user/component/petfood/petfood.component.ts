import { Component } from '@angular/core';
import { PetFood } from '../../../models/petfood';
import { PetFoodService } from '../../services/petfood.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ImageDetails } from '../../../models/imagedetails';

@Component({
  selector: 'app-petfood',
  imports: [CommonModule, FormsModule],
  templateUrl: './petfood.component.html',
  styleUrl: './petfood.component.css'
})
export class PetfoodComponent {
  petFoods: PetFood[] = [];
  filteredPetFoods: PetFood[] = [];
  searchTerm: string = '';
  selectedCategory: string = 'name';

  constructor(private petFoodService: PetFoodService, private router: Router) {}

  ngOnInit(): void {
    this.getPetFoods();
  }

  getPetFoods(): void {
    this.petFoodService.getAllPetFoods().subscribe({
      next: (data) => {
        this.petFoods = data;
        this.filteredPetFoods = data;
      },
      error: (err) => {
        console.error('Error fetching pet foods:', err);
      }
    });
  }

  onSearch(): void {
    if (this.searchTerm.trim()) {
      switch (this.selectedCategory) {
        case 'name':
          this.searchByName();
          break;
        case 'brand':
          this.searchByBrand();
          break;
        case 'type':
          this.searchByType();
          break;
        default:
          this.filteredPetFoods = [...this.petFoods];
      }
    } else {
      this.filteredPetFoods = [...this.petFoods];
    }
  }

  searchByName(): void {
    this.petFoodService.searchByName(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        this.filteredPetFoods = data;
      },
      (error: any) => {
        console.error('Error searching by name:', error);
        this.filteredPetFoods = [];
      }
    );
  }
  searchByBrand(): void {
    this.petFoodService.searchByBrand(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        this.filteredPetFoods = data;
      },
      (error: any) => {
        console.error('Error searching by brand:', error);
        this.filteredPetFoods = [];
      }
    );
  }
  searchByType(): void {
    this.petFoodService.searchByType(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        this.filteredPetFoods = data;
      },
      (error: any) => {
        console.error('Error searching by type:', error);
        this.filteredPetFoods = [];
      }
    );
  }
  imageDetail:ImageDetails[]=[{
    imageUrl:"assets/i1.jpg",
    imageName:"Premium Cat Food",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/i2.jpg",
    imageName:"Healthy Dog Bites",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/i3.jpg",
    imageName:"Organic Rabbit Pellets",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/i4.jpg",
    imageName:"Natural Fish Treats",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/i5.jpg",
    imageName:"Grain-Free Puppy Chow",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/i6.jpg",
    imageName:"Fresh Chicken Delight",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/i7.jpg",
    imageName:"Shell Care",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/i8.jpg",
    imageName:"Tweet Treats",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/i9.jpg",
    imageName:"Hoof & Horn",
    width:"300",
    height:"300"
  },
  {
    imageUrl:"assets/i10.jpg",
    imageName:"Soft Lamb Bites",
    width:"300",
    height:"300"
  }

]
}


