import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RecipeItem, RecipeItemPK } from 'src/app/shared/models/RecipeItem';

@Injectable({
  providedIn: 'root'
})
export class RecipeItemService extends HttpClient{

  readonly API = "http://localhost:8080"
  constructor(handler: HttpHandler) {
    super(handler);
  }

  public findAll(): Observable<Array<RecipeItem>> {
    return this.get<Array<RecipeItem>>(`${this.API}/recipes`);
  }

  public findById(id: RecipeItemPK): Observable<RecipeItem> {
    return this.get<RecipeItem>(`${this.API}/recipes/${id}`);
  }

  public save(recipe: RecipeItem): Observable<RecipeItem> {
    return this.post<RecipeItem>(`${this.API}/recipes`, recipe);
  }
}
