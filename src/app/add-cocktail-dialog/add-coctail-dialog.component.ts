import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { GlassService } from '../service/glass/glass.service';
import { Glass } from '../shared/models/Glass';
import { Cocktail } from '../shared/models/Cocktail';
import { Ingredient } from '../shared/models/Ingredient';
import { IngredientService } from '../service/ingredient/ingredient.service';
import { RecipeItem, RecipeItemPK } from '../shared/models/RecipeItem';
import { UnitOfMeasure } from '../shared/models/UnitOfMeasure';
import { MatTable } from '@angular/material/table';
import { RecipeItemService } from '../service/recipeItem/recipe-item.service';
import { UnitOfMeasureService } from '../service/unitOfMeasure/unit-of-measure.service';
import { MatTableDataSource } from '@angular/material/table';
import { CocktailsService } from '../service/cocktails/cocktails.service';
import { HomeComponent } from '../home/home.component';
import { switchMap } from 'rxjs';
import { MessageDialogService } from '../service/message-dialog/message-dialog.service';
import { TypeOfCocktail } from '../shared/models/Cocktail';


@Component({
  selector: 'app-add-coctail-dialog',
  templateUrl: './add-coctail-dialog.component.html',
  styleUrls: ['./add-coctail-dialog.component.css']
})
export class AddCoctailDialogComponent implements OnInit{


  glasses?: Glass[] = [];
  cocktail: Cocktail =new Cocktail();
  ingredients?: Ingredient[] = [];
  ingredient: Ingredient = {} as Ingredient;
  recipeItems: RecipeItem[] = [];
  recipeItem: RecipeItem = new RecipeItem();
  unitOfMeasures?: UnitOfMeasure[] = [];
  spinnerVisible = true;
  selectedFile: File | null = null;
  flag: boolean = false;
  tableSpinner = false;
  dataSource :RecipeItem[] = [];
  allCocktails? : Cocktail[] = [];

  constructor(
    public dialogRef: MatDialogRef<AddCoctailDialogComponent>,
    private glassService: GlassService,
    private ingredientService: IngredientService,
    private unitOfMeasureService: UnitOfMeasureService,
    private cocktailService: CocktailsService,
    private messageDialogService: MessageDialogService
  ) { }

  _recipeItemTable?: MatTable<RecipeItem>;
  @ViewChild(MatTable) set recipeItemTable(recipeItemTable: MatTable<RecipeItem>) {
    if (recipeItemTable) {
      this._recipeItemTable = recipeItemTable;
    }
  };

  displayedColumns: string[] = ['sastojak', 'kolicina', 'jedinicaMere'];

  ngOnInit(): void {
    //this.dataSource = new MatTableDataSource<RecipeItem>(this.cocktail.recipeItems);
    this.cocktail.recipeItems = [];
    this.recipeItem = new RecipeItem();
    this.recipeItem.itemID = new RecipeItemPK();
    this.recipeItem.ingredientDTO = new Ingredient();
    //this.cocktail.recipeItems = [this.recipeItem];
    this.glassService.findAll().subscribe((response) => {
      this.glasses = response;
      console.log("STIGLA SAM OVDE");
      console.log("Case", this.glasses);
      this.spinnerVisible = false;
    });

    this.ingredientService.findAll().subscribe((response) => {
      this.ingredients = response;
      console.log("STIGLA SAM OVDE");
      console.log("Sastojci:", this.ingredients);
      this.spinnerVisible = false;
    
    });

    this.unitOfMeasureService.findAll().subscribe((response) => {
      this.unitOfMeasures = response;
      console.log("STIGLA SAM OVDE");
      console.log("Jedinice mere:", this.unitOfMeasures);
      this.spinnerVisible = false;
    });

  }

  onFileSelected(event: any): void {
    const file: File | null = event.target.files[0];
      if (!file) {
        return; 
      }
    this.selectedFile = event.target.files[0];
    console.log('Selected file:', this.selectedFile);
  
    const reader = new FileReader();
    reader.onload = () => {
      const imageData = reader.result as string; 
      console.log('Image data:', imageData);
      this.cocktail.picture = imageData; 
    };
    reader.readAsDataURL(this.selectedFile!); 
  }
  

  saveRecipeItem():void{

    this.recipeItem.itemID.cocktailName = '';
    //this.recipeItem.itemID.ingredientID = -1;
    //this.assignment.assignmentCPK.employeeID = this.assignment.employeeDTO.employeeID;
    for(let i of this.ingredients!){

      if(this.recipeItem.ingredientDTO.name==i.name){

        //ako ingredient vec postoji
        this.flag=true;
        this.recipeItem.ingredientDTO = i;
        break;

      }
    }
    //ako ne postoji, dodaje se id -1
    if(!this.flag){
      this.recipeItem.ingredientDTO.ingredientID = -1;

    }
    this.flag = false;
    
    this.recipeItem.cocktailDTO = this.cocktail;
    //this.recipeItem.ingredientDTO = this.ingredient;
    
    this.cocktail.recipeItems.push(this.recipeItem);
    

    this.dataSource.push(this.recipeItem);

  // Osvežavanje izvora podataka tabele
    this.dataSource = [...this.dataSource];

    this.recipeItem = new RecipeItem();
    this.recipeItem.itemID = new RecipeItemPK();
    this.recipeItem.ingredientDTO = new Ingredient();
    this.recipeItem.quantity = 0;
    this.recipeItem.measureDTO = new UnitOfMeasure();
    this.recipeItem.ingredientDTO.name = "";
  }

  // confirm(): void {
  //   //this.cocktail.recipeItems.splice(0, 1);

  //   if(!this.proveriJedinstvenostImena(this.cocktail.cocktailName)){

  //     console.log(this.cocktail);
  //     this.messageDialogService.openDialog(true, 'Sistem je uspesno sacuvao koktel.');
  //     this.dialogRef.close(this.cocktail);
  //   }else{
  //     this.messageDialogService.openDialog(true, 'Ne moze se dodati recep za isti koktel!');
  //     console.log("Ne moze se dodati recept za isti koktel.");
  //   }
    
  // }

  async confirm(): Promise<void> {
    // if(this.cocktail.cocktailName !== undefined && this.cocktail.cocktailName.length>0! && 
    //   (this.cocktail.type==TypeOfCocktail.SUV || this.cocktail.type==TypeOfCocktail.SLADAK || this.cocktail.type==TypeOfCocktail.POLUSLADAK) &&
    //   this.cocktail.preparation.length>0 && this.cocktail.glassDTO.name!=="" && this.recipeItems.length>0){

        if (!await this.proveriJedinstvenostImena(this.cocktail.cocktailName)) {
          console.log(this.cocktail);
          this.messageDialogService.openDialog(true, 'Sistem je uspešno sačuvao koktel.');
          this.dialogRef.close(this.cocktail);
        } else {
          this.messageDialogService.openDialog(false, 'Ne moze se dodati recep za isti koktel!');
          console.log("Ne moze se dodati recept za isti koktel.");
        }
      // }else {
      //   this.messageDialogService.openDialog(false, 'Moraju se popuniti sva polja!');
      //   console.log("Ne moze se dodati recept za isti koktel.");
      // }
    
  }

  // async proveriJedinstvenostImena(ime: String): Promise<boolean> {
  //   await this.vratiSveKoktele(); 
  //   for (let i of this.allCocktails!) {
  //     if (i.cocktailName == ime) {
  //       return true;
  //     }
  //   }
  //   return false;
  // }

  async proveriJedinstvenostImena(ime: String): Promise<boolean> {
    await this.vratiSveKoktele(); 
    for (let i of this.allCocktails!) {
      if (i.cocktailName == ime) {
        return true;
      }
    }
    return false;
  }
  
  
  // proveriJedinstvenostImena(ime: String): boolean{

  //   this.vratiSveKoktele();
  //   for(let i of this.allCocktails!){

  //     if(i.cocktailName==ime){
  //       return true;
  //     }

  //   }

  //   return false;
  // }

  // async vratiSveKoktele() {
  //   this.cocktailService
  //     .findAll()
  //     .subscribe((cocktails: Array<Cocktail>) => {

  //       this.allCocktails = cocktails;
  //       for (let i = 0; i < this.allCocktails.length; i++) {  
          
  //           // this.cocktails[i].picture = atob(this.cocktails[i].picture!);
  //           this.allCocktails[i].glassDTO = cocktails[i].glassDTO;
  //           // this.cocktails[i].glassDTO.picture = atob(this.cocktails[i].glassDTO.picture!);
          
          
  //       }
  //     });
  // }

  async vratiSveKoktele(): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      this.cocktailService
        .findAll()
        .subscribe((cocktails: Array<Cocktail>) => {
          this.allCocktails = cocktails;
          for (let i = 0; i < this.allCocktails.length; i++) {  
            this.allCocktails[i].glassDTO = cocktails[i].glassDTO;
          }
          resolve(); 
        }, error => {
          reject(error); 
        });
    });
  }
  
  // addRow(): void {
  //   this.recipeItem.itemID.cocktailName = '';
  //   //this.recipeItem.itemID.ingredientID = -1;
  //   //this.assignment.assignmentCPK.employeeID = this.assignment.employeeDTO.employeeID;
  //   for(let i of this.ingredients!){

  //     if(this.recipeItem.ingredientDTO.name==i.name){

  //       this.flag=true;
  //       this.recipeItem.ingredientDTO = i;
  //       break;

  //     }
  //   }
  //   if(!this.flag){
  //     this.recipeItem.ingredientDTO.ingredientID = -1;

  //   }
  //   this.flag = false;
    
  //   this.recipeItem.cocktailDTO = this.cocktail;
  //   //this.recipeItem.ingredientDTO = this.ingredient;
  //   this.cocktail.recipeItems.push(this.recipeItem);
  //   this.recipeItem = {itemID: {} as RecipeItemPK} as RecipeItem;
  //   this._recipeItemTable!.renderRows();
  // }

  // removeRow(: Assignment): void {
  //   let index = this.project.assignments.findIndex(x => x = assignment)
  //   this.project.assignments.splice(index, 1);
  //   this._assignmentTable!.renderRows();
  // }


}
