package rs.ac.bg.fon.fontazijakokteli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="cocktail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cocktail implements Serializable {

    @Id
    @Column(name="cocktailname")
    private String cocktailName;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private TypeOfCoctaile type;

    @Column(name="preparation")
    private String preparation;

    @Column(name="picture")
    private String picture;

    @JoinColumn(name="glassid",referencedColumnName = "glassid")
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Glass glass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cocktail")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<RecipeItem> recipeItemCollection;





}
