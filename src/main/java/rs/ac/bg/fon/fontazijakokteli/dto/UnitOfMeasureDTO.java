package rs.ac.bg.fon.fontazijakokteli.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UnitOfMeasureDTO {

    private Integer unitOfMeasureID;

    @NotBlank(message = "Jedinica mere mora imati oznaku.")
    private String measure;
}
