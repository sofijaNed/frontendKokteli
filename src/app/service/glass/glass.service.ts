import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Glass } from 'src/app/shared/models/Glass';

@Injectable({
  providedIn: 'root'
})
export class GlassService extends HttpClient{

  readonly API = "http://localhost:8080"
  constructor(handler: HttpHandler) {
    super(handler);
  }

  public findAll(): Observable<Array<Glass>> {
    return this.get<Array<Glass>>(`${this.API}/glasses`);
  }

  public findById(id: number): Observable<Glass> {
    return this.get<Glass>(`${this.API}/glasses/${id}`);
  }

  public save(glass: Glass): Observable<Glass> {
    return this.post<Glass>(`${this.API}/glasses`, glass);
  }
}
