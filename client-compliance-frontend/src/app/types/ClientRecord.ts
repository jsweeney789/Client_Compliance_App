import { BoardCase } from "./BoardCase";

export interface ClientRecord {
  id: string;
  firstName: string;
  lastName: string;
  type: string;
  sector:  string;
  domicile: string;
  phoneNumber: string;
  email: string;
  boardcase?: BoardCase; 
}