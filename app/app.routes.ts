import { Routes } from '@angular/router';
import { UserLoginComponent } from '../AuthenticationModule/AuthenticationModule/user-login/user-login.component';
import { UserRegisterComponent } from '../AuthenticationModule/AuthenticationModule/user-register/user-register.component';
import { AdminLoginComponent } from '../AuthenticationModule/AuthenticationModule/admin-login/admin-login.component';
import { PetfoodComponent } from '../user/component/petfood/petfood.component';
import { VaccinationsComponent } from '../user/component/vaccinations/vaccinations.component';
import { GroomingServicesComponent } from '../user/component/groomingservices/groomingservices.component';
import { PetsComponent } from '../Admin/component/pets/pets.component';

export const routes: Routes = [
    {path:'user-login',component:UserLoginComponent},
    {path: 'user-register',component:UserRegisterComponent},
    {path:'admin-login',component:AdminLoginComponent},
    {path:'pet-foods',component:PetfoodComponent},
    {path:'vaccinations',component:VaccinationsComponent},
    {path: 'services',component:GroomingServicesComponent},
    {path: 'pets',component:PetsComponent}
];
