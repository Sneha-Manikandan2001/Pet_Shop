import { Component, OnInit } from '@angular/core';
import { SupplierService } from '../../services/supplierservices';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Supplier } from '../../models/Supplier';
import { Addresses } from '../../models/addresses';

@Component({
  selector: 'app-supplier',
  imports: [CommonModule, FormsModule],
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.css']
})
export class SupplierComponent implements OnInit {
  suppliers: Supplier[] = [];
  newSupplier: Supplier = {} as Supplier;
  newAddress: Addresses = {} as Addresses;
  showAddSupplierForm: boolean = false;
  editSupplierIndex: number | null = null; 
  showEditForm: boolean = false;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';

  constructor(private suppliersService: SupplierService) {}

  ngOnInit(): void {
    this.getSuppliers();
  }

  // Fetch suppliers based on filter type
  getSuppliers(): void {
    switch (this.filterType) {
      case 'getAll':
        this.suppliersService.getAllSuppliers().subscribe(
          (data) => {
            this.suppliers = data;
          console.log(this.suppliers)
          },
          (error) => {
            console.error('Error fetching suppliers:', error);
            this.errorMessage = 'Failed to load suppliers. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.suppliersService.getSupplierById(Number(this.filterValue)).subscribe(
          (data) => {
            this.suppliers = [data];
          },
          (error) => {
            console.error('Error fetching supplier by ID:', error);
            this.errorMessage = 'Failed to load supplier. Please try again later.';
          }
        );
        break;
      case 'getByName':
        this.suppliersService.getSuppliersByName(this.filterValue).subscribe(
          (data) => {
            this.suppliers = data;
          },
          (error) => {
            console.error('Error fetching suppliers by name:', error);
            this.errorMessage = 'Failed to load suppliers. Please try again later.';
          }
        );
        break;
      case 'getByCity':
        this.suppliersService.getSuppliersByCity(this.filterValue).subscribe(
          (data) => {
            this.suppliers = data;
          },
          (error) => {
            console.error('Error fetching suppliers by city:', error);
            this.errorMessage = 'Failed to load suppliers. Please try again later.';
          }
        );
        break;
      case 'getByState':
        this.suppliersService.getSuppliersByState(this.filterValue).subscribe(
          (data) => {
            this.suppliers = data;
          },
          (error) => {
            console.error('Error fetching suppliers by state:', error);
            this.errorMessage = 'Failed to load suppliers. Please try again later.';
          }
        );
        break;
    }
  }

  // Add a new supplier
  addSupplier(): void {
    this.newSupplier.address = this.newAddress;
    console.log(this.newSupplier)
    this.suppliersService.addSupplier(this.newSupplier).subscribe(
      (data) => {
        console.log('Supplier added successfully', data);
        this.newSupplier = {} as Supplier;
        this.newAddress = {} as Addresses;
        this.showAddSupplierForm = false;
        this.getSuppliers();
      },
      (error) => console.error('Error adding supplier:', error)
    );
  }

  toggleAddSupplierForm(): void {
    this.showAddSupplierForm = !this.showAddSupplierForm;
  }

  // Enable edit mode for a specific supplier
  editSupplier(index: number): void {
    this.editSupplierIndex = index;
  }

  // Save changes to supplier details (excluding address)
  saveSupplier(index: number): void {
    const updateSupplier = this.suppliers[index];
    this.suppliersService.updateSupplier(updateSupplier.supplierId, updateSupplier).subscribe(
      () => {
        this.editSupplierIndex = null;
      },
      (error) => console.error('Error updating supplier:', error)
    );
  }

  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editSupplierIndex = null;
  }
}


