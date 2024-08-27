package rs.ac.bg.fon.fontazijakokteli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import rs.ac.bg.fon.fontazijakokteli.entity.primaryKey.RecipeItemPK;

@Data
public class RecipeItemDTO {

    private RecipeItemPK itemID;

    private Double quantity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UnitOfMeasureDTO measureDTO;

    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private CocktailDTO cocktailDTO;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private IngredientDTO ingredientDTO;

}
