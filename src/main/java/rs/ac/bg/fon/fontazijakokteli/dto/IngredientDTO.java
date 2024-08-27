package rs.ac.bg.fon.fontazijakokteli.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IngredientDTO {

    private Integer ingredientID;

    @NotBlank(message = "Mora postojati sastojak.")
    private String name;
}
