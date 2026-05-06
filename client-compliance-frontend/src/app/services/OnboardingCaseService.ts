import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BoardCase } from "../types/BoardCase";
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OnboardingCaseService {
    private readonly URL = `http://localhost:8080/api/cases`
    constructor (
        private http: HttpClient 
    ) {}

    addCase(onboardingCase: BoardCase): Observable<BoardCase> {
        return this.http
            .post<BoardCase>(this.URL, onboardingCase, {withCredentials: true})
            .pipe(
                catchError(() => 
                    throwError(() => new Error('Failed to add case.')),
            ),
        );
    }

    getCases(): Observable<BoardCase[]> {
        return this.http
            .get<BoardCase[]>(this.URL, {withCredentials: true})
            .pipe(catchError(() => throwError(() => new Error('Failed to retrieve cases.'))))
    }

    getCase(id: string): Observable<BoardCase> {
        return this.http
            .get<BoardCase>(`${this.URL}/${id}`, {withCredentials: true})
            .pipe(catchError(() => throwError(() => new Error('Failed to retrieve case.'))))
    }

    updateCase(onboardingCase: BoardCase): Observable<BoardCase> {
        return this.http
            .put<BoardCase>(`${this.URL}/${onboardingCase.id}`, onboardingCase, {withCredentials: true})
            .pipe(catchError(() => throwError(() => new Error('Failed to update case.'))))
    }

    deleteCase(id: string): Observable<void> {
        return this.http
            .delete<void>(`${this.URL}/${id}`, {withCredentials: true})
            .pipe(catchError(() => throwError(() => new Error('Failed to delete case.'))))
    }
}