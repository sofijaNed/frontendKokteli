import { Injectable } from '@angular/core';
import { Cocktail } from 'src/app/shared/models/Cocktail';
import { Tag } from 'src/app/shared/models/Tag';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { Observable, forkJoin, map } from 'rxjs';
import { RecipeItem } from 'src/app/shared/models/RecipeItem';

@Injectable({
  providedIn: 'root'
})
export class CocktailsService extends HttpClient{

  //constructor(){};
  readonly API = "http://localhost:8080";
  allCocktails: Cocktail[] = [];
  svi: number = 0;
  sladak: number = 0;
  suv: number = 0;
  polusladak: number = 0;
  constructor(handler: HttpHandler) {
    super(handler);
  }

  public findAll(): Observable<Array<Cocktail>> {
    return this.get<Array<Cocktail>>(`${this.API}/cocktails`);
  }

  public findById(id: string): Observable<Cocktail> {
    return this.get<Cocktail>(`${this.API}/cocktails/${id}`);
  }

  public save(cocktail: Cocktail): Observable<Cocktail> {
    return this.post<Cocktail>(`${this.API}/cocktails`, cocktail);
  }

  public getRecipeItems(id: string): Observable<Array<RecipeItem>> {
    return this.get<Array<RecipeItem>>(`${this.API}/cocktails/${id}/recipeItems`);
  }

  // getCocktailByCocktailName(cocktailName:String) : Cocktail{

    
  //   return this.getAll().find(cocktail => cocktail.cocktailName == cocktailName)!;

  // }
  getAllTags(): Observable<Tag[]> {
    return forkJoin([
      this.findAll(),
      this.getAllCocktailsByTag("SLADAK"),
      this.getAllCocktailsByTag("SUV"),
      this.getAllCocktailsByTag("POLUSLADAK")
    ]).pipe(
      map(([allCocktails, sladakCocktails, suvCocktails, polusladakCocktails]) => {
        this.svi = allCocktails.length;
        this.sladak = sladakCocktails.length;
        this.suv = suvCocktails.length;
        this.polusladak = polusladakCocktails.length;
  
        return [
          { name: 'SVI', count: this.svi},
          { name: 'SUV', count: this.suv },
          { name: 'POLUSLADAK', count: this.polusladak },
          { name: 'SLADAK', count: this.sladak },
        ];
      })
    );
  }

  // getAllCocktailsBySearchTerm(searchTerm:string) :Cocktail[]{
    
  //   return  this.findAll().filter(cocktail =>
  //     cocktail.cocktailName.toLowerCase().includes(searchTerm.toLowerCase()));
  // }

  getAllCocktailsBySearchTerm(searchTerm: string): Observable<Cocktail[]> {
    
    return this.get<Cocktail[]>(`${this.API}/cocktails`).pipe(
      map(cocktails => 
        cocktails.filter(cocktail =>
          cocktail.cocktailName.toLowerCase().includes(searchTerm.toLowerCase()))
      )
    );
  }


  getAllCocktailsByTag(tag: string): Observable<Array<Cocktail>>{
    if (tag == "SVI") return this.findAll();
    else return this.get<Array<Cocktail>>(`${this.API}/cocktails`).pipe(
        map(cocktails => 
          //cocktails.filter(cocktail => cocktail.type?.includes(tag))));
          cocktails.filter(cocktail =>{
            let regex = new RegExp(`\\b${tag}\\b`, "i"); 
            return regex.test(cocktail.type || "");
        })));
    }
  
}
