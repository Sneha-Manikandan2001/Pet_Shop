
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PetService } from '../../uservices/pet.service';
import { TransactionService } from '../../uservices/transaction.service';
import { FoodRelationship, GroomingRelationship, Pet, SupplierRelationship, TransactionStatus, Transactions, VaccinationRelationship, Customers } from '../../models/pets';
import { CustomersService } from '../../uservices/customers.service'; // Import CustomersService

@Component({
  selector: 'app-pets',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css']
})
export class UPetsComponent implements OnInit {
  pets: Pet[] = [];
  petForm: FormGroup;
  selectedFile: File | null = null;
  base64Image: string | null = null;
  showAddPetForm: boolean = false;
  editPetIndex: number | null = null;
  errorMessage: string | null = null;
  groomingServices: GroomingRelationship[] = [];
  vaccinations: VaccinationRelationship[] = [];
  foodInfo: FoodRelationship[] = [];
  suppliers: SupplierRelationship[] = [];
  transactionHistory: Transactions[] = [];
  showOrderModal: boolean = false;
  selectedPet: Pet | null = null;
  customerId: number | null = null; // Add this line
  orderReceived: boolean = false; // Add this line
  orderCancelled: boolean = false; // Add this line

  constructor(
    private fb: FormBuilder,
    private petService: PetService,
    private transactionService: TransactionService,
    private customersService: CustomersService // Inject CustomersService
  ) {
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
    this.getCustomerIdFromLocalStorage();
    this.getPets();
  }

  // Retrieve customer ID from local storage
  getCustomerIdFromLocalStorage(): void {
    const username = localStorage.getItem('username');
    if (username) {
      const [firstName, lastName] = username.split(' ');
      this.customersService.getCustomerByName(firstName, lastName).subscribe(
        (customer) => {
          this.customerId = customer.customerId;
          console.log('Customer ID:', this.customerId); // Debugging line
        },
        (error) => {
          console.error('Error fetching customer ID:', error);
          this.errorMessage = 'Failed to load customer information. Please try again later.';
        }
      );
    }
  }
  // Fetch all pets and their additional details
  getPets(): void {
    this.petService.getAllPets().subscribe(
      (data) => {
        this.pets = data;
        this.pets.forEach(pet => {
          if (pet.image) {
            console.log('Base64 Image URL:', 'data:image/jpg;base64,' + pet.image); // Debugging line
          }
          this.fetchAdditionalDetails(pet);
        });
      },
      (error) => {
        console.error('Error fetching pets:', error);
        this.errorMessage = 'Failed to load pets. Please try again later.';
      }
    );
  }

  // Fetch additional details for a pet
  fetchAdditionalDetails(pet: Pet): void {
    if (pet.petId !== undefined) {
      this.petService.getGroomingServicesByPetId(pet.petId).subscribe(
        (data) => {
          pet.groomingRelationships = data;
        },
        (error) => console.error('Error fetching grooming services:', error)
      );

      this.petService.getVaccinationsByPetId(pet.petId).subscribe(
        (data) => {
          pet.vaccinationRelationships = data;
        },
        (error) => console.error('Error fetching vaccinations:', error)
      );

      this.petService.getFoodInfoByPetId(pet.petId).subscribe(
        (data) => {
          pet.foodRelationships = data;
        },
        (error) => console.error('Error fetching food info:', error)
      );
    }
  }

  // Open order modal
  openOrderModal(pet: Pet): void {
    this.selectedPet = pet;
    this.showOrderModal = true;
    console.log('Order modal opened for pet:', pet); // Debugging line
  }

  payForPet(): void {
    if (this.selectedPet && this.customerId !== null) {
      const transaction: Transactions = {
        customer: { customerId: this.customerId } as Customers, // Use the retrieved customerId
        pet: this.selectedPet,
        transactionDate: new Date(),
        amount: this.selectedPet.price,
        transactionStatus: TransactionStatus.Success
      };

      console.log('Sending transaction:', transaction); // Debugging line

      this.transactionService.addTransaction(transaction).subscribe(
        (data) => {
          console.log('Transaction successful', data);
          this.showOrderModal = false;
          this.orderReceived = true;
          this.selectedPet = null;
          this.getPets(); // Refresh pets to update transaction history
        },
        (error) => {
          console.error('Error processing transaction:', error);
          this.errorMessage = 'Failed to process transaction. Please try again later.';
        }
      );
    } else {
      console.error('Selected pet or customer ID is undefined');
    }
  }

  // Cancel order
  cancelOrder(): void {
    if (this.selectedPet && this.customerId !== null) {
      const transaction: Transactions = {
        customer: { customerId: this.customerId } as Customers, // Use the retrieved customerId
        pet: this.selectedPet,
        transactionDate: new Date(),
        amount: this.selectedPet.price,
        transactionStatus: TransactionStatus.Failed
      };

      console.log('Sending transaction:', transaction); // Debugging line

      this.transactionService.addTransaction(transaction).subscribe(
        (data) => {
          console.log('Transaction cancelled', data);
          this.showOrderModal = false;
          this.orderCancelled = true;
          this.selectedPet = null;
          this.getPets(); // Refresh pets to update transaction history
        },
        (error) => {
          console.error('Error processing transaction:', error);
          this.errorMessage = 'Failed to process transaction. Please try again later.';
        }
      );
    } else {
      console.error('Selected pet or customer ID is undefined');
    }
  }
   // Method to close pop-ups
   closePopUp(): void {
    this.orderReceived = false;
    this.orderCancelled = false;
  }
}
