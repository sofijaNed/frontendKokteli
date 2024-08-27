package rs.ac.bg.fon.fontazijakokteli.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="unitofmeasure")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitOfMeasure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="unitofmeasureid")
    private Integer unitOfMeasureID;

    @Column(name="measure")
    private String measure;

}
