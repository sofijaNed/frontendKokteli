import { Cocktail } from "./Cocktail";
import { Ingredient } from "./Ingredient";
import { UnitOfMeasure } from "./UnitOfMeasure";
export class RecipeItem{

    itemID!: RecipeItemPK;
    quantity!: number;
    measureDTO!: UnitOfMeasure;
    cocktailDTO!: Cocktail;
    ingredientDTO!: Ingredient;
}

export class RecipeItemPK{
    cocktailName!: string;
    ingredientID!: number;
}