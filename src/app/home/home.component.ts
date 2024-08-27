import { Component } from '@angular/core';
import { CocktailsService } from '../service/cocktails/cocktails.service';
import { Cocktail } from '../shared/models/Cocktail';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Glass } from '../shared/models/Glass';
import { Ingredient } from '../shared/models/Ingredient';
import { IngredientService } from '../service/ingredient/ingredient.service';
import { MatDialog } from '@angular/material/dialog';
import { AddCoctailDialogComponent } from '../add-cocktail-dialog/add-coctail-dialog.component';
import { switchMap } from 'rxjs';
import { RecipeItem } from '../shared/models/RecipeItem';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  cocktails: Cocktail[] = [];
  ingredients: Ingredient[] = [];
  selectedIngredients: string[] = [];
  filtratedByIngredients: Cocktail[] = [];
  allCocktails: Cocktail[] = [];
  //filtratedCocktails: Cocktail[] = [];
  //glass?: Glass;
  constructor(private cocktailsService: CocktailsService, 
    private ingredientService: IngredientService,
    private route: ActivatedRoute,
    private router: Router, 
    public dialog: MatDialog){
    
  };

 
  openAddCocktailDialog(): void {
    const dialogRef = this.dialog.open(AddCoctailDialogComponent, {
      // maxWidth : 'auto',
      // maxHeight: 'auto',
      width: "1000px",
      
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        result.recipeItems.forEach((recipeItem: any) => {
           recipeItem.cocktailDTO = null;
        });
        //this.spinnerVisible = true;
        this.cocktailsService.save(result).pipe(switchMap(() => this.popuniTabelu())).subscribe();
      }
    });
  }

  openCocktailDetails(cocktail: Cocktail): void {

    this.route.paramMap.subscribe(params => {
      const cocktailName = cocktail.cocktailName;
      this.router.navigate(['/cocktail', cocktailName]);
    });
  }

  async vratiSveSastojke(){
    this.ingredientService
    .findAll()
    .subscribe((ingredients: Array<Ingredient>) =>{
      console.log("Sastojci:", ingredients);
      this.ingredients = ingredients;
    })
  }
  async popuniTabelu() {
    console.log("Hejejejeje");
    
    this.cocktailsService
      .findAll()
      .subscribe((cocktails: Array<Cocktail>) => {
        console.log('Kokteli:', cocktails);

        this.cocktails = cocktails;
        this.allCocktails = cocktails;
        for (let i = 0; i < this.cocktails.length; i++) {  
          
            // this.cocktails[i].picture = atob(this.cocktails[i].picture!);
            this.cocktails[i].glassDTO = cocktails[i].glassDTO;
            // this.cocktails[i].glassDTO.picture = atob(this.cocktails[i].glassDTO.picture!);
          
          
        }
      });
  }

  async filtriranoKokteli(searchTerm: string){

    this.cocktailsService.getAllCocktailsBySearchTerm(searchTerm)
    .subscribe((cocktails: Array<Cocktail>) => {
      console.log('Kokteli:', cocktails);

      this.cocktails = cocktails;
      for (let i = 0; i < this.cocktails.length; i++) {  
        
          // this.cocktails[i].picture = atob(this.cocktails[i].picture!);
          this.cocktails[i].glassDTO = cocktails[i].glassDTO;
          // this.cocktails[i].glassDTO.picture = atob(this.cocktails[i].glassDTO.picture!);
        
        
      }
    })

    
  }

  async filtriranoPoTagu(tag:string){
    this.cocktailsService.getAllCocktailsByTag(tag).subscribe((cocktails: Array<Cocktail>) => {
      this.cocktails = cocktails;    
      for (let i = 0; i < this.cocktails.length; i++) {  
        // this.cocktails[i].picture=atob(this.cocktails[i].picture!);
        this.cocktails[i].glassDTO = cocktails[i].glassDTO;
        // this.cocktails[i].glassDTO.picture=atob(this.cocktails[i].glassDTO.picture!);
      }   
    })
  }
  ngOnInit(): void{

    this.vratiSveSastojke();
    console.log("Hejejejeje");
    

     this.route.params.subscribe(params => {
       if (params['searchTerm'])
          this.filtriranoKokteli(params['searchTerm']);
       else if (params['tag'])
        this.filtriranoPoTagu(params['tag']);
       else
         
         this.popuniTabelu();
     })
    
  }

  isSelected(ingredientName: string): boolean {
    return this.selectedIngredients.includes(ingredientName);
  }

  toggleIngredient(ingredientName: string) {
    if (this.isSelected(ingredientName)) {
      this.selectedIngredients = this.selectedIngredients.filter(name => name !== ingredientName);
    } else {
      this.selectedIngredients.push(ingredientName);
    }
  }

  // applyFilter() {
  //   let nasao = 0;
  //   const filteredCocktails = []; // Koristimo novi niz za filtrirane koktele
  
  //   for (let i = 0; i < this.cocktails.length; i++) {
  //     const cocktail = this.cocktails[i]; // Trenutni koktel
  
  //     // Provera da li postoji niz recipeItems i nije prazan
  //     if (cocktail.recipeItems && cocktail.recipeItems.length > 0) {
  //       for (let k = 0; k < cocktail.recipeItems.length; k++) {
  //         const recipeItem = cocktail.recipeItems[k]; // Trenutni stavka recepta
  
  //         // Provera da li postoji svojstvo ingredient
  //         if (recipeItem.ingredientDTO && recipeItem.ingredientDTO.name) {
  //           // Provera da li je sastojak izabran
  //           if (this.selectedIngredients.includes(recipeItem.ingredientDTO.name)) {
  //             filteredCocktails.push(cocktail);
  //             nasao = 1;
  //             break;
  //           }
  //         }
  //       }
  //     }
  
  //     if (nasao == 1) {
  //       nasao = 0;
  //       break;
  //     }
  //   }
  
  //   this.cocktails = filteredCocktails;
  // }

  applyFilter() {
    //this.popuniTabelu();
    console.log("Selektovani sastojci: " + this.selectedIngredients);
    this.cocktails = this.allCocktails.filter(cocktail => {
      return cocktail.recipeItems.some(item => 
          this.selectedIngredients.includes(item.ingredientDTO.name));
        
    });
    console.log("Kokteli filtr: " + this.cocktails);
    this.sortBySelectedIngredientsCount();
  }

  sortBySelectedIngredientsCount(): void {
    this.cocktails.sort((a, b) => {
        
        const countA = this.countMatchingIngredients(a);
        const countB = this.countMatchingIngredients(b);
        
        
        return countB - countA;
    });
}

// countMatchingIngredients(cocktail: Cocktail): number {
//     // Broji broj sastojaka u koktelu koji se podudaraju sa selektovanim
//     return cocktail.recipeItems.filter(item => 
//         this.selectedIngredients.includes(item.ingredientDTO.name)
//     ).length;
// }

countMatchingIngredients(cocktail: Cocktail): number {
  let ingredientCountMap = new Map<string, number>();

  
  cocktail.recipeItems.forEach(item => {
      const ingredientName = item.ingredientDTO.name;
      if (ingredientCountMap.has(ingredientName)) {
          ingredientCountMap.set(ingredientName, ingredientCountMap.get(ingredientName)! + 1);
      } else {
          ingredientCountMap.set(ingredientName, 1);
      }
  });

  
  let matchingCount = 0;
  this.selectedIngredients.forEach(selectedIngredient => {
      if (ingredientCountMap.has(selectedIngredient)) {
          matchingCount += ingredientCountMap.get(selectedIngredient)!;
      }
  });

  return matchingCount;
}


 
}
