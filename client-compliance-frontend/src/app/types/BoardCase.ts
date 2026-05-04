import { Check } from "./Check";
import { Note } from "./Note";

export interface BoardCase {
  id: string;
  clientId: string;
  stage: string;
  checks: Check[];
  notes: Note[];
}