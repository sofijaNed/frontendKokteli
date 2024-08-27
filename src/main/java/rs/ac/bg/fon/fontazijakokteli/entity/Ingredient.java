package rs.ac.bg.fon.fontazijakokteli.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="ingredient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ingredientid")
    private Integer ingredientID;

    @Column(name="name")
    private String name;

}
