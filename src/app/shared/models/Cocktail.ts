import { Glass } from "./Glass";
import { RecipeItem } from "./RecipeItem";

export class Cocktail{

    
    cocktailName!: string;
    type!: TypeOfCocktail;
    preparation!:string;
    picture?: string;
    glassDTO!:Glass;
    recipeItems!:Array<RecipeItem>;



}

export enum TypeOfCocktail{
    SUV="SUV",
    SLADAK="SLADAK",
    POLUSLADAK="POLUSLADAK"
}