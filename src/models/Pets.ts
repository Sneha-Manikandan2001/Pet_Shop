//import { Employees } from "./Employees";
import { GroomingServices } from "./GroomingServices";
import { PetCategory } from "./PetCategory";
import { PetFood } from "./PetFood";
import { Supplier } from "./Supplier";
import { Transactions } from "./Transaction";
import { Vaccinations } from "./Vaccinations";

export interface Pet {
  petId?: number;
  name: string;
  breed: string;
  age: number;
  price: number;
  description: string;
  image: any; // Updated to 'any' to handle byte array
  imagetobase64?:string;
  category: PetCategory;
  foodRelationships: FoodRelationship[];
  groomingRelationships: GroomingRelationship[];
  supplierRelationships: SupplierRelationship[];
  vaccinationRelationships: VaccinationRelationship[];
  transaction: Transactions;
}

export interface FoodRelationship {
  id: number;
  pet: Pet;
  food: PetFood;
}
   
export interface GroomingRelationship {
  id: number;
  pet: Pet;
  groomingService: GroomingServices;
}
   
export interface SupplierRelationship {
  id: number;
  pet: Pet;
  supplier: Supplier;
}
export interface VaccinationRelationship {
  id: number;
  pet: Pet;
  vaccination: Vaccinations;
}