import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UnitOfMeasure } from 'src/app/shared/models/UnitOfMeasure';

@Injectable({
  providedIn: 'root'
})
export class UnitOfMeasureService extends HttpClient{

  readonly API = "http://localhost:8080"
  constructor(handler: HttpHandler) {
    super(handler);
  }

  public findAll(): Observable<Array<UnitOfMeasure>> {
    return this.get<Array<UnitOfMeasure>>(`${this.API}/measures`);
  }

  public findById(id: number): Observable<UnitOfMeasure> {
    return this.get<UnitOfMeasure>(`${this.API}/measures/${id}`);
  }

  public save(measure: UnitOfMeasure): Observable<UnitOfMeasure> {
    return this.post<UnitOfMeasure>(`${this.API}/measures`, measure);
  }
}
