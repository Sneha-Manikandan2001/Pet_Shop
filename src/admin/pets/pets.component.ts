import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PetService } from '../../services/petservice';
import { FoodRelationship, GroomingRelationship, Pet, SupplierRelationship,VaccinationRelationship } from '../../models/Pets';
import { TransactionStatus } from '../../models/TransactionStatus';
import { Transactions } from '../../models/Transaction';
@Component({
  selector: 'app-pets',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css']
})
export class PetsComponent implements OnInit {
  pets: Pet[] = [];
  petForm: FormGroup;
  selectedFile: File | null = null;
  base64Image: string | null = null;
  showAddPetForm: boolean = false;
  editPetIndex: number | null = null;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';
  groomingServices: GroomingRelationship[] = [];
  vaccinations: VaccinationRelationship[] = [];
  foodInfo: FoodRelationship[] = [];
  suppliers: SupplierRelationship[] = [];
  transactionHistory: Transactions[] = [];
  constructor(private fb: FormBuilder, private petService: PetService) {
    this.petForm = this.fb.group({
      name: ['', Validators.required],
      breed: ['', Validators.required],
      age: [0, Validators.required],
      price: [0, Validators.required],
      description: ['', Validators.required],
      category: [0, Validators.required],
      image: [null]
     
    });
  }
 
  ngOnInit(): void {
    this.getPets();
  }
 
  // Fetch pets based on filter type
  getPets(): void {
    switch (this.filterType) {
      case 'getAll':
        this.petService.getAllPets().subscribe(
          (data) => {
            console.log('Raw Image Data:', data); // Debugging line
            this.pets = data;
            this.pets.forEach(pet => {
              if (pet.image) {
              //  pet.image = 'data:image/jpg;base64,' + pet.image;
                console.log('Base64 Image URL:', 'data:image/jpg;base64,'+pet.image); // Debugging line
              }
            });
          },
          (error) => {
            console.error('Error fetching pets:', error);
            //this.errorMessage = 'Failed to load pets. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.petService.getPetById(Number(this.filterValue)).subscribe(
          (data) => {
           // data.image = 'data:image/jpg;base64,' + data.image;
            console.log('Base64 Image URL:', data.image); // Debugging line
            this.pets = [data];
          },
          (error) => {
            console.error('Error fetching pet by ID:', error);
            //this.errorMessage = 'Failed to load pet. Please try again later.';
          }
        );
        break;
      case 'getByCategory':
        this.petService.getPetsByCategory(this.filterValue).subscribe(
          (data) => {
            this.pets = data;
            // this.pets.forEach(pet => {
            //   if (pet.image) {
            //     pet.image = 'data:image/jpg;base64,' + pet.image;
            //     console.log('Base64 Image URL:', pet.image); // Debugging line
            //   }
            // });
          },
          (error) => {
            console.error('Error fetching pets by category:', error);
           // this.errorMessage = 'Failed to load pets. Please try again later.';
          }
        );
        break;
    }
  }
 
  // Add a new pet
  addPet(): void {
    if (this.selectedFile) {
      const formData = new FormData();
      const petData: Pet = {
        name: this.petForm.get('name')?.value,
        breed: this.petForm.get('breed')?.value,
        age: this.petForm.get('age')?.value,
        price: this.petForm.get('price')?.value,
        description: this.petForm.get('description')?.value,
        image: this.base64Image,
        category: { categoryId:1, name: '' },
        foodRelationships: [],
        groomingRelationships: [],
        supplierRelationships: [],
        vaccinationRelationships: [],
        transaction: {
          transactionId: 0,
          customerId: 0,
          petId: 0,
          transactionDate: new Date(),
          amount: 0,
          transactionStatus: TransactionStatus.Success // or TransactionStatus.Failed
        }
       
      };
     
 
      this.petService.addPet(petData).subscribe(
        (data) => {
          console.log('Pet added successfully', data);
          this.petForm.reset();
          this.selectedFile = null;
          this.base64Image = null;
          this.showAddPetForm = false;
          this.getPets();
        },
        (error) => console.error('Error adding pet:', error)
      );
    } else {
      console.error('Image file is required');
    }
  }
 
  toggleAddPetForm(): void {
    this.showAddPetForm = !this.showAddPetForm;
  }
 
  // Enable edit mode for a specific pet
  editPet(index: number): void {
    this.editPetIndex = index;
  }
 
  // Save changes to pet details
  savePet(index: number): void {
    const updatePet = this.pets[index];
    if (updatePet.petId !== undefined) {
      this.petService.updatePet(updatePet.petId, updatePet).subscribe(
        () => {
          this.editPetIndex = null;
          this.getPets();
        },
        (error) => console.error('Error updating pet:', error)
      );
    } else {
      console.error('Pet ID is required for updating a pet');
    }
  }
 
  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editPetIndex = null;
  }
 
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const byteArray = new Uint8Array(e.target.result);
        this.base64Image = btoa(String.fromCharCode(...byteArray));
      };
      reader.readAsArrayBuffer(this.selectedFile);
    }
  }
 
   // Fetch grooming services by pet ID
   getGroomingServicesByPetId(petId: number | undefined): void {
    if (petId !== undefined) {
      this.petService.getGroomingServicesByPetId(petId).subscribe(
        (data) => {
          this.groomingServices = data;
        },
        (error) => console.error('Error fetching grooming services:', error)
      );
    }
  }
 
  // Fetch vaccinations by pet ID
  getVaccinationsByPetId(petId: number | undefined): void {
    if (petId !== undefined) {
      this.petService.getVaccinationsByPetId(petId).subscribe(
        (data) => {
          this.vaccinations = data;
        },
        (error) => console.error('Error fetching vaccinations:', error)
      );
    }
  }
 
  // Fetch food info by pet ID
  getFoodInfoByPetId(petId: number | undefined): void {
    if (petId !== undefined) {
      this.petService.getFoodInfoByPetId(petId).subscribe(
        (data) => {
          this.foodInfo = data;
        },
        (error) => console.error('Error fetching food info:', error)
      );
    }
  }
 
  // Fetch suppliers by pet ID
  getSuppliersByPetId(petId: number | undefined): void {
    if (petId !== undefined) {
      this.petService.getSuppliersByPetId(petId).subscribe(
        (data) => {
          this.suppliers = data;
        },
        (error) => console.error('Error fetching suppliers:', error)
      );
    }
  }
 
  // Fetch transaction history by pet ID
  getTransactionHistoryByPetId(petId: number | undefined): void {
    if (petId !== undefined) {
      this.petService.getTransactionHistoryByPetId(petId).subscribe(
        (data) => {
          this.transactionHistory = data;
        },
        (error) => console.error('Error fetching transaction history:', error)
      );
    }
  }
}
 
 
