package rs.ac.bg.fon.fontazijakokteli.entity;

import jakarta.persistence.*;
import lombok.*;
import rs.ac.bg.fon.fontazijakokteli.entity.primaryKey.RecipeItemPK;

import java.io.Serializable;

@Entity
@Table(name="recipeitem")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeItem implements Serializable {

    @EmbeddedId
    private RecipeItemPK itemID;

    @Column(name="quantity")
    private Double quantity;

    @JoinColumn(name="unitofmeasureid",referencedColumnName = "unitofmeasureid")
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UnitOfMeasure measure;

    @JoinColumn(name = "cocktailname", referencedColumnName = "cocktailname", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Cocktail cocktail;

    @JoinColumn(name = "ingredientid", referencedColumnName = "ingredientid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Ingredient ingredient;

    public RecipeItem(RecipeItemPK itemID){
        this.itemID = itemID;
    }

    public RecipeItem(String cocktailName, Integer measureID){
        this.itemID = new RecipeItemPK(cocktailName, measureID);
    }

}
