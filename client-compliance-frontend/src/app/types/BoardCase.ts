import { Check } from "./Check";
import { Note } from "./Note";

export interface BoardCase {
  id: string;
  clientId: string;
  stage: 'INITIATED' | 'UNDER_REVIEW' | 'KYC_IN_PROGRESS' | 'APPROVED' | 'REJECTED';
  checks: Check[];
  notes: Note[];
}