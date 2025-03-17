import { Component, OnInit } from '@angular/core';
import { EmployeesService } from '../../../services/employeeservices';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Employees } from '../../../models/Employees';
import { Addresses } from '../../../models/addresses';

@Component({
  selector: 'app-employees',
  imports: [CommonModule, FormsModule],
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeesComponent implements OnInit {
  employees: Employees[] = [];
  newEmployee: Employees = {} as Employees;
  newAddress: Addresses = {} as Addresses;
  showAddEmployeeForm: boolean = false;
  editEmployeeIndex: number | null = null;
  showEditForm: boolean = false;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';

  constructor(private employeesService: EmployeesService) {}

  ngOnInit(): void {
    this.getEmployees();
  }

  // Fetch employees based on filter type
  getEmployees(): void {
    switch (this.filterType) {
      case 'getAll':
        this.employeesService.getAllEmployees().subscribe(
          (data) => {
            this.employees = data.map(employee => ({
              ...employee,
              address: employee.address || {} as Addresses
            }));
            console.log(this.employees);
          },
          (error) => {
            console.error('Error fetching employees:', error);
            //this.errorMessage = 'Failed to load employees. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.employeesService.getEmployeeById(Number(this.filterValue)).subscribe(
          (data) => {
            this.employees = [{
              ...data,
              address: data.address || {} as Addresses
            }];
          },
          (error) => {
            console.error('Error fetching employee by ID:', error);
            //this.errorMessage = 'Failed to load employee. Please try again later.';
          }
        );
        break;
      case 'getByFirstName':
        this.employeesService.getEmployeesByFirstName(this.filterValue).subscribe(
          (data) => {
            this.employees = data.map(employee => ({
              ...employee,
              address: employee.address || {} as Addresses
            }));
          },
          (error) => {
            console.error('Error fetching employees by first name:', error);
            //this.errorMessage = 'Failed to load employees. Please try again later.';
          }
        );
        break;
      case 'getByPosition':
        this.employeesService.getEmployeesByPosition(this.filterValue).subscribe(
          (data) => {
            this.employees = data.map(employee => ({
              ...employee,
              address: employee.address || {} as Addresses
            }));
          },
          (error) => {
            console.error('Error fetching employees by position:', error);
           // this.errorMessage = 'Failed to load employees. Please try again later.';
          }
        );
        break;
    }
  }

  // Add a new employee
  addEmployee(): void {
    this.newEmployee.address = this.newAddress;
    console.log(this.newEmployee);
    this.employeesService.addEmployee(this.newEmployee).subscribe(
      (data) => {
        console.log('Employee added successfully', data);
        this.newEmployee = {} as Employees;
        this.newAddress = {} as Addresses;
        this.showAddEmployeeForm = false;
        this.getEmployees();
      },
      (error) => console.error('Error adding employee:', error)
    );
  }

  toggleAddEmployeeForm(): void {
    this.showAddEmployeeForm = !this.showAddEmployeeForm;
  }

  // Enable edit mode for a specific employee
  editEmployee(index: number): void {
    this.editEmployeeIndex = index;
  }

  // Save changes to employee details (excluding address)
  saveEmployee(index: number): void {
    const updateEmployee = this.employees[index];
    this.employeesService.updateEmployee(updateEmployee.employeeId, updateEmployee).subscribe(
      () => {
        this.editEmployeeIndex = null;
      },
      (error) => console.error('Error updating employee:', error)
    );
  }

  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editEmployeeIndex = null;
  }
}