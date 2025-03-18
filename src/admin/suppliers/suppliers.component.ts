import { Component, OnInit } from '@angular/core';
import { SupplierService } from '../../services/supplierservices';
import { AddressesService } from '../../services/addressservice';
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
  addresses: Addresses[] = [];
  newSupplier: Supplier = {} as Supplier;
  newAddress: Addresses = {} as Addresses;
  showAddSupplierForm: boolean = false;
  showAddressModal: boolean = false;
  editSupplierIndex: number | null = null;
  showEditForm: boolean = false;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';
 
  constructor(private suppliersService: SupplierService, private addressesService: AddressesService) {}
 
  ngOnInit(): void {
    this.getSuppliers();
    this.getAddresses();
  }
 
  getSuppliers(): void {
    switch (this.filterType) {
      case 'getAll':
        this.suppliersService.getAllSuppliers().subscribe(
          (data) => {
            this.suppliers = data;
          },
          (error) => {
            console.error('Error fetching suppliers:', error);
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
          }
        );
        break;
    }
  }
 
  getAddresses(): void {
    this.addressesService.getAllAddresses().subscribe(
      (data) => {
        this.addresses = data;
      },
      (error) => {
        console.error('Error fetching addresses:', error);
      }
    );
  }
 
  showAddressList(): void {
    this.showAddressModal = true;
  }
 
  closeAddressList(): void {
    this.showAddressModal = false;
  }
 
  selectAddress(addressId: number): void {
    this.newAddress.addressId = addressId;
    this.closeAddressList();
  }
 
  addSupplier(): void {
    this.newSupplier.address = this.newAddress;
    this.suppliersService.addSupplier(this.newSupplier).subscribe(
      (data) => {
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
 
  editSupplier(index: number): void {
    this.editSupplierIndex = index;
  }
 
  saveSupplier(index: number): void {
    const updateSupplier = this.suppliers[index];
    this.suppliersService.updateSupplier(updateSupplier.supplierId, updateSupplier).subscribe(
      () => {
        this.editSupplierIndex = null;
      },
      (error) => console.error('Error updating supplier:', error)
    );
  }
 
  cancelEdit(): void {
    this.editSupplierIndex = null;
  }
}
 