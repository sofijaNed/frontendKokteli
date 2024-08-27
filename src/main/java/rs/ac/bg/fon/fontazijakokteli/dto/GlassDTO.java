package rs.ac.bg.fon.fontazijakokteli.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GlassDTO {

    private Integer glassID;

    @NotBlank(message = "Koktel mora imati naziv.")
    private String name;

    private String picture;
}
