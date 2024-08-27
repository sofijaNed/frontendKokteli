package rs.ac.bg.fon.fontazijakokteli.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="glass")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Glass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="glassid")
    private Integer glassID;

    @Column(name="name")
    private String name;

    @Column(name="picture")
    private String picture;

}
