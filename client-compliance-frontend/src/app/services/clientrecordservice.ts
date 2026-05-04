import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { ClientRecord } from '../types/ClientRecord';

@Injectable({
  providedIn: 'root',
})
export class Clientrecordservice {

  constructor(private http: HttpClient) {}

  private url = 'http://localhost:8080/api/clientrecord';

   getClientRecordOnboard(): Observable<ClientRecord[]> {
      return this.http
        .get<ClientRecord[]>('http://localhost:8080/api/clientrecord/clientOnboard',{ withCredentials: true })
        .pipe(
          catchError(() =>
            throwError(() => new Error('Failed to retrieve client records.')),
          ),
        );
    }
  
    getClientRecords(): Observable<ClientRecord[]> {
      return this.http
        .get<ClientRecord[]>(this.url,{ withCredentials: true } )
        .pipe(
          catchError(() =>
            throwError(() => new Error('Failed to retrieve client records.')),
          ),
        );
    }
  
    getClientRecord(id: string): Observable<ClientRecord> {
      return this.http
        .get<ClientRecord>(`${this.url}/${id}`,{ withCredentials: true })
        .pipe(
          catchError(() =>
            throwError(() => new Error('Failed to retrieve client record.')),
          ),
        );
    }
  
    addUser(client: ClientRecord): Observable<ClientRecord> {
      return this.http
        .post<ClientRecord>(this.url, client, { withCredentials: true })
        .pipe(
          catchError(() =>
            throwError(() => new Error('Failed to add client record.')),
          ),
        );
    }
  
    updateClientRecord(client: ClientRecord): Observable<ClientRecord> {
       console.log("Here id: "+ client.id)
      return this.http
       
        .put<ClientRecord>(`${this.url}/${client.id}`, client,{ withCredentials: true })
        .pipe(
          catchError(() =>
            throwError(() => new Error('Failed to update client record.')),
          ),
        );
    }
  
    deleteUser(id: string): Observable<void> {
      return this.http
        .delete<void>(`${this.url}/${id}`,{ withCredentials: true })
        .pipe(
          catchError(() =>
            throwError(() => new Error('Failed to delete client record.')),
          ),
        );
    }


}
