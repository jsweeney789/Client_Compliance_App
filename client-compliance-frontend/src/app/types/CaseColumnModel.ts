import { BoardCase } from "./BoardCase";
export interface CaseColumnModel {
    id: string;
    name: string;
    stage: BoardCase['stage'];
    cases: BoardCase[];
}