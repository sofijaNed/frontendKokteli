import { Component, Input, OnInit } from '@angular/core';
import { Tag } from '../shared/models/Tag';
import { CocktailsService } from '../service/cocktails/cocktails.service';
import { Observable } from 'rxjs';
import { Cocktail } from '../shared/models/Cocktail';

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.css']
})
export class TagsComponent implements OnInit{

  

  @Input()
  cocktailPageTags?:string;

  @Input()
  justifyContent:string = 'center';

  tags?:Tag[];
  constructor(private cocktailService:CocktailsService) { }

  ngOnInit(): void {
    if(!this.cocktailPageTags)
      this.cocktailService.getAllTags().subscribe(tags => {
        this.tags = tags;
      });
     

  }


}
