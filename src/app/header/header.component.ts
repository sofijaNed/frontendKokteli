import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddCoctailDialogComponent } from '../add-cocktail-dialog/add-coctail-dialog.component';
import { CocktailsService } from '../service/cocktails/cocktails.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  spinnerVisible = true;
   constructor(public dialog: MatDialog, private cocktailService: CocktailsService){}

  openAddCocktailDialog(): void {
     console.log("DOSAO JE DO OVDE");
    // const dialogRef = this.dialog.open(this.addCocktailDialogComponent, {
    //   width: '500px',
    //   // Dodajte ostale opcije dijaloga ovde ako je potrebno
    // });

    const dialogRef = this.dialog.open(AddCoctailDialogComponent);

    // dialogRef.afterClosed().subscribe(result => {
    //   if (result) {
    //     this.spinnerVisible = true;
    //     this.employeeApiService.insert(result).pipe(
    //       catchError(() => {
    //         this.spinnerVisible = false;
    //         return EMPTY;
    //       }),
    //       switchMap(() => this.getEmployees()))
    //       .subscribe();
    //   }
    // });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.spinnerVisible = true;
        this.cocktailService.save(result).pipe(
          )
      }
    });
      //this.spinnerVisible = true;
      // Ovde možete postaviti logiku koja će se izvršiti nakon zatvaranja dijaloga,
      // kao što je osvežavanje podataka ili obrada rezultata
      console.log('The dialog was closed');
    
  }

  
}
