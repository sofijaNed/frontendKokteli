package rs.ac.bg.fon.fontazijakokteli.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.fontazijakokteli.dto.IngredientDTO;
import rs.ac.bg.fon.fontazijakokteli.entity.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
