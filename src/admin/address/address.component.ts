import { Component, OnInit } from '@angular/core';
import { AddressesService } from '../../services/addressservice';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Addresses } from '../../models/addresses';
 
@Component({
  selector: 'app-addresses',
  imports: [CommonModule, FormsModule],
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressesComponent implements OnInit {
  
  addresses: Addresses[] = [];
  newAddress: Addresses = {} as Addresses;
  showAddAddressForm: boolean = false;
  editAddressIndex: number | null = null;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';
 
  constructor(private addressesService: AddressesService) {}
 
  ngOnInit(): void {
    this.getAddresses();
  }
 
  // Fetch addresses based on filter type
  getAddresses(): void {
    switch (this.filterType) {
      case 'getAll':
        this.addressesService.getAllAddresses().subscribe(
          (data) => {
            this.addresses = data;
          },
          (error) => {
            console.error('Error fetching addresses:', error);
           // this.errorMessage = 'Failed to load addresses. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.addressesService.getAddressById(Number(this.filterValue)).subscribe(
          (data) => {
            this.addresses = [data];
          },
          (error) => {
            console.error('Error fetching address by ID:', error);
           // this.errorMessage = 'Failed to load address. Please try again later.';
          }
        );
        break;
      case 'getByCity':
        this.addressesService.getAddressesByCity(this.filterValue).subscribe(
          (data) => {
            this.addresses = data;
          },
          (error) => {
            console.error('Error fetching addresses by city:', error);
           // this.errorMessage = 'Failed to load addresses. Please try again later.';
          }
        );
        break;
      case 'getByState':
        this.addressesService.getAddressesByState(this.filterValue).subscribe(
          (data) => {
            this.addresses = data;
          },
          (error) => {
            console.error('Error fetching addresses by state:', error);
            //this.errorMessage = 'Failed to load addresses. Please try again later.';
          }
        );
        break;
    }
  }
 
  // Add a new address
  addAddress(): void {
    this.addressesService.addAddress(this.newAddress).subscribe(
      (data) => {
        console.log('Address added successfully', data);
        this.newAddress = {} as Addresses;
        this.showAddAddressForm = false;
        this.getAddresses();
      },
      (error) => console.error('Error adding address:', error)
    );
  }
 
  toggleAddAddressForm(): void {
    this.showAddAddressForm = !this.showAddAddressForm;
  }
 
  // Enable edit mode for a specific address
  editAddress(index: number): void {
    this.editAddressIndex = index;
  }
 
  // Save changes to address details
  // saveAddress(index: number): void {
  //   const updateAddress = this.addresses[index];
  //   this.addressesService.updateAddress(index, updateAddress).subscribe(
  //     () => {
  //       this.editAddressIndex = null;
  //     },
  //     (error) => console.error('Error updating address:', error)
  //   );
  // }
  saveAddress(index: number): void {
    const updateAddress = this.addresses[index];
    console.log(updateAddress); // Log the payload
    this.addressesService.updateAddress(updateAddress.addressId, updateAddress).subscribe(
      () => {
        this.editAddressIndex = null;
      },
      (error) => {
        console.error('Error updating address:', error);
        //this.errorMessage = `Failed to update address: ${error.message}`;
      }
    );
  }
 
  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editAddressIndex = null;
  }
}
 