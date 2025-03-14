import { Component, OnInit } from '@angular/core';
import { PetCategoryService } from '../../services/petcategoryservice';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PetCategory } from '../../models/PetCategory';

@Component({
  selector: 'app-pet-category',
  imports: [CommonModule, FormsModule],
  templateUrl: './petcategories.component.html',
  styleUrls: ['./petcategories.component.css']
})
export class PetCategoryComponent implements OnInit {
  categories: PetCategory[] = [];
  newCategory: PetCategory = {} as PetCategory;
  showAddCategoryForm: boolean = false;
  editCategoryIndex: number | null = null;
  showEditForm: boolean = false;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';

  constructor(private petCategoryService: PetCategoryService) {}

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(): void {
    switch (this.filterType) {
      case 'getAll':
        this.petCategoryService.getAllCategories().subscribe(
          (data) => {
            this.categories = data;
          },
          (error) => {
            console.error('Error fetching categories:', error);
            this.errorMessage = 'Failed to load categories. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.petCategoryService.getCategoryById(Number(this.filterValue)).subscribe(
          (data) => {
            this.categories = [data];
          },
          (error) => {
            console.error('Error fetching category by ID:', error);
            this.errorMessage = 'Failed to load category. Please try again later.';
          }
        );
        break;
      case 'getByName':
        this.petCategoryService.getCategoryByName(this.filterValue).subscribe(
          (data) => {
            this.categories = data;
          },
          (error) => {
            console.error('Error fetching categories by name:', error);
            this.errorMessage = 'Failed to load categories. Please try again later.';
          }
        );
        break;
    }
  }

  addCategory(): void {
    this.petCategoryService.addCategory(this.newCategory).subscribe(
      (data) => {
        console.log('Category added successfully', data);
        this.newCategory = {} as PetCategory;
        this.showAddCategoryForm = false;
        this.getCategories();
      },
      (error) => console.error('Error adding category:', error)
    );
  }

  toggleAddCategoryForm(): void {
    this.showAddCategoryForm = !this.showAddCategoryForm;
  }

  editCategory(index: number): void {
    this.editCategoryIndex = index;
  }

  saveCategory(index: number): void {
    const updateCategory = this.categories[index];
    this.petCategoryService.updateCategory(updateCategory.categoryId, updateCategory).subscribe(
      () => {
        this.editCategoryIndex = null;
      },
      (error) => console.error('Error updating category:', error)
    );
  }

  cancelEdit(): void {
    this.editCategoryIndex = null;
  }
}
