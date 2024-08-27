import { Component, OnInit } from '@angular/core';
import { Cocktail } from '../shared/models/Cocktail';
import { ActivatedRoute } from '@angular/router';
import { CocktailsService } from '../service/cocktails/cocktails.service';

@Component({
  selector: 'app-coctail-page',
  templateUrl: './coctail-page.component.html',
  styleUrls: ['./coctail-page.component.css']
})
export class CoctailPageComponent implements OnInit{

  cocktail!: Cocktail;

  constructor(private activatedRoute: ActivatedRoute, private cocktailService: CocktailsService){
    
  }

  async vratiKoktel(coctailName: string) {
    
    this.cocktailService
      .findById(coctailName)
      .subscribe((cocktail: Cocktail) => {
        console.log('Koktel:', cocktail);

        this.cocktail = cocktail;
        
        // if(this.cocktail.picture != null)
        //   this.cocktail.picture = atob(this.cocktail.picture!);
        // this.cocktail.glassDTO = cocktail.glassDTO;
        // if(this.cocktail.glassDTO.picture != null)
        //   this.cocktail.glassDTO.picture = atob(this.cocktail.glassDTO.picture!);
             
      });
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      
      if(params['cocktailName']){
        this.vratiKoktel(params['cocktailName']);

      }
       console.log("Ime:", params['cocktailName'])
    })
  }

}
