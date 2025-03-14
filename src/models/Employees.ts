import { Addresses } from "./addresses";
import { RoleType } from './RoleType';

export interface Employees {
   
    employeeId: number;
    firstName: string;
    lastName: string;
    position: string;
    phoneNumber: string;
    email: string;
    hireDate: Date;
    address: Addresses;
     role: RoleType;
     addresses:{street:"",city:"",state:"",zipCode:""}
}  