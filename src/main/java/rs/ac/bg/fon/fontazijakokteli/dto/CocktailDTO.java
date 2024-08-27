package rs.ac.bg.fon.fontazijakokteli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import rs.ac.bg.fon.fontazijakokteli.entity.TypeOfCoctaile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

@Data
public class CocktailDTO {

    private String cocktailName;

    @NotNull
    private TypeOfCoctaile type;

    @NotBlank(message = "Koktel mora imati recept.")
    private String preparation;

    private String picture;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GlassDTO glassDTO;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<RecipeItemDTO> recipeItems;
}
