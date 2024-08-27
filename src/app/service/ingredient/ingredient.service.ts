import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ingredient } from 'src/app/shared/models/Ingredient';
import { RecipeItem } from 'src/app/shared/models/RecipeItem';
@Injectable({
  providedIn: 'root'
})
export class IngredientService extends HttpClient{

  readonly API = "http://localhost:8080"
  constructor(handler: HttpHandler) {
    super(handler);
  }

  public findAll(): Observable<Array<Ingredient>> {
    return this.get<Array<Ingredient>>(`${this.API}/ingredients`);
  }

  public findById(id: number): Observable<Ingredient> {
    return this.get<Ingredient>(`${this.API}/ingredients/${id}`);
  }

  public save(ingredient: Ingredient): Observable<Ingredient> {
    return this.post<Ingredient>(`${this.API}/ingredients`, ingredient);
  }

  public getRecipeItems(id: number): Observable<Array<RecipeItem>> {
    return this.get<Array<RecipeItem>>(`${this.API}/ingredients/${id}/recipeItems`);
  }
}
