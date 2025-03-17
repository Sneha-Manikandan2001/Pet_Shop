import { Routes } from '@angular/router';
import { UserLoginComponent } from '../AuthenticationModule/user-login/user-login.component';
import { UserRegisterComponent } from '../AuthenticationModule/user-register/user-register.component';
import { AdminLoginComponent } from '../AuthenticationModule/admin-login/admin-login.component';
import { EmployeesComponent } from '../admin/component/employee/employee.component';
import { GroomingServicesComponent } from '../admin/groomingservices/groomingservices.component';
import { VaccinationsComponent } from '../admin/vaccinations/vaccinations.component';
import { PetFoodComponent } from '../admin/petfood/petfood.component';
import { PetCategoryComponent } from '../admin/petcategories/petcategories.component';
import { AddressesComponent } from '../admin/address/address.component';
import { SupplierComponent } from '../admin/suppliers/suppliers.component';
import { TransactionComponent } from '../admin/transaction/transaction.component';
import { CustomersComponent } from '../admin/customer/customer.component';
import { PetsComponent } from '../admin/pets/pets.component';
import { CustomerRegisterComponent } from '../AuthenticationModule/customer-register/customer-register/customer-register.component';
import { UPetfoodComponent } from '../user/component/upetfood/petfood.component';
import { UGroomingServicesComponent } from '../user/component/ugroomingservices/groomingservices.component';
import { UVaccinationsComponent } from '../user/component/uvaccinations/vaccinations.component';
import { UserdashboardComponent } from '../userdashboard/userdashboard.component';
import { AdmindashboardComponent } from '../admindashboard/admindashboard.component';
import { UPetsComponent } from '../user/component/pets/pets.component';
 
export const routes: Routes = [
    {path: 'user-login', component: UserLoginComponent},
    {path: 'customer-register', component: CustomerRegisterComponent},
    {path: 'admin-login', component: AdminLoginComponent},    
    {path:'user-register',component:UserRegisterComponent},

    {path:'admin-dashboard',component:AdmindashboardComponent,children:[{path: 'employees',component:EmployeesComponent},
    {path: 'groomingservices',component:GroomingServicesComponent},
    {path: 'vaccinations',component:VaccinationsComponent},
    {path: 'petfood',component:PetFoodComponent},
    {path: 'categories',component:PetCategoryComponent},
    {path: 'addresses',component:AddressesComponent},
    {path: 'suppliers',component:SupplierComponent},
    {path: 'transactions',component:TransactionComponent},
    {path: 'customers', component:CustomersComponent},
    {path: 'pets',component:PetsComponent}]},
    

    {path:'',component:UserdashboardComponent,children:[
        {path: 'ugroomingservices',component:UGroomingServicesComponent},
        {path: 'upet-foods',component:UPetfoodComponent},
        {path: 'uvaccinations',component:UVaccinationsComponent},
        {path:'upets',component:UPetsComponent}
    ]}
    
    
];
 
 