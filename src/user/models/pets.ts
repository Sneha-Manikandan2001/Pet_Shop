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
   
   
  export interface PetCategory {
    categoryId: number;
    name: string;
  }
   
  export interface FoodRelationship {
    id: number;
    pet: Pet;
    food: PetFood;
  }
   
  export interface PetFood{
    foodId:number;
    name:String;
    brand:String;
    type:String;
    quantity:number;
    price:number;
  }
   
  export interface GroomingRelationship {
    id: number;
    pet: Pet;
    groomingService: GroomingServices;
  }
   
  export interface GroomingServices {
    serviceId?: number;
    name: string;
    description: string;
    price: number;
    available: boolean;
  }
   
  export interface SupplierRelationship {
    id: number;
    pet: Pet;
    supplier: Supplier;
  }
  
  
  export interface Supplier {
  supplierId: number;
  name: string;
  contactPerson: string;
  phoneNumber: string;
  email: string;
  address: Addresses;
  }
   
  export interface Addresses {
  addressId : number;
  street: string;
  city: string;
  state: string;
  zipCode: string;
  }
   
  export interface VaccinationRelationship {
    id: number;
    pet: Pet;
    vaccination: Vaccinations;
  }
   
  export interface Vaccinations{
    vaccinationId:number;
  name:String;
  description:String;
  price:number;
    available:boolean;
  }
   
  export enum TransactionStatus {
    Success = 'Success',
    Failed = 'Failed'
  }
   
   
  export interface Transactions {
    transactionId?: number;
    customer: {customerId:number};
    pet: Pet;
    transactionDate: Date;
    amount: number;
    transactionStatus: TransactionStatus;
  }
   
  export interface Customers {
    customerId: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    address: Addresses;
    transactions: Transactions[];
   
  }
  
  
  
  